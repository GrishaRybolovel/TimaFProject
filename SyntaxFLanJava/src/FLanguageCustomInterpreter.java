import org.antlr.v4.runtime.ParserRuleContext;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.*;

public class FLanguageCustomInterpreter extends FLanguageBaseVisitor<String> {
    private Stack<HashMap<String, String>> ScopeVar = new Stack<>();
    private Stack<HashMap<String, Function>> ScopeFunc = new Stack<>();
    private Stack<HashMap<String, FLanguageParser.ElementContext>> ScopeQuotes = new Stack<>();
    String val;
    FLanguageParser.ElementContext retQuote;

    @Override
    public String visitFunc(FLanguageParser.FuncContext ctx) {
        String functionName = ctx.identifier().Identifier().getText();
        List<FLanguageParser.IdentifierContext> parametersIdentifiers = ctx.identifierList().identifier();
        List<String> parameters = new ArrayList<>();
        for (FLanguageParser.IdentifierContext parameterIdentifier : parametersIdentifiers) {
            parameters.add(parameterIdentifier.Identifier().getText());
        }
        List<FLanguageParser.ElementContext> body = ctx.element();

        Function func = new Function(functionName, parameters, body);


        ScopeFunc.peek().put(functionName, func);
        return null;
    }

    @Override
    public String visitFuncCall(FLanguageParser.FuncCallContext ctx) {
        String functionName = ctx.identifier().Identifier().getText();
        List<FLanguageParser.ElementContext> parameters = ctx.argumentList().element();


        Function myFunc = null;
        boolean foundFunc = false;

        for (HashMap<String, Function> symbols : ScopeFunc) {
            // Iterating through the map
            if (symbols.containsKey(functionName)) {
                myFunc = symbols.get(functionName);
                foundFunc = true;
                break;
            }
        }

//        System.out.println(ScopeFunc);

        if (!foundFunc) {
            throw new BreakStatementException("Function " + functionName + " doesn't exist");
        }

        ScopeVar.push(new HashMap<>());
        ScopeFunc.push(new HashMap<>());
        ScopeQuotes.push(new HashMap<>());

        for (int i = 0; i < myFunc.getParameters().size(); i++) {
//            System.out.println(myFunc.getParameters().get(i));
//            System.out.println(parameters.get(i).getText());
            visitElement(parameters.get(i));
            ScopeVar.peek().put(myFunc.getParameters().get(i), val);
        }

        for (int j = 0; j < myFunc.getBody().size(); j++) {
            int n = myFunc.getBody().get(j).getChildCount();

            for (int i = 0; i < n; ++i) {
                ParseTree child = myFunc.getBody().get(j); //Gets the current element -> ElementContext

                if (child instanceof FLanguageParser.ElementContext elementContext) {
                    visitElement(elementContext);
//                    System.out.println(val);
                } else {
                    System.err.println("Child is not an ElementContext");
                }
            }
        }
        ScopeVar.pop();
        ScopeFunc.pop();

//        val = null;
        if (ScopeVar.size() == 1) {
            String val1 = val;
            System.out.print("Function (" + functionName + " (");
            int cnt = 0;
            for (FLanguageParser.ElementContext argument : parameters) {
                if (argument.getChild(0) instanceof FLanguageParser.LiteralContext) {
                    visitLiteral((FLanguageParser.LiteralContext) argument.getChild(0));
                } else {
                    visitAtom((FLanguageParser.AtomContext) argument.getChild(0));
                }
                if (cnt > 0) {
                    System.out.print(" ");
                }
                System.out.print(val);
            }
            val = val1;
            System.out.println(")) returned:\n" + val);
        }
        return null;
    }

    @Override
    public String visitAtom(FLanguageParser.AtomContext ctx) {
        String varName = ctx.identifier().getText();
        boolean foundVal = false;

        for (HashMap<String, String> symbols : ScopeVar) {
            if (symbols.containsKey(varName)) {
                val = symbols.get(varName);
                foundVal = true;
            }
        }
        if (foundVal) {
            return val;
        } else {
            new BreakStatementException("Variable " + varName + " not in scope");
            return null;
        }
    }

    @Override
    public String visitLiteral(FLanguageParser.LiteralContext ctx) {
        val = ctx.getText();
        return ctx.getText();
    }

    @Override
    public String visitLambda(FLanguageParser.LambdaContext ctx) {
//        List<FLanguageParser.IdentifierContext> parametersIdentifiers = ctx.identifierList().identifier();
//        List<String> parameters = new ArrayList<>();
//        for (FLanguageParser.IdentifierContext parameterIdentifier: parametersIdentifiers) {
//            parameters.add(parameterIdentifier.Identifier().getText());
//        }
//        List<FLanguageParser.ElementContext> body = ctx.element();
//
//        Function func = new Function(parameters, body);
//
//
//        ScopeFunc.peek().put(functionName, func);
        return null;
    }

    @Override
    public String visitAdditiveExpression(FLanguageParser.AdditiveExpressionContext ctx) {
        if (Objects.equals(ctx.getChild(0).getText(), "+")) {
            visitChildren((RuleNode) ctx.getChild(1));
            double v1 = Double.parseDouble(val);
            visitChildren((RuleNode) ctx.getChild(2));
            double v2 = Double.parseDouble(val);
            val = Double.toString(v1 + v2);
            return val;
        } else {
            visitChildren((RuleNode) ctx.getChild(1));
            double v1 = Double.parseDouble(val);
            visitChildren((RuleNode) ctx.getChild(2));
            double v2 = Double.parseDouble(val);
            val = Double.toString(v1 - v2);
            return val;
        }
    }

    @Override
    public String visitMultiplicativeExpression(FLanguageParser.MultiplicativeExpressionContext ctx) {
        if (Objects.equals(ctx.getChild(0).getText(), "*")) {
            visitChildren((RuleNode) ctx.getChild(1));
            double v1 = Double.parseDouble(val);
            visitChildren((RuleNode) ctx.getChild(2));
            double v2 = Double.parseDouble(val);
            val = Double.toString(v1 * v2);
            return val;
        } else {
            visitChildren((RuleNode) ctx.getChild(1));
            double v1 = Double.parseDouble(val);
            visitChildren((RuleNode) ctx.getChild(2));
            double v2 = Double.parseDouble(val);
            val = Double.toString(v1 / v2);
            return val;
        }
    }

    @Override
    public String visitComparisonExpression(FLanguageParser.ComparisonExpressionContext ctx) {
        if (Objects.equals(ctx.comparisonOperator().getText(), "less")) {
//            System.out.println(ctx.getChild(2).getClass());
            if (ctx.getChild(2) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(2));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(2));
            }
            double v1 = Double.parseDouble(val);
            if (ctx.getChild(3) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(3));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(3));
            }
            double v2 = Double.parseDouble(val);
            val = Boolean.toString(v1 < v2);
            return val;
        } else if (Objects.equals(ctx.comparisonOperator().getText(), "greater")) {
            if (ctx.getChild(2) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(2));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(2));
            }
            double v1 = Double.parseDouble(val);
            if (ctx.getChild(3) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(3));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(3));
            }
            double v2 = Double.parseDouble(val);
            val = Boolean.toString(v1 > v2);
            return val;
        } else if (Objects.equals(ctx.comparisonOperator().getText(), "lesseq")) {
            if (ctx.getChild(2) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(2));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(2));
            }
            double v1 = Double.parseDouble(val);
            if (ctx.getChild(3) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(3));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(3));
            }
            double v2 = Double.parseDouble(val);
            val = Boolean.toString(v1 <= v2);
            return val;
        } else if (Objects.equals(ctx.comparisonOperator().getText(), "greatereq")) {
            if (ctx.getChild(2) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(2));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(2));
            }
            double v1 = Double.parseDouble(val);
            if (ctx.getChild(3) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(3));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(3));
            }
            double v2 = Double.parseDouble(val);
            val = Boolean.toString(v1 >= v2);
            return val;
        } else if (Objects.equals(ctx.comparisonOperator().getText(), "equal")) {
            if (ctx.getChild(2) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(2));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(2));
            }
            double v1 = Double.parseDouble(val);
            if (ctx.getChild(3) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(3));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(3));
            }
            double v2 = Double.parseDouble(val);
            val = Boolean.toString(v1 == v2);
            return val;
        } else {
            if (ctx.getChild(2) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(2));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(2));
            }
            double v1 = Double.parseDouble(val);
            if (ctx.getChild(3) instanceof FLanguageParser.LiteralContext) {
                visitLiteral((FLanguageParser.LiteralContext) ctx.getChild(3));
            } else {
                visitAtom((FLanguageParser.AtomContext) ctx.getChild(3));
            }
            double v2 = Double.parseDouble(val);
            val = Boolean.toString(v1 != v2);
            return val;
        }
    }

    @Override
    public String visitCond(FLanguageParser.CondContext ctx) {
        visitExpression((FLanguageParser.ExpressionContext) ctx.getChild(2).getChild(0));
        boolean cond = Boolean.parseBoolean(val);
        FLanguageParser.ElementContext child1 = (FLanguageParser.ElementContext) ctx.getChild(3);
        FLanguageParser.ElementContext child2 = (FLanguageParser.ElementContext) ctx.getChild(4);//Gets the current element -> ElementContext

        if (cond) {
            visitChildren(child1);
            String v1 = val;
            val = v1;
//            System.out.println(v1);
            return v1;
        } else {
            visitChildren(child2);
            String v2 = val;
            val = v2;
//            System.out.println(v2);
            return v2;
        }
    }

    @Override
    public String visitWhileForm(FLanguageParser.WhileFormContext ctx) {
        visitChildren(ctx);
        val = null;
        return null;
    }

    @Override
    public String visitBreakForm(FLanguageParser.BreakFormContext ctx) {
        val = null;
        return null;
    }

    @Override
    public String visitReturnForm(FLanguageParser.ReturnFormContext ctx) {
        val = null;
        return null;
    }

    @Override
    public String visitSetq(FLanguageParser.SetqContext ctx) {
        String variableName = ctx.identifier().Identifier().getText();
        if (ctx.element().getChild(0) instanceof FLanguageParser.SpecialFormContext) {
            if (ctx.element().getChild(0).getChild(0) instanceof FLanguageParser.LambdaContext) {
                FLanguageParser.LambdaContext lambdaF = ((FLanguageParser.LambdaContext) ctx.element().getChild(0).getChild(0));
                List<FLanguageParser.IdentifierContext> parametersIdentifiers = lambdaF.identifierList().identifier();
                List<String> parameters = new ArrayList<>();
                for (FLanguageParser.IdentifierContext parameterIdentifier : parametersIdentifiers) {
                    parameters.add(parameterIdentifier.Identifier().getText());
                }
                List<FLanguageParser.ElementContext> body = lambdaF.element();

                Function func = new Function(variableName, parameters, body);
                ScopeFunc.peek().put(variableName, func);
            } else if (ctx.element().getChild(0).getChild(0) instanceof FLanguageParser.FuncCallContext) {
                visitFuncCall((FLanguageParser.FuncCallContext) ctx.element().getChild(0).getChild(0));
                String variableValue = val;
                ScopeVar.peek().put(variableName, variableValue);
            } else if (ctx.element().getChild(0).getChild(0) instanceof FLanguageParser.QuoteContext) {
                visitQuote((FLanguageParser.QuoteContext) ctx.element().getChild(0).getChild(0));
                FLanguageParser.ElementContext variableValue = retQuote;
                ScopeQuotes.peek().put(variableName, variableValue);
            }
        } else {
            String variableValue = ctx.element().literal().getText();
            ScopeVar.peek().put(variableName, variableValue);
        }
        return null;
    }

    @Override
    public String visitProgram(FLanguageParser.ProgramContext ctx) {
        ScopeVar.push(new HashMap<>());
        ScopeFunc.push(new HashMap<>());
        ScopeQuotes.push(new HashMap<>());
        visitChildren(ctx);
//        System.out.println(val);
        val = null;
        return null;
    }

    @Override
    public String visitQuote(FLanguageParser.QuoteContext ctx) {
        retQuote = ctx.element();
        return null;
    }

    @Override
    public String visitEvalCallUnnamed(FLanguageParser.EvalCallUnnamedContext ctx) {
        visitQuote(ctx.quote());
        if (retQuote.getChild(0) instanceof FLanguageParser.FuncCallContext) {
            visitFuncCall((FLanguageParser.FuncCallContext) retQuote.getChild(0));
        } else {
            throw new BreakStatementException("We can't eval not a function call");
        }
        return null;
    }

    @Override
    public String visitEvalCallNamed(FLanguageParser.EvalCallNamedContext ctx) {
        String varName = ctx.identifier().getText();
        boolean foundVal = false;

        for (HashMap<String, FLanguageParser.ElementContext> symbols : ScopeQuotes) {
            if (symbols.containsKey(varName)) {
                retQuote = symbols.get(varName);
                foundVal = true;
            }
        }
        if (!foundVal) {
            throw new BreakStatementException("Variable " + varName + " not in scope");
        }
        visitFuncCall((FLanguageParser.FuncCallContext) retQuote.getChild(0));
        return null;
    }

    @Override
    public String visitLambdaCall(FLanguageParser.LambdaCallContext ctx){
//        String functionName = ctx.identifier().Identifier().getText();
        List<FLanguageParser.ElementContext> parameters = ctx.argumentList().element();

        List<FLanguageParser.IdentifierContext> parametersIdentifiers = ctx.lambda().identifierList().identifier();
        List<String> my_parameters = new ArrayList<>();
        for (FLanguageParser.IdentifierContext parameterIdentifier : parametersIdentifiers) {
            my_parameters.add(parameterIdentifier.Identifier().getText());
        }
        List<FLanguageParser.ElementContext> body = ctx.lambda().element();

        Function func = new Function("1", my_parameters, body);


        ScopeFunc.peek().put("1", func);


        Function myFunc = func;

//        for (HashMap<String, Function> symbols : ScopeFunc) {
//            // Iterating through the map
//            if (symbols.containsKey(functionName)) {
//                myFunc = symbols.get(functionName);
//                foundFunc = true;
//                break;
//            }
//        }

//        System.out.println(ScopeFunc);

//        if (!foundFunc) {
//            throw new BreakStatementException("Function " + functionName + " doesn't exist");
//        }

        ScopeVar.push(new HashMap<>());
        ScopeFunc.push(new HashMap<>());
        ScopeQuotes.push(new HashMap<>());

        for (int i = 0; i < myFunc.getParameters().size(); i++) {
//            System.out.println(myFunc.getParameters().get(i));
//            System.out.println(parameters.get(i).getText());
            visitElement(parameters.get(i));
            ScopeVar.peek().put(myFunc.getParameters().get(i), val);
        }

        for (int j = 0; j < myFunc.getBody().size(); j++) {
            int n = myFunc.getBody().get(j).getChildCount();

            for (int i = 0; i < n; ++i) {
                ParseTree child = myFunc.getBody().get(j); //Gets the current element -> ElementContext

                if (child instanceof FLanguageParser.ElementContext elementContext) {
                    visitElement(elementContext);
//                    System.out.println(val);
                } else {
                    System.err.println("Child is not an ElementContext");
                }
            }
        }
        ScopeVar.pop();
        ScopeFunc.pop();

//        val = null;
        if (ScopeVar.size() == 1) {
            String val1 = val;
            System.out.print("Lambda function ("  + " (");
            int cnt = 0;
            for (FLanguageParser.ElementContext argument : parameters) {
                if (argument.getChild(0) instanceof FLanguageParser.LiteralContext) {
                    visitLiteral((FLanguageParser.LiteralContext) argument.getChild(0));
                } else {
                    visitAtom((FLanguageParser.AtomContext) argument.getChild(0));
                }
                if (cnt > 0) {
                    System.out.print(" ");
                }
                System.out.print(val);
            }
            val = val1;
            System.out.println(")) returned:\n" + val);
        }
        return null;
    }
}