package antlr;// Generated from Zcc.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ZccParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ZccVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ZccParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(ZccParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ZccParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(ZccParser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(ZccParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(ZccParser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#bType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBType(ZccParser.BTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#constDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDef(ZccParser.ConstDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#constInitVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstInitVal(ZccParser.ConstInitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#constExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExp(ZccParser.ConstExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(ZccParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(ZccParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(ZccParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#initVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitVal(ZccParser.InitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(ZccParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(ZccParser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#funcFParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncFParams(ZccParser.FuncFParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#funcFParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncFParam(ZccParser.FuncFParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ZccParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(ZccParser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(ZccParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(ZccParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#lVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLVal(ZccParser.LValContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(ZccParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(ZccParser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(ZccParser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#relExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExp(ZccParser.RelExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#eqExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExp(ZccParser.EqExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#lAndExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLAndExp(ZccParser.LAndExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#lOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOrExp(ZccParser.LOrExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(ZccParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(ZccParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#pUnayOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPUnayOp(ZccParser.PUnayOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#cmpOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpOp(ZccParser.CmpOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#eOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEOp(ZccParser.EOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZccParser#funcRParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncRParams(ZccParser.FuncRParamsContext ctx);
}