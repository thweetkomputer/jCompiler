import antlr.ErrorHandler;
import antlr.ZccBaseVisitor;
import antlr.ZccParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author jerryzhao
 * @date 2021/11/4
 */
public class Visitor extends ZccBaseVisitor<Void> {
    boolean acc = true;
    int index = 1;
    int constExpRes;
    boolean calConstExp = false;
    Map<String, Integer> constMap = new HashMap<>(128);
    Map<String, Integer> varMap = new HashMap<>(128);

    @Override
    public Void visitCompUnit(ZccParser.CompUnitContext ctx) {
        String funcType = null;
        if (ctx.funcDef().funcType().INT() == null) {
            System.exit(-1);
        } else {
            funcType = "i32";
        }
        printf("define dso_local %s @%s", funcType, ctx.funcDef().ident().IDENT());
        if (ctx.funcDef().LPAREN() == null || ctx.funcDef().RPAREN() == null) {
            ErrorHandler.err("missing () after function name");
        }
        print("()");
        visit(ctx.funcDef().block());
        return null;
    }

    @Override
    public Void visitBlock(ZccParser.BlockContext ctx) {
        if (ctx.LBRACE() == null || ctx.RBRACE() == null) {
            ErrorHandler.err("missing {} in block");
        }
        print("{");
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
        return null;
    }

    @Override
    public Void visitConstDecl(ZccParser.ConstDeclContext ctx) {
        if (ctx.CONST() == null) {
            ErrorHandler.err("missing const in constant declare");
        }
        if (ctx.bType() == null) {
            ErrorHandler.err("missing type in constant declare");
        }
        if (ctx.COMMA() == null) {
            ErrorHandler.err("missing ; in constant declare");
        }
        for (ZccParser.ConstDefContext constDefContext : ctx.constDef()) {
            visit(constDefContext);
        }
        return null;
    }

    @Override
    public Void visitConstDef(ZccParser.ConstDefContext ctx) {
        if (ctx.ASSIGN() == null || ctx.ident() == null || ctx.constInitVal() == null) {
            ErrorHandler.err("constant undefined in constant declare");
        }
        String name = ctx.ident().IDENT().toString();
        if (findInConstMap(name) || findInVarMap(name)) {
            ErrorHandler.err("constant already declare");
        }
        constExpRes = 0;
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
        return null;
    }

    @Override
    public Void visitPrimaryExp(ZccParser.PrimaryExpContext ctx) {
        if (calConstExp) {
            if (ctx.lVal() != null) {
                visit(ctx.lVal());
            } else if (ctx.number() != null) {
                visit(ctx.number());
            } else if (ctx.children.size() == 3) {
                visit(ctx.exp());
            }
        }
        return null;
    }

    @Override
    public Void visitLVal(ZccParser.LValContext ctx) {
        if (calConstExp) {
            String name = ctx.ident().IDENT().toString();
            if (findInVarMap(name)) {
                ErrorHandler.err("cannot use variable to declare constant");
            }
            if (!findInConstMap(name)) {
                ErrorHandler.err("cannot find constant %s", name);
            }
            constExpRes = constMap.get(name);
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
        }
        return null;
    }

    @Override
    public Void visitVarDecl(ZccParser.VarDeclContext ctx) {
        return null;
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
