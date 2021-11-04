package antlr;

/**
 * @author jerryzhao
 * @date 2021/11/4
 */
public class ErrorHandler {
    public static void err(String msg) {
        System.err.println("\n" + msg);
        System.exit(-1);
    }

    public static void err(String msg, Object... params) {
        System.err.printf("\n" + msg, params);
        System.exit(-1);
    }
}
