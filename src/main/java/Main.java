// Main.java

import antlr.ZccLexer;
import antlr.ZccParser;
import org.antlr.v4.runtime.BaseErrorListener;
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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}