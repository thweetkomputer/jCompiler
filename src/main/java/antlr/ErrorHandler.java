package antlr;

/**
 * @author jerryzhao
 * @date 2021/11/4
 * @description
 */
public class ErrorHandler {
    public static void err(String msg) {
        System.out.println(msg);
        System.exit(-1);
    }
}
