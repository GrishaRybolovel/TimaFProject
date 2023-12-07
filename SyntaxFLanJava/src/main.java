import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class main {
    public static PrintWriter writer;
    public static void main(String[] args) throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Print input filename:");
//            String inputFilename = scanner.nextLine();
            String inputFilename = "fib";
//            System.out.println("Print output filename:");
//            String outputFilename = scanner.nextLine();
            String outputFilename = "fib_out";
            writer = new PrintWriter(outputFilename);

            CharStream input = CharStreams.fromFileName(inputFilename);
            FLanguageLexer lexer = new FLanguageLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            FLanguageParser parser = new FLanguageParser(tokens);
            ParseTree tree = parser.program();
            FLanguageCustomVisitor visitor = new FLanguageCustomVisitor();
            visitor.visit(tree);

            FLanguageCustomListener listener = new FLanguageCustomListener(parser, visitor.returnUnused());
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new FLanguageCustomListener(parser, visitor.returnUnused()), tree);
            visitor.optimizeAST(tree);

            print(tree, 0);
            writer.close();

            FLanguageCustomInterpreter interpreter = new FLanguageCustomInterpreter();
            interpreter.visit(tree);
        } catch (BreakStatementException e) {
            System.err.println(e.getMessage());
            // Handle the exception as needed, e.g., log the error or exit the program.
        }

    }
    public static void print(ParseTree tree, int height) {
        for (int i = 0; i < height - 1; i++) {
            writer.print("- ");
        }
        for (int i = Math.max(height - 1,0); i < height; i++) {
            writer.print("|-");
        }
        writer.print(tree.getClass().getSimpleName());
        if(tree.getChildCount() == 0){
            writer.println(": " + tree.getText());
        }
        else{
            writer.println();
        }
        for (int i = 0; i < tree.getChildCount(); i++) {
            print(tree.getChild(i), height + 1);
        }
    }


}