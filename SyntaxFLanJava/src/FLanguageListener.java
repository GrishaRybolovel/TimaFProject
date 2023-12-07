// Generated from /Users/grisharybolovlev/IdeaProjects/SyntaxFLanJava/src/FLanguage.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FLanguageParser}.
 */
public interface FLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(FLanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(FLanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(FLanguageParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(FLanguageParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(FLanguageParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(FLanguageParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(FLanguageParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(FLanguageParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(FLanguageParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(FLanguageParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#specialForm}.
	 * @param ctx the parse tree
	 */
	void enterSpecialForm(FLanguageParser.SpecialFormContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#specialForm}.
	 * @param ctx the parse tree
	 */
	void exitSpecialForm(FLanguageParser.SpecialFormContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#quote}.
	 * @param ctx the parse tree
	 */
	void enterQuote(FLanguageParser.QuoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#quote}.
	 * @param ctx the parse tree
	 */
	void exitQuote(FLanguageParser.QuoteContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#setq}.
	 * @param ctx the parse tree
	 */
	void enterSetq(FLanguageParser.SetqContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#setq}.
	 * @param ctx the parse tree
	 */
	void exitSetq(FLanguageParser.SetqContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(FLanguageParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(FLanguageParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#lambda}.
	 * @param ctx the parse tree
	 */
	void enterLambda(FLanguageParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#lambda}.
	 * @param ctx the parse tree
	 */
	void exitLambda(FLanguageParser.LambdaContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(FLanguageParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(FLanguageParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(FLanguageParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(FLanguageParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(FLanguageParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(FLanguageParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(FLanguageParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(FLanguageParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(FLanguageParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(FLanguageParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#whileForm}.
	 * @param ctx the parse tree
	 */
	void enterWhileForm(FLanguageParser.WhileFormContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#whileForm}.
	 * @param ctx the parse tree
	 */
	void exitWhileForm(FLanguageParser.WhileFormContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#returnForm}.
	 * @param ctx the parse tree
	 */
	void enterReturnForm(FLanguageParser.ReturnFormContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#returnForm}.
	 * @param ctx the parse tree
	 */
	void exitReturnForm(FLanguageParser.ReturnFormContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#breakForm}.
	 * @param ctx the parse tree
	 */
	void enterBreakForm(FLanguageParser.BreakFormContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#breakForm}.
	 * @param ctx the parse tree
	 */
	void exitBreakForm(FLanguageParser.BreakFormContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(FLanguageParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(FLanguageParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#lambdaCall}.
	 * @param ctx the parse tree
	 */
	void enterLambdaCall(FLanguageParser.LambdaCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#lambdaCall}.
	 * @param ctx the parse tree
	 */
	void exitLambdaCall(FLanguageParser.LambdaCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FLanguageParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FLanguageParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(FLanguageParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(FLanguageParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(FLanguageParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(FLanguageParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(FLanguageParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(FLanguageParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(FLanguageParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(FLanguageParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(FLanguageParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#comparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(FLanguageParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(FLanguageParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(FLanguageParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#evalCallUnnamed}.
	 * @param ctx the parse tree
	 */
	void enterEvalCallUnnamed(FLanguageParser.EvalCallUnnamedContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#evalCallUnnamed}.
	 * @param ctx the parse tree
	 */
	void exitEvalCallUnnamed(FLanguageParser.EvalCallUnnamedContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#evalCallNamed}.
	 * @param ctx the parse tree
	 */
	void enterEvalCallNamed(FLanguageParser.EvalCallNamedContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#evalCallNamed}.
	 * @param ctx the parse tree
	 */
	void exitEvalCallNamed(FLanguageParser.EvalCallNamedContext ctx);
	/**
	 * Enter a parse tree produced by {@link FLanguageParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(FLanguageParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLanguageParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(FLanguageParser.PrimaryContext ctx);
}