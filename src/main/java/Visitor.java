import antlr.ZccBaseVisitor;
import antlr.ZccParser;

/**
 * @author jerryzhao
 * @date 2021/11/4
 */
public class Visitor extends ZccBaseVisitor<Void> {
    boolean acc = true;
    @Override
    public Void visitCompUnit(ZccParser.CompUnitContext ctx) {
        String funcType = null;
        if(ctx.funcDef().funcType().INT() != null) {
            acc = false;
            System.exit(-1);
        } else {
            funcType = "i32";
        }
        System.out.printf("define dso_local %s @%s", funcType, ctx.funcDef().ident().IDENT());
        return null;
    }
}
