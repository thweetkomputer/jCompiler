import antlr.ZccLexer;
import antlr.ZccParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author jerryzhao
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String content = getFileContent(new FileInputStream(args[0]), StandardCharsets.UTF_8.toString());
        System.out.println(content);
        CharStream inputStream = CharStreams.fromString(content);
        ZccLexer lexer = new ZccLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ZccParser parser = new ZccParser(tokenStream);
        parser.addErrorListener(new ExitErrorListener());

        ParseTree tree = parser.compUnit();
        Visitor visitor = new Visitor();
        visitor.visit(tree);
    }

    public static String getFileContent(FileInputStream fis, String encoding) throws IOException {
        boolean isComment = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (isComment) {
                    int offset = line.indexOf("*/");
                    if (offset == -1) {
                        continue;
                    }
                    line = line.substring(offset+2);
                    isComment = false;
                } else {
                    int offset1 = line.indexOf("//");
                    int offset2 = line.indexOf("/*");
                    if (offset1 != -1 && (offset2 == -1 || offset2 > offset1)) {
                            line = line.substring(0, offset1);
                    } else if (offset2 != -1 && (offset1 == -1 || offset2 < offset1)){
                        line = line.substring(0, offset2);
                        isComment = true;
                    }
                }
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}