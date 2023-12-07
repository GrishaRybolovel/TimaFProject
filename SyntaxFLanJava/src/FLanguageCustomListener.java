import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.*;

class FLanguageCustomListener extends FLanguageBaseListener {
    private FLanguageParser parser;
    private Set<String> usedVariables = new HashSet<>();
    private Stack<HashMap<String, Boolean>> Scope = new Stack<>();
    private HashMap<Integer, List<Pair<String, String>>> Unused = new HashMap<>();
    private int currentScope = 0;

    public FLanguageCustomListener(FLanguageParser parser, HashMap<Integer, List<Pair<String, String>>> Unused) {
        this.parser = parser;
        this.Unused = Unused;
    }

    @Override
    public void enterProgram(FLanguageParser.ProgramContext ctx){
        Scope.push(new HashMap<>());
    }

    @Override
    public void enterFunc(FLanguageParser.FuncContext ctx) {
        FLanguageParser.IdentifierContext identifier = ctx.identifier();
        if (identifier != null) {
            String funcName = identifier.Identifier().getText();

            if(Unused.containsKey(currentScope)){
                Pair<String, String> value = new Pair<>(funcName, "func");
                for(Pair<String, String> element: Unused.get(currentScope)) {
                    if (Objects.equals(element.getFirst(), value.getFirst()) && Objects.equals(element.getSecond(), value.getSecond())) {
                        System.out.println("Function " + funcName + " deleted(unused)");
                        removeFuncStatement(ctx);
                    }
                }
            }
        }
        currentScope++;
    }

    @Override
    public void exitFunc(FLanguageParser.FuncContext ctx) {
        // Function has exited, remove unused parameters from the parameter list
        FLanguageParser.IdentifierListContext paramList = ctx.identifierList();
        if (paramList != null) {
            for (FLanguageParser.IdentifierContext param : paramList.identifier()) {
                String paramName = param.Identifier().getText();
                if (!usedVariables.contains(paramName)) {
                    paramList.removeLastChild(); // Remove the parameter node
                }
            }
        }
    }

    @Override public void exitIdentifier(FLanguageParser.IdentifierContext ctx) {
        // Add used variables to the set
        String varName = ctx.getText();
        usedVariables.add(varName);
    }

    @Override public void enterSetq(FLanguageParser.SetqContext ctx) {
        FLanguageParser.IdentifierContext identifier = ctx.identifier();
        if (identifier != null) {
            String varName = identifier.Identifier().getText();

            if(Unused.containsKey(currentScope)){
                Pair<String, String> value = new Pair<>(varName, "var");
                for(Pair<String, String> element: Unused.get(currentScope)){
                    if(Objects.equals(element.getFirst(), value.getFirst()) && Objects.equals(element.getSecond(), value.getSecond())){
                        System.out.println("Variable " + varName + " deleted(unused)");
                        removeSetqStatement(ctx);
                    }
                }
            }
        }
    }
    private void removeSetqStatement(FLanguageParser.SetqContext ctx) {
        ParserRuleContext parent = ctx.getParent();
        parent.removeLastChild();
    }

    private void removeFuncStatement(FLanguageParser.FuncContext ctx) {
        ParserRuleContext parent = ctx.getParent();
        parent.removeLastChild();
    }

    private FLanguageParser.ExpressionContext createExpressionContext(ParserRuleContext parent, String text) {
        CommonToken token = new CommonToken(FLanguageLexer.Identifier); // Use the appropriate token type
        token.setText(text);

        FLanguageParser.ExpressionContext expressionContext = new FLanguageParser.ExpressionContext(parent, -1); // -1 is a placeholder for the invoking state
        expressionContext.start = token;
        expressionContext.stop = token;

        // Create a new terminal node with the token
        TerminalNodeImpl terminalNode = new TerminalNodeImpl(token);
        expressionContext.addChild(terminalNode);

        expressionContext.addChild(new FLanguageParser.IdentifierContext(expressionContext, -1)); // Again, -1 is a placeholder

        return expressionContext;
    }

//    @Override
//    public void enterAdditiveExpression(FLanguageParser.AdditiveExpressionContext ctx){
//
//        if(ctx.children.get(1).getText().matches("-?\\d+(\\.\\d+)?") && ctx.children.get(2).getText().matches("-?\\d+(\\.\\d+)?")){
//            ParserRuleContext parent = ctx.getParent();
//            parent.removeLastChild();
//            double res = 0;
//            if(Objects.equals(ctx.children.get(0).getText(), "+")){
//                res += Double.parseDouble(ctx.children.get(1).getText()) + Double.parseDouble(ctx.children.get(2).getText());
//            }
//            else{
//                res += Double.parseDouble(ctx.children.get(1).getText()) - Double.parseDouble(ctx.children.get(2).getText());
//            }
//
//            FLanguageParser.ExpressionContext newExpression = createExpressionContext(ctx, Double.toString(res));
//            parent.addChild(newExpression);
//        }
//    }
}