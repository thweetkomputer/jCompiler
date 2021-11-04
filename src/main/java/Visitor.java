import antlr.ErrorHandler;
import antlr.ZccBaseVisitor;
import antlr.ZccParser;

import java.util.*;

/**
 * @author jerryzhao
 * @date 2021/11/4
 */
public class Visitor extends ZccBaseVisitor<Void> {
    boolean acc = true;
    int index = 1;
    int constExpRes;
    boolean calConstExp = false;
    boolean declareVar = false;
    Map<String, Integer> constMap = new HashMap<>(128);
    Map<String, Integer> varMap = new HashMap<>(128);
    Map<String, Function> functionMap = new HashMap<String, Function>(128) {
        {
            put("putint", new Function("putint", "void", "i32"));
            put("getint", new Function("getint", "i32", "void"));
            put("putch", new Function("putch", "void", "i32"));
            put("getch", new Function("getch", "i32", "void"));
        }
    };
    Deque<List<Integer>> paramsStack = new LinkedList<>();

    @Override
    public Void visitCompUnit(ZccParser.CompUnitContext ctx) {
        String funcType = null;
        if (ctx.funcDef().funcType().INT() == null) {
            System.exit(-1);
        } else {
            funcType = "i32";
        }
        printf("define dso_local %s @%s()", funcType, ctx.funcDef().ident().IDENT());
        visit(ctx.funcDef().block());
        return null;
    }

    @Override
    public Void visitBlock(ZccParser.BlockContext ctx) {
        print("{\n");
        for (ZccParser.BlockItemContext blockItemContext : ctx.blockItem()) {
            visit(blockItemContext);
        }
        print("}");
        return null;
    }

    @Override
    public Void visitBlockItem(ZccParser.BlockItemContext ctx) {
        visit(ctx.decl() == null ? ctx.stmt() : ctx.decl());
        return null;
    }

    @Override
    public Void visitDecl(ZccParser.DeclContext ctx) {
        visit(ctx.varDecl() == null ? ctx.constDecl() : ctx.varDecl());
        return null;
    }


    @Override
    public Void visitStmt(ZccParser.StmtContext ctx) {
        if (ctx.children.size() == 1) {
            return null;
        }
        if (ctx.children.size() == 2) {
            visit(ctx.exp());
            return null;
        }
        if (ctx.children.size() == 3) {
            visit(ctx.exp());
            printf("\treturn i32 %%%d\n", index-1);
            return null;
        }
        String name = ctx.lVal().ident().IDENT().toString();
        if (findInConstMap(name)) {
            ErrorHandler.err("cannot modify constant value");
        }
        if (!findInVarMap(name)){
            ErrorHandler.err("cannot find variable %s", name);
        }
        visit(ctx.exp());
        printf("\tstore i32 %%%d, i32* %%%d\n", index - 1, varMap.get(name));
        return null;
    }

    @Override
    public Void visitConstDecl(ZccParser.ConstDeclContext ctx) {
        for (ZccParser.ConstDefContext constDefContext : ctx.constDef()) {
            visit(constDefContext);
        }
        return null;
    }

    @Override
    public Void visitConstDef(ZccParser.ConstDefContext ctx) {
        String name = ctx.ident().IDENT().toString();
        if (findInConstMap(name) || findInVarMap(name)) {
            ErrorHandler.err("constant already declare");
        }
        visit(ctx.constInitVal());
        constMap.put(name, constExpRes);
        return null;
    }

    @Override
    public Void visitConstInitVal(ZccParser.ConstInitValContext ctx) {
        calConstExp = true;
        visit(ctx.constExp());
        calConstExp = false;
        return null;
    }

    @Override
    public Void visitConstExp(ZccParser.ConstExpContext ctx) {
        visit(ctx.addExp());
        return null;
    }

    @Override
    public Void visitAddExp(ZccParser.AddExpContext ctx) {
        if (calConstExp) {
            int res;
            visit(ctx.mulExp(0));
            res = constExpRes;
            for (int i = 0; i < ctx.unaryOp().size(); i++) {
                visit(ctx.mulExp(i + 1));
                if (ctx.unaryOp().get(i).ADD() != null) {
                    res += constExpRes;
                } else {
                    res -= constExpRes;
                }
            }
            constExpRes = res;
        }
        int vn = index;
        visit(ctx.mulExp(0));
        for (int i = 0; i < ctx.unaryOp().size(); i++) {
            visit(ctx.mulExp(i + 1));
            if (ctx.unaryOp().get(i).ADD() != null) {
                printf("\t%%%d = add i32 %%%d, %%%d\n", index, vn, index - 1);
            } else {
                printf("\t%%%d = sub i32 %%%d, %%%d\n", index, vn, index - 1);
            }
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitMulExp(ZccParser.MulExpContext ctx) {
        if (calConstExp) {
            int res;
            visit(ctx.unaryExp(0));
            res = constExpRes;
            for (int i = 0; i < ctx.pUnayOp().size(); i++) {
                visit(ctx.unaryExp(i + 1));
                if (ctx.pUnayOp().get(i).MUL() != null) {
                    res *= constExpRes;
                } else if (ctx.pUnayOp().get(i).DIV() != null) {
                    res /= constExpRes;
                } else {
                    res %= constExpRes;
                }
            }
            constExpRes = res;
        }
        int vn = index;
        visit(ctx.unaryExp(0));
        for (int i = 0; i < ctx.pUnayOp().size(); i++) {
            visit(ctx.unaryExp(i + 1));
            if (ctx.pUnayOp().get(i).MUL() != null) {
                printf("\t%%%d = mul i32 %%%d, %%%d\n", index, vn, index - 1);
            } else if (ctx.pUnayOp().get(i).DIV() != null) {
                printf("\t%%%d = sdiv i32 %%%d, %%%d\n", index, vn, index - 1);
            } else {
                printf("\t%%%d = srem i32 %%%d, %%%d\n", index, vn, index - 1);
            }
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitUnaryExp(ZccParser.UnaryExpContext ctx) {
        if (calConstExp) {
            if (ctx.ident() != null) {
                ErrorHandler.err("cannot use function return value to define constant");
            }
            if (ctx.primaryExp() != null) {
                visit(ctx.primaryExp());
            } else if (ctx.unaryOp() != null) {
                visit(ctx.unaryExp());
                int res = constExpRes;
                if (ctx.unaryOp().SUB() != null) {
                    res = -res;
                }
                constExpRes = res;
            }
        }
        if (ctx.primaryExp() != null) {
            visit(ctx.primaryExp());
        } else if (ctx.unaryOp() != null) {
            visit(ctx.unaryExp());
            if (ctx.unaryOp().SUB() != null) {
                printf("\t%%%d = sub i32 0, %%%d\n", index, index - 1);
                index++;
            }
        } else {

            paramsStack.push(new ArrayList<>());
            String funcName = ctx.ident().IDENT().toString();
            Function function = functionMap.get(funcName);
            if (function == null) {
                ErrorHandler.err("cannot found %s()", funcName);
            }
            if (ctx.funcRParams() != null) {
                visit(ctx.funcRParams());
            }
            List<Integer> list = paramsStack.pop();
            assert function != null;
            if ("void".equals(function.returnType)) {
                printf("\tcall %s @%s(", function.returnType, function.name);
            } else {
                printf("\t%%%d = call %s @%s(", index++, function.returnType, function.name);
            }
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    print(", ");
                }
                printf("%s %%%d", function.parameterType, list.get(i));
            }
            printf(")\n");


        }
        return null;
    }

    @Override
    public Void visitFuncRParams(ZccParser.FuncRParamsContext ctx) {
        for (ZccParser.ExpContext expContext : ctx.exp()) {
            visit(expContext);
            assert paramsStack.peek() != null;
            paramsStack.peek().add(index - 1);
        }
        return null;
    }

    @Override
    public Void visitPrimaryExp(ZccParser.PrimaryExpContext ctx) {
        if (ctx.lVal() != null) {
            visit(ctx.lVal());
        } else if (ctx.number() != null) {
            visit(ctx.number());
        } else if (ctx.children.size() == 3) {
            visit(ctx.exp());
        }
        return null;
    }

    @Override
    public Void visitLVal(ZccParser.LValContext ctx) {
        String name = ctx.ident().IDENT().toString();
        if (calConstExp) {
            if (findInVarMap(name)) {
                ErrorHandler.err("cannot use variable to declare constant");
            }
            if (!findInConstMap(name)) {
                ErrorHandler.err("cannot find constant %s", name);
            }
            constExpRes = constMap.get(name);
        }
        if (findInVarMap(name)) {
            printf("\t%%%d = load i32, i32* %%%d\n", index++, varMap.get(name));
        } else if (findInConstMap(name)) {
            printf("\t%%%d = add i32 0, %d\n", index++, constMap.get(name));
        } else {
            ErrorHandler.err("cannot find constant %s", name);
        }

        return null;
    }

    @Override
    public Void visitNumber(ZccParser.NumberContext ctx) {
        int val;
        if (ctx.DECIMAL_CONST() != null) {
            val = Integer.parseInt(ctx.DECIMAL_CONST().toString());
        } else if (ctx.HEXADECIMAL_CONST() != null) {
            val = 0;
            String str = ctx.HEXADECIMAL_CONST().toString().toLowerCase();
            for (int i = 2; i < str.length(); i++) {
                val *= 16;
                val += Character.isDigit(str.charAt(i)) ? str.charAt(i) - '0' : str.charAt(i) - 'a' + 10;
            }
        } else {
            val = 0;
            String str = ctx.OCTAL_CONST().toString();
            for (int i = 2; i < str.length(); i++) {
                val *= 8;
                val += str.charAt(i) - '0';
            }
        }
        if (calConstExp) {
            constExpRes = val;
        } else {
            printf("\t%%%d = add i32 0, %d\n", index++, val);
        }
        return null;
    }

    @Override
    public Void visitVarDecl(ZccParser.VarDeclContext ctx) {
        for (ZccParser.VarDefContext varDefContext : ctx.varDef()) {
            visit(varDefContext);
        }
        return null;
    }

    @Override
    public Void visitVarDef(ZccParser.VarDefContext ctx) {
        String name = ctx.ident().IDENT().toString();
        if (findInConstMap(name) || findInVarMap(name)) {
            ErrorHandler.err("variable already declare");
        }
        switch (ctx.children.size()) {
            case 1:
                declareVar(name);
                break;
            case 3:
                int vn = index;
                declareVar(name);
                visit(ctx.initVal());
                printf("\tstore i32 %%%d, i32* %%%d\n", index - 1, vn);
                break;
            default:
        }
        return null;
    }

    @Override
    public Void visitInitVal(ZccParser.InitValContext ctx) {
        visit(ctx.exp());
        return null;
    }

    @Override
    public Void visitExp(ZccParser.ExpContext ctx) {
        visit(ctx.addExp());
        return null;
    }

    void declareVar(String name) {
        printf("\t%%%d = alloca i32\n", index);
        varMap.put(name, index++);
    }

    void print(String msg) {
        System.out.print(msg);
    }

    void printf(String msg, Object... params) {
        System.out.printf(msg, params);
    }

    boolean findInConstMap(String name) {
        return constMap.containsKey(name);
    }

    boolean findInVarMap(String name) {
        return varMap.containsKey(name);
    }
}

class Function {
    String name;
    String returnType;
    String parameterType;

    public Function() {
    }

    public Function(String name, String returnType, String parameterType) {
        this.name = name;
        this.returnType = returnType;
        this.parameterType = parameterType;
    }
}