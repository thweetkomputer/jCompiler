// Generated from Zcc.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ZccParser}.
 */
public interface ZccListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ZccParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(ZccParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(ZccParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ZccParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ZccParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompUnit(ZccParser.CompUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompUnit(ZccParser.CompUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(ZccParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(ZccParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstDecl(ZccParser.ConstDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstDecl(ZccParser.ConstDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#bType}.
	 * @param ctx the parse tree
	 */
	void enterBType(ZccParser.BTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#bType}.
	 * @param ctx the parse tree
	 */
	void exitBType(ZccParser.BTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#constDef}.
	 * @param ctx the parse tree
	 */
	void enterConstDef(ZccParser.ConstDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#constDef}.
	 * @param ctx the parse tree
	 */
	void exitConstDef(ZccParser.ConstDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void enterConstInitVal(ZccParser.ConstInitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void exitConstInitVal(ZccParser.ConstInitValContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#constExp}.
	 * @param ctx the parse tree
	 */
	void enterConstExp(ZccParser.ConstExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#constExp}.
	 * @param ctx the parse tree
	 */
	void exitConstExp(ZccParser.ConstExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(ZccParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(ZccParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(ZccParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(ZccParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#initVal}.
	 * @param ctx the parse tree
	 */
	void enterInitVal(ZccParser.InitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#initVal}.
	 * @param ctx the parse tree
	 */
	void exitInitVal(ZccParser.InitValContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(ZccParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(ZccParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#funcType}.
	 * @param ctx the parse tree
	 */
	void enterFuncType(ZccParser.FuncTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#funcType}.
	 * @param ctx the parse tree
	 */
	void exitFuncType(ZccParser.FuncTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ZccParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ZccParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(ZccParser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(ZccParser.BlockItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(ZccParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(ZccParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(ZccParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(ZccParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#lVal}.
	 * @param ctx the parse tree
	 */
	void enterLVal(ZccParser.LValContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#lVal}.
	 * @param ctx the parse tree
	 */
	void exitLVal(ZccParser.LValContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(ZccParser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(ZccParser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#addExp}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(ZccParser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#addExp}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(ZccParser.AddExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#mulExp}.
	 * @param ctx the parse tree
	 */
	void enterMulExp(ZccParser.MulExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#mulExp}.
	 * @param ctx the parse tree
	 */
	void exitMulExp(ZccParser.MulExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(ZccParser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(ZccParser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(ZccParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(ZccParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZccParser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void enterFuncRParams(ZccParser.FuncRParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZccParser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void exitFuncRParams(ZccParser.FuncRParamsContext ctx);
}