// Generated from /Users/grisharybolovlev/IdeaProjects/SyntaxFLanJava/src/FLanguage.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(FLanguageParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(FLanguageParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(FLanguageParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(FLanguageParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(FLanguageParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#specialForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialForm(FLanguageParser.SpecialFormContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuote(FLanguageParser.QuoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#setq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetq(FLanguageParser.SetqContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(FLanguageParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#lambda}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(FLanguageParser.LambdaContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(FLanguageParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(FLanguageParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(FLanguageParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(FLanguageParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(FLanguageParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#whileForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileForm(FLanguageParser.WhileFormContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#returnForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnForm(FLanguageParser.ReturnFormContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#breakForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakForm(FLanguageParser.BreakFormContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(FLanguageParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#lambdaCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaCall(FLanguageParser.LambdaCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(FLanguageParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#logicalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(FLanguageParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(FLanguageParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(FLanguageParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(FLanguageParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#comparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(FLanguageParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(FLanguageParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#evalCallUnnamed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvalCallUnnamed(FLanguageParser.EvalCallUnnamedContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#evalCallNamed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvalCallNamed(FLanguageParser.EvalCallNamedContext ctx);
	/**
	 * Visit a parse tree produced by {@link FLanguageParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(FLanguageParser.PrimaryContext ctx);
}