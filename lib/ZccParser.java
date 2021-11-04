// Generated from Zcc.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ZccParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VOID=1, INT=2, RETURN=3, CONST=4, IDENT=5, DECIMAL_CONST=6, OCTAL_CONST=7, 
		HEXADECIMAL_CONST=8, WHITE_SPACE=9, ADD=10, SUB=11, MUL=12, DIV=13, MOD=14, 
		LPAREN=15, RPAREN=16, LBRACE=17, RBRACE=18, LBRACK=19, RBRACK=20, COMMA=21, 
		SEMECOLON=22, ASSIGN=23;
	public static final int
		RULE_ident = 0, RULE_number = 1, RULE_compUnit = 2, RULE_decl = 3, RULE_constDecl = 4, 
		RULE_bType = 5, RULE_constDef = 6, RULE_constInitVal = 7, RULE_constExp = 8, 
		RULE_varDef = 9, RULE_varDecl = 10, RULE_initVal = 11, RULE_funcDef = 12, 
		RULE_funcType = 13, RULE_block = 14, RULE_blockItem = 15, RULE_stmt = 16, 
		RULE_exp = 17, RULE_lVal = 18, RULE_primaryExp = 19, RULE_addExp = 20, 
		RULE_mulExp = 21, RULE_unaryExp = 22, RULE_unaryOp = 23, RULE_funcRParams = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"ident", "number", "compUnit", "decl", "constDecl", "bType", "constDef", 
			"constInitVal", "constExp", "varDef", "varDecl", "initVal", "funcDef", 
			"funcType", "block", "blockItem", "stmt", "exp", "lVal", "primaryExp", 
			"addExp", "mulExp", "unaryExp", "unaryOp", "funcRParams"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'void'", "'int'", "'return'", "'const'", null, null, null, null, 
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'('", "')'", "'{'", "'}'", 
			"'['", "']'", "','", "';'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VOID", "INT", "RETURN", "CONST", "IDENT", "DECIMAL_CONST", "OCTAL_CONST", 
			"HEXADECIMAL_CONST", "WHITE_SPACE", "ADD", "SUB", "MUL", "DIV", "MOD", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "COMMA", 
			"SEMECOLON", "ASSIGN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Zcc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ZccParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ZccParser.IDENT, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DECIMAL_CONST() { return getToken(ZccParser.DECIMAL_CONST, 0); }
		public TerminalNode OCTAL_CONST() { return getToken(ZccParser.OCTAL_CONST, 0); }
		public TerminalNode HEXADECIMAL_CONST() { return getToken(ZccParser.HEXADECIMAL_CONST, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DECIMAL_CONST) | (1L << OCTAL_CONST) | (1L << HEXADECIMAL_CONST))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompUnitContext extends ParserRuleContext {
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public CompUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterCompUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitCompUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitCompUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompUnitContext compUnit() throws RecognitionException {
		CompUnitContext _localctx = new CompUnitContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_compUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			funcDef();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONST:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				constDecl();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				varDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDeclContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(ZccParser.CONST, 0); }
		public BTypeContext bType() {
			return getRuleContext(BTypeContext.class,0);
		}
		public List<ConstDefContext> constDef() {
			return getRuleContexts(ConstDefContext.class);
		}
		public ConstDefContext constDef(int i) {
			return getRuleContext(ConstDefContext.class,i);
		}
		public TerminalNode SEMECOLON() { return getToken(ZccParser.SEMECOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ZccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZccParser.COMMA, i);
		}
		public ConstDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterConstDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitConstDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitConstDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclContext constDecl() throws RecognitionException {
		ConstDeclContext _localctx = new ConstDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(CONST);
			setState(61);
			bType();
			setState(62);
			constDef();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(63);
				match(COMMA);
				setState(64);
				constDef();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
			match(SEMECOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ZccParser.INT, 0); }
		public BTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterBType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitBType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitBType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BTypeContext bType() throws RecognitionException {
		BTypeContext _localctx = new BTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDefContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ZccParser.ASSIGN, 0); }
		public ConstInitValContext constInitVal() {
			return getRuleContext(ConstInitValContext.class,0);
		}
		public ConstDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterConstDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitConstDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitConstDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDefContext constDef() throws RecognitionException {
		ConstDefContext _localctx = new ConstDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			ident();
			setState(75);
			match(ASSIGN);
			setState(76);
			constInitVal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstInitValContext extends ParserRuleContext {
		public ConstExpContext constExp() {
			return getRuleContext(ConstExpContext.class,0);
		}
		public ConstInitValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constInitVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterConstInitVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitConstInitVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitConstInitVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstInitValContext constInitVal() throws RecognitionException {
		ConstInitValContext _localctx = new ConstInitValContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constInitVal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			constExp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstExpContext extends ParserRuleContext {
		public AddExpContext addExp() {
			return getRuleContext(AddExpContext.class,0);
		}
		public ConstExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterConstExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitConstExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitConstExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstExpContext constExp() throws RecognitionException {
		ConstExpContext _localctx = new ConstExpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_constExp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			addExp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ZccParser.ASSIGN, 0); }
		public InitValContext initVal() {
			return getRuleContext(InitValContext.class,0);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varDef);
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				ident();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				ident();
				setState(84);
				match(ASSIGN);
				setState(85);
				initVal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public BTypeContext bType() {
			return getRuleContext(BTypeContext.class,0);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public TerminalNode SEMECOLON() { return getToken(ZccParser.SEMECOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ZccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZccParser.COMMA, i);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			bType();
			setState(90);
			varDef();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(91);
				match(COMMA);
				setState(92);
				varDef();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			match(SEMECOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitValContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public InitValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterInitVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitInitVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitInitVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitValContext initVal() throws RecognitionException {
		InitValContext _localctx = new InitValContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_initVal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			exp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public FuncTypeContext funcType() {
			return getRuleContext(FuncTypeContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ZccParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZccParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			funcType();
			setState(103);
			ident();
			setState(104);
			match(LPAREN);
			setState(105);
			match(RPAREN);
			setState(106);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ZccParser.INT, 0); }
		public FuncTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterFuncType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitFuncType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitFuncType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncTypeContext funcType() throws RecognitionException {
		FuncTypeContext _localctx = new FuncTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funcType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ZccParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ZccParser.RBRACE, 0); }
		public List<BlockItemContext> blockItem() {
			return getRuleContexts(BlockItemContext.class);
		}
		public BlockItemContext blockItem(int i) {
			return getRuleContext(BlockItemContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(LBRACE);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << RETURN) | (1L << CONST) | (1L << IDENT) | (1L << DECIMAL_CONST) | (1L << OCTAL_CONST) | (1L << HEXADECIMAL_CONST) | (1L << ADD) | (1L << SUB) | (1L << LPAREN) | (1L << SEMECOLON))) != 0)) {
				{
				{
				setState(111);
				blockItem();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockItemContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public BlockItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterBlockItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitBlockItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitBlockItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockItemContext blockItem() throws RecognitionException {
		BlockItemContext _localctx = new BlockItemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_blockItem);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case CONST:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				decl();
				}
				break;
			case RETURN:
			case IDENT:
			case DECIMAL_CONST:
			case OCTAL_CONST:
			case HEXADECIMAL_CONST:
			case ADD:
			case SUB:
			case LPAREN:
			case SEMECOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public LValContext lVal() {
			return getRuleContext(LValContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ZccParser.ASSIGN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMECOLON() { return getToken(ZccParser.SEMECOLON, 0); }
		public TerminalNode RETURN() { return getToken(ZccParser.RETURN, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stmt);
		int _la;
		try {
			setState(136);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				lVal();
				setState(124);
				match(ASSIGN);
				setState(125);
				exp();
				setState(126);
				match(SEMECOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IDENT) | (1L << DECIMAL_CONST) | (1L << OCTAL_CONST) | (1L << HEXADECIMAL_CONST) | (1L << ADD) | (1L << SUB) | (1L << LPAREN))) != 0)) {
					{
					setState(128);
					exp();
					}
				}

				setState(131);
				match(SEMECOLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				match(RETURN);
				setState(133);
				exp();
				setState(134);
				match(SEMECOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public AddExpContext addExp() {
			return getRuleContext(AddExpContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			addExp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LValContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public LValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterLVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitLVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitLVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LValContext lVal() throws RecognitionException {
		LValContext _localctx = new LValContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_lVal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ZccParser.LPAREN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ZccParser.RPAREN, 0); }
		public LValContext lVal() {
			return getRuleContext(LValContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public PrimaryExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterPrimaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitPrimaryExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitPrimaryExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpContext primaryExp() throws RecognitionException {
		PrimaryExpContext _localctx = new PrimaryExpContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_primaryExp);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				match(LPAREN);
				setState(143);
				exp();
				setState(144);
				match(RPAREN);
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				lVal();
				}
				break;
			case DECIMAL_CONST:
			case OCTAL_CONST:
			case HEXADECIMAL_CONST:
				enterOuterAlt(_localctx, 3);
				{
				setState(147);
				number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddExpContext extends ParserRuleContext {
		public List<MulExpContext> mulExp() {
			return getRuleContexts(MulExpContext.class);
		}
		public MulExpContext mulExp(int i) {
			return getRuleContext(MulExpContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(ZccParser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(ZccParser.ADD, i);
		}
		public List<TerminalNode> SUB() { return getTokens(ZccParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(ZccParser.SUB, i);
		}
		public AddExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterAddExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitAddExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitAddExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddExpContext addExp() throws RecognitionException {
		AddExpContext _localctx = new AddExpContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_addExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			mulExp();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUB) {
				{
				{
				setState(151);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(152);
				mulExp();
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulExpContext extends ParserRuleContext {
		public List<UnaryExpContext> unaryExp() {
			return getRuleContexts(UnaryExpContext.class);
		}
		public UnaryExpContext unaryExp(int i) {
			return getRuleContext(UnaryExpContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(ZccParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(ZccParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(ZccParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(ZccParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(ZccParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(ZccParser.MOD, i);
		}
		public MulExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterMulExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitMulExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitMulExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulExpContext mulExp() throws RecognitionException {
		MulExpContext _localctx = new MulExpContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_mulExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			unaryExp();
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(159);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(160);
				unaryExp();
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExpContext extends ParserRuleContext {
		public PrimaryExpContext primaryExp() {
			return getRuleContext(PrimaryExpContext.class,0);
		}
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ZccParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ZccParser.RPAREN, 0); }
		public FuncRParamsContext funcRParams() {
			return getRuleContext(FuncRParamsContext.class,0);
		}
		public UnaryOpContext unaryOp() {
			return getRuleContext(UnaryOpContext.class,0);
		}
		public UnaryExpContext unaryExp() {
			return getRuleContext(UnaryExpContext.class,0);
		}
		public UnaryExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterUnaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitUnaryExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitUnaryExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpContext unaryExp() throws RecognitionException {
		UnaryExpContext _localctx = new UnaryExpContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unaryExp);
		int _la;
		try {
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				primaryExp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				ident();
				setState(168);
				match(LPAREN);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IDENT) | (1L << DECIMAL_CONST) | (1L << OCTAL_CONST) | (1L << HEXADECIMAL_CONST) | (1L << ADD) | (1L << SUB) | (1L << LPAREN))) != 0)) {
					{
					setState(169);
					funcRParams();
					}
				}

				setState(172);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(174);
				unaryOp();
				setState(175);
				unaryExp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOpContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(ZccParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(ZccParser.SUB, 0); }
		public UnaryOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterUnaryOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitUnaryOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitUnaryOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOpContext unaryOp() throws RecognitionException {
		UnaryOpContext _localctx = new UnaryOpContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_unaryOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_la = _input.LA(1);
			if ( !(_la==ADD || _la==SUB) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncRParamsContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ZccParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ZccParser.COMMA, i);
		}
		public FuncRParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcRParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).enterFuncRParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZccListener ) ((ZccListener)listener).exitFuncRParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZccVisitor ) return ((ZccVisitor<? extends T>)visitor).visitFuncRParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncRParamsContext funcRParams() throws RecognitionException {
		FuncRParamsContext _localctx = new FuncRParamsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_funcRParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			exp();
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(182);
				match(COMMA);
				setState(183);
				exp();
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31\u00c0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\5\5=\n\5\3\6\3\6\3\6\3\6\3"+
		"\6\7\6D\n\6\f\6\16\6G\13\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\5\13Z\n\13\3\f\3\f\3\f\3\f\7\f`\n\f\f\f"+
		"\16\fc\13\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\7\20s\n\20\f\20\16\20v\13\20\3\20\3\20\3\21\3\21\5\21|\n\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\5\22\u0084\n\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u008b\n\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u0097\n\25\3\26\3\26\3\26\7\26\u009c\n\26\f\26\16\26\u009f\13\26\3\27"+
		"\3\27\3\27\7\27\u00a4\n\27\f\27\16\27\u00a7\13\27\3\30\3\30\3\30\3\30"+
		"\5\30\u00ad\n\30\3\30\3\30\3\30\3\30\3\30\5\30\u00b4\n\30\3\31\3\31\3"+
		"\32\3\32\3\32\7\32\u00bb\n\32\f\32\16\32\u00be\13\32\3\32\2\2\33\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\5\3\2\b\n\3\2\f\r\3"+
		"\2\16\20\2\u00b7\2\64\3\2\2\2\4\66\3\2\2\2\68\3\2\2\2\b<\3\2\2\2\n>\3"+
		"\2\2\2\fJ\3\2\2\2\16L\3\2\2\2\20P\3\2\2\2\22R\3\2\2\2\24Y\3\2\2\2\26["+
		"\3\2\2\2\30f\3\2\2\2\32h\3\2\2\2\34n\3\2\2\2\36p\3\2\2\2 {\3\2\2\2\"\u008a"+
		"\3\2\2\2$\u008c\3\2\2\2&\u008e\3\2\2\2(\u0096\3\2\2\2*\u0098\3\2\2\2,"+
		"\u00a0\3\2\2\2.\u00b3\3\2\2\2\60\u00b5\3\2\2\2\62\u00b7\3\2\2\2\64\65"+
		"\7\7\2\2\65\3\3\2\2\2\66\67\t\2\2\2\67\5\3\2\2\289\5\32\16\29\7\3\2\2"+
		"\2:=\5\n\6\2;=\5\26\f\2<:\3\2\2\2<;\3\2\2\2=\t\3\2\2\2>?\7\6\2\2?@\5\f"+
		"\7\2@E\5\16\b\2AB\7\27\2\2BD\5\16\b\2CA\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF"+
		"\3\2\2\2FH\3\2\2\2GE\3\2\2\2HI\7\30\2\2I\13\3\2\2\2JK\7\4\2\2K\r\3\2\2"+
		"\2LM\5\2\2\2MN\7\31\2\2NO\5\20\t\2O\17\3\2\2\2PQ\5\22\n\2Q\21\3\2\2\2"+
		"RS\5*\26\2S\23\3\2\2\2TZ\5\2\2\2UV\5\2\2\2VW\7\31\2\2WX\5\30\r\2XZ\3\2"+
		"\2\2YT\3\2\2\2YU\3\2\2\2Z\25\3\2\2\2[\\\5\f\7\2\\a\5\24\13\2]^\7\27\2"+
		"\2^`\5\24\13\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2"+
		"\2\2de\7\30\2\2e\27\3\2\2\2fg\5$\23\2g\31\3\2\2\2hi\5\34\17\2ij\5\2\2"+
		"\2jk\7\21\2\2kl\7\22\2\2lm\5\36\20\2m\33\3\2\2\2no\7\4\2\2o\35\3\2\2\2"+
		"pt\7\23\2\2qs\5 \21\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2"+
		"\2vt\3\2\2\2wx\7\24\2\2x\37\3\2\2\2y|\5\b\5\2z|\5\"\22\2{y\3\2\2\2{z\3"+
		"\2\2\2|!\3\2\2\2}~\5&\24\2~\177\7\31\2\2\177\u0080\5$\23\2\u0080\u0081"+
		"\7\30\2\2\u0081\u008b\3\2\2\2\u0082\u0084\5$\23\2\u0083\u0082\3\2\2\2"+
		"\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u008b\7\30\2\2\u0086\u0087"+
		"\7\5\2\2\u0087\u0088\5$\23\2\u0088\u0089\7\30\2\2\u0089\u008b\3\2\2\2"+
		"\u008a}\3\2\2\2\u008a\u0083\3\2\2\2\u008a\u0086\3\2\2\2\u008b#\3\2\2\2"+
		"\u008c\u008d\5*\26\2\u008d%\3\2\2\2\u008e\u008f\5\2\2\2\u008f\'\3\2\2"+
		"\2\u0090\u0091\7\21\2\2\u0091\u0092\5$\23\2\u0092\u0093\7\22\2\2\u0093"+
		"\u0097\3\2\2\2\u0094\u0097\5&\24\2\u0095\u0097\5\4\3\2\u0096\u0090\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0096\u0095\3\2\2\2\u0097)\3\2\2\2\u0098\u009d"+
		"\5,\27\2\u0099\u009a\t\3\2\2\u009a\u009c\5,\27\2\u009b\u0099\3\2\2\2\u009c"+
		"\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e+\3\2\2\2"+
		"\u009f\u009d\3\2\2\2\u00a0\u00a5\5.\30\2\u00a1\u00a2\t\4\2\2\u00a2\u00a4"+
		"\5.\30\2\u00a3\u00a1\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6-\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00b4\5(\25\2"+
		"\u00a9\u00aa\5\2\2\2\u00aa\u00ac\7\21\2\2\u00ab\u00ad\5\62\32\2\u00ac"+
		"\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\22"+
		"\2\2\u00af\u00b4\3\2\2\2\u00b0\u00b1\5\60\31\2\u00b1\u00b2\5.\30\2\u00b2"+
		"\u00b4\3\2\2\2\u00b3\u00a8\3\2\2\2\u00b3\u00a9\3\2\2\2\u00b3\u00b0\3\2"+
		"\2\2\u00b4/\3\2\2\2\u00b5\u00b6\t\3\2\2\u00b6\61\3\2\2\2\u00b7\u00bc\5"+
		"$\23\2\u00b8\u00b9\7\27\2\2\u00b9\u00bb\5$\23\2\u00ba\u00b8\3\2\2\2\u00bb"+
		"\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\63\3\2\2"+
		"\2\u00be\u00bc\3\2\2\2\20<EYat{\u0083\u008a\u0096\u009d\u00a5\u00ac\u00b3"+
		"\u00bc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}