import antlr.ErrorHandler;
import antlr.ZccBaseVisitor;
import antlr.ZccParser;

import java.util.HashMap;
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
        System.out.println(constMap);
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
        int res = 0;
        visit(ctx.mulExp(0));
        for (int i = 1; i < ctx.children.size(); i += 2) {

        }
            return null;
    }

    @Override
    public Void visitVarDecl(ZccParser.VarDeclContext ctx) {
        int a;
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
