import org.antlr.v4.runtime.ParserRuleContext;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.*;

public class FLanguageCustomVisitor extends FLanguageBaseVisitor<Void> {

    //This to variables to check right bool and return insertion
    private boolean insideLoop = false;
    private boolean insideFunc = false;
    private int currentScope = 0;

    // Symbol table
    private Stack<HashMap<String, String>> Scope = new Stack<>();
    private Stack<HashMap<Integer, List<Triple<String, String, Boolean>>>> Tracking = new Stack<>();
    private HashMap<Integer, List<Pair<String, String>>> Unused = new HashMap<>();

    @Override
    public Void visitFunc(FLanguageParser.FuncContext ctx) {
        // Extract function information from the context
        String functionName = ctx.identifier().Identifier().getText();
        List<FLanguageParser.IdentifierContext> parameters = ctx.identifierList().identifier(); // Implement this

        // Iterating through the stack
        for (HashMap<String, String> symbols : Scope) {
            // Iterating through the map
            if(symbols.containsKey(functionName)){
                if(Objects.equals(symbols.get(functionName), "func")){
                    throw new RuntimeException("Function " + functionName + " is already declared");
                }
            }
        }
        Scope.peek().put(functionName, "func");

        Triple<String, String, Boolean> myTriple = new Triple<>(functionName, "func", false);
        if(!Tracking.peek().containsKey(currentScope)){
            Tracking.peek().put(currentScope, new ArrayList<>());
        }
        Tracking.peek().get(currentScope).add(myTriple);

        Scope.push(new HashMap<>());

//        For tracking unused variables
        Tracking.push(new HashMap<>());
        currentScope++;

        for(FLanguageParser.IdentifierContext argument: parameters){
            for (HashMap<String, String> symbols : Scope) {
                // Iterating through the map
                if(symbols.containsKey(argument.getText())){
                    if(Objects.equals(symbols.get(argument.getText()), "var")){
                        throw new RuntimeException("Variable " + argument.getText() + " is already declared");
                    }
                }
            }

            Scope.peek().put(argument.getText(), "var");
        }

        insideFunc = true;
        visitChildren(ctx);
        insideFunc = false;

        for(Triple<String, String, Boolean> argument: Tracking.peek().getOrDefault(currentScope, new ArrayList<>())){
            if(!argument.getThird()){
                Pair<String, String> myPair = new Pair<>(argument.getFirst(), argument.getSecond());
                if(!Unused.containsKey(currentScope)){
                    Unused.put(currentScope, new ArrayList<>());
                }
                Unused.get(currentScope).add(myPair);
            }
        }

        Scope.pop();
        Tracking.pop();
        return null;

        // Continue visiting the function declaration
//        super.visitFunc(ctx);
//        return null;
    }

    @Override
    public Void visitFuncCall(FLanguageParser.FuncCallContext ctx) {
        String functionName = ctx.identifier().Identifier().getText();
        List<FLanguageParser.ElementContext> parameters = ctx.argumentList().element(); // Implement this

        // Iterating through the stack
        boolean flag = false;
        for (HashMap<String, String> symbols : Scope) {
            // Iterating through the map
            if(symbols.containsKey(functionName)){
                if(Objects.equals(symbols.get(functionName), "func")){
                    flag = true;
                }
            }
        }

        if (!flag) {
            // Function is not declared
            throw new RuntimeException("Function " + functionName + " is not declared");
        }
        // Visit the arguments
        for (FLanguageParser.ElementContext argument : parameters) {
            if(!argument.getText().matches("-?\\d+(\\.\\d+)?") && argument.getText().matches("[A-Za-z]+")){
                boolean flag_args = false;
                for (HashMap<String, String> symbols : Scope) {
                    // Iterating through the map
                    if(symbols.containsKey(argument.getText())){
                        if(Objects.equals(symbols.get(argument.getText()), "var")){
                            flag_args = true;
                        }
                    }
                }
                if(!flag_args) {
                    throw new RuntimeException("Function " + functionName + " takes undeclared variable " + argument.getText());
                }
            }
        }

        boolean flag_func = true;
        for(HashMap<Integer, List<Triple<String, String, Boolean>>> scopes: Tracking){
            if(flag_func) {
                for (Integer element : scopes.keySet()) {
                    if(flag_func) {
                        Triple<String, String, Boolean> myTriple = new Triple<>(functionName, "func", false);
                        for (Triple<String, String, Boolean> tri : scopes.get(element)) {
                            if (Objects.equals(tri.getFirst(), myTriple.getFirst()) && Objects.equals(tri.getSecond(), myTriple.getSecond()) && tri.getThird() == myTriple.getThird() && flag_func) {
                                tri.setThird(true);
                                flag_func = false;
                            }
                        }
                    }
                }
            }
        }

        for(FLanguageParser.ElementContext argument : parameters){
            for(HashMap<Integer, List<Triple<String, String, Boolean>>> scopes: Tracking){
                for(Integer element: scopes.keySet()){
                    Triple<String, String, Boolean> myTriple = new Triple<>(argument.getText(), "var", false);
                    for(Triple<String, String, Boolean> tri: scopes.get(element)){
                        if(Objects.equals(tri.getFirst(), myTriple.getFirst()) && Objects.equals(tri.getSecond(), myTriple.getSecond()) && tri.getThird() == myTriple.getThird()){
                            tri.setThird(true);
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Void visitEvalCallNamed(FLanguageParser.EvalCallNamedContext ctx) {
        String variableName = ctx.identifier().Identifier().getText();
        if(variableName.matches("[A-Za-z]+")) {
            boolean flag_args = false;
            for (HashMap<String, String> symbols : Scope) {
                // Iterating through the map
                if (symbols.containsKey(variableName)) {
                    if (Objects.equals(symbols.get(variableName), "func")) {
                        flag_args = true;
                    }
                }
            }
            if (!flag_args) {
                throw new RuntimeException("Undeclared variable " + variableName);
            }
        }
        return null;
    }

    @Override
    public Void visitAtom(FLanguageParser.AtomContext ctx) {
        String variableName = ctx.identifier().Identifier().getText();
        if(variableName.matches("[A-Za-z]+")) {
            boolean flag_args = false;
            for (HashMap<String, String> symbols : Scope) {
                // Iterating through the map
                if (symbols.containsKey(variableName)) {
                    if (Objects.equals(symbols.get(variableName), "var")) {
                        flag_args = true;
                    }
                }
            }
            if (!flag_args) {
                throw new RuntimeException("Undeclared variable " + variableName);
            }
        }

        for(HashMap<Integer, List<Triple<String, String, Boolean>>> scopes: Tracking){
            for(Integer element: scopes.keySet()){
                Triple<String, String, Boolean> myTriple = new Triple<>(variableName, "var", false);
                for(Triple<String, String, Boolean> tri: scopes.get(element)){
                    if(Objects.equals(tri.getFirst(), myTriple.getFirst()) && Objects.equals(tri.getSecond(), myTriple.getSecond()) && tri.getThird() == myTriple.getThird()){
                        tri.setThird(true);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Void visitWhileForm(FLanguageParser.WhileFormContext ctx) {
        insideLoop = true;
        visitChildren(ctx);
        insideLoop = false;
        return null;
    }

    @Override
    public Void visitBreakForm(FLanguageParser.BreakFormContext ctx) {
        if (!insideLoop) {
            throw new RuntimeException("Break used outside of a loop");
        }
        return null;
    }

    @Override
    public Void visitReturnForm(FLanguageParser.ReturnFormContext ctx) {
        if(!insideFunc){
            System.out.println("Error: 'return' used outside of a function.");
        }
        return null;
    }

    @Override
    public Void visitSetq(FLanguageParser.SetqContext ctx) {
        String variableName = ctx.identifier().Identifier().getText();
        for (HashMap<String, String> symbols : Scope) {
            // Iterating through the map
            if(symbols.containsKey(variableName)){
                if(Objects.equals(symbols.get(variableName), "var")){
                    throw new RuntimeException("Variable " + variableName + " is already declared");
                }
            }
        }
        if (ctx.element().getChild(0) instanceof FLanguageParser.SpecialFormContext) {
            Scope.peek().put(variableName, "func");
        } else {
            Scope.peek().put(variableName, "var");
        }

        Triple<String, String, Boolean> myTriple = new Triple<>(variableName, "var", false);
        if(!Tracking.peek().containsKey(currentScope)){
            Tracking.peek().put(currentScope, new ArrayList<>());
        }
        Tracking.peek().get(currentScope).add(myTriple);
        return null;
    }

    @Override
    public Void visitProgram(FLanguageParser.ProgramContext ctx){
        Scope.push(new HashMap<>());
        Tracking.push(new HashMap<>());
        visitChildren(ctx);


        for(int i = 0; i <= currentScope; i++) {
            for (Triple<String, String, Boolean> argument : Tracking.peek().getOrDefault(i, new ArrayList<>())) {
                if (!argument.getThird()) {
                    Pair<String, String> myPair = new Pair<>(argument.getFirst(), argument.getSecond());
                    if (!Unused.containsKey(i)) {
                        Unused.put(i, new ArrayList<>());
                    }
                    Unused.get(i).add(myPair);
                }
            }
        }

//        System.out.println(Tracking.get(0).get(0).get(1).getThird());

        Scope.pop();
        Tracking.pop();

        return null;
    }

    @Override
    public Void visitLambda(FLanguageParser.LambdaContext ctx) {
        // Extract function information from the context
        List<FLanguageParser.IdentifierContext> parameters = ctx.identifierList().identifier(); // Implement this

        Scope.push(new HashMap<>());

//        For tracking unused variables
        Tracking.push(new HashMap<>());
        currentScope++;

        for(FLanguageParser.IdentifierContext argument: parameters){
            for (HashMap<String, String> symbols : Scope) {
                // Iterating through the map
                if(symbols.containsKey(argument.getText())){
                    if(Objects.equals(symbols.get(argument.getText()), "var")){
                        throw new RuntimeException("Variable " + argument.getText() + " is already declared");
                    }
                }
            }

            Scope.peek().put(argument.getText(), "var");
        }

        insideFunc = true;
        visitChildren(ctx);
        insideFunc = false;

        for(Triple<String, String, Boolean> argument: Tracking.peek().getOrDefault(currentScope, new ArrayList<>())){
            if(!argument.getThird()){
                Pair<String, String> myPair = new Pair<>(argument.getFirst(), argument.getSecond());
                if(!Unused.containsKey(currentScope)){
                    Unused.put(currentScope, new ArrayList<>());
                }
                Unused.get(currentScope).add(myPair);
            }
        }

        Scope.pop();
        Tracking.pop();
        return null;

        // Continue visiting the function declaration
//        super.visitFunc(ctx);
//        return null;
    }

    public HashMap<Integer, List<Pair<String, String>>> returnUnused(){
        return this.Unused;
    }

    public void optimizeAST(ParseTree tree) {
        ParseTreeWalker walker = new ParseTreeWalker();
        FLanguageListener listener = new FLanguageBaseListener() {
        };
        walker.walk(listener, tree);
    }
}