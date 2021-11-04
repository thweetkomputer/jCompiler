import antlr.ErrorHandler;
import antlr.ZccBaseVisitor;
import antlr.ZccParser;

/**
 * @author jerryzhao
 * @date 2021/11/4
 */
public class Visitor extends ZccBaseVisitor<Void> {
    boolean acc = true;
    int index = 1;
    @Override
    public Void visitCompUnit(ZccParser.CompUnitContext ctx) {
        String funcType = null;
        if(ctx.funcDef().funcType().INT() == null) {
            System.exit(-1);
        } else {
            funcType = "i32";
        }
        System.out.printf("define dso_local %s @%s", funcType, ctx.funcDef().ident().IDENT());
        if (ctx.funcDef().LPAREN() == null) {
            ErrorHandler.err("missing { after function name");
        }
        System.out.print("{\n");
        System.out.print("}\n");
        return null;
    }


}
