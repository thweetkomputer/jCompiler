package antlr;// Generated from Zcc.g4 by ANTLR 4.9.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ZccLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, VOID=3, INT=4, RETURN=5, CONST=6, IDENT=7, DECIMAL_CONST=8, 
		OCTAL_CONST=9, HEXADECIMAL_CONST=10, WHITE_SPACE=11, ADD=12, SUB=13, MUL=14, 
		DIV=15, MOD=16, NOT=17, LPAREN=18, RPAREN=19, LBRACE=20, RBRACE=21, LBRACK=22, 
		RBRACK=23, GE=24, LE=25, GT=26, LT=27, COMMA=28, SEMECOLON=29, EQ=30, 
		NE=31, AND=32, OR=33, ASSIGN=34;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "VOID", "INT", "RETURN", "CONST", "IDENT", "DECIMAL_CONST", 
			"OCTAL_CONST", "HEXADECIMAL_CONST", "WHITE_SPACE", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "NOT", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", 
			"RBRACK", "GE", "LE", "GT", "LT", "COMMA", "SEMECOLON", "EQ", "NE", "AND", 
			"OR", "ASSIGN"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'void'", "'int'", "'return'", "'const'", null, 
			null, null, null, null, "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'", "'>='", "'<='", "'>'", "'<'", "','", 
			"';'", "'=='", "'!='", "'&&'", "'||'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "VOID", "INT", "RETURN", "CONST", "IDENT", "DECIMAL_CONST", 
			"OCTAL_CONST", "HEXADECIMAL_CONST", "WHITE_SPACE", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "NOT", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", 
			"RBRACK", "GE", "LE", "GT", "LT", "COMMA", "SEMECOLON", "EQ", "NE", "AND", 
			"OR", "ASSIGN"
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


	public ZccLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Zcc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u00bd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\7\bh\n\b\f\b\16\bk\13\b\3\t\3\t\7\to\n\t\f\t\16\tr\13\t\3\n\3\n"+
		"\7\nv\n\n\f\n\16\ny\13\n\3\13\3\13\3\13\3\13\5\13\177\n\13\3\13\6\13\u0082"+
		"\n\13\r\13\16\13\u0083\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\2\2"+
		"$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$\3\2\t\5\2C\\aac|\6\2\62;C\\aac|\3\2\63;\3\2\62;\3\2\639\5"+
		"\2\62;CHc|\5\2\13\f\17\17\"\"\2\u00c1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5J\3\2\2\2\7O\3\2\2\2\tT\3\2\2"+
		"\2\13X\3\2\2\2\r_\3\2\2\2\17e\3\2\2\2\21l\3\2\2\2\23s\3\2\2\2\25~\3\2"+
		"\2\2\27\u0085\3\2\2\2\31\u0089\3\2\2\2\33\u008b\3\2\2\2\35\u008d\3\2\2"+
		"\2\37\u008f\3\2\2\2!\u0091\3\2\2\2#\u0093\3\2\2\2%\u0095\3\2\2\2\'\u0097"+
		"\3\2\2\2)\u0099\3\2\2\2+\u009b\3\2\2\2-\u009d\3\2\2\2/\u009f\3\2\2\2\61"+
		"\u00a1\3\2\2\2\63\u00a4\3\2\2\2\65\u00a7\3\2\2\2\67\u00a9\3\2\2\29\u00ab"+
		"\3\2\2\2;\u00ad\3\2\2\2=\u00af\3\2\2\2?\u00b2\3\2\2\2A\u00b5\3\2\2\2C"+
		"\u00b8\3\2\2\2E\u00bb\3\2\2\2GH\7k\2\2HI\7h\2\2I\4\3\2\2\2JK\7g\2\2KL"+
		"\7n\2\2LM\7u\2\2MN\7g\2\2N\6\3\2\2\2OP\7x\2\2PQ\7q\2\2QR\7k\2\2RS\7f\2"+
		"\2S\b\3\2\2\2TU\7k\2\2UV\7p\2\2VW\7v\2\2W\n\3\2\2\2XY\7t\2\2YZ\7g\2\2"+
		"Z[\7v\2\2[\\\7w\2\2\\]\7t\2\2]^\7p\2\2^\f\3\2\2\2_`\7e\2\2`a\7q\2\2ab"+
		"\7p\2\2bc\7u\2\2cd\7v\2\2d\16\3\2\2\2ei\t\2\2\2fh\t\3\2\2gf\3\2\2\2hk"+
		"\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\20\3\2\2\2ki\3\2\2\2lp\t\4\2\2mo\t\5\2\2"+
		"nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\22\3\2\2\2rp\3\2\2\2sw\7\62"+
		"\2\2tv\t\6\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\24\3\2\2\2yw\3"+
		"\2\2\2z{\7\62\2\2{\177\7z\2\2|}\7\62\2\2}\177\7Z\2\2~z\3\2\2\2~|\3\2\2"+
		"\2\177\u0081\3\2\2\2\u0080\u0082\t\7\2\2\u0081\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\26\3\2\2\2\u0085"+
		"\u0086\t\b\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\f\2\2\u0088\30\3\2\2"+
		"\2\u0089\u008a\7-\2\2\u008a\32\3\2\2\2\u008b\u008c\7/\2\2\u008c\34\3\2"+
		"\2\2\u008d\u008e\7,\2\2\u008e\36\3\2\2\2\u008f\u0090\7\61\2\2\u0090 \3"+
		"\2\2\2\u0091\u0092\7\'\2\2\u0092\"\3\2\2\2\u0093\u0094\7#\2\2\u0094$\3"+
		"\2\2\2\u0095\u0096\7*\2\2\u0096&\3\2\2\2\u0097\u0098\7+\2\2\u0098(\3\2"+
		"\2\2\u0099\u009a\7}\2\2\u009a*\3\2\2\2\u009b\u009c\7\177\2\2\u009c,\3"+
		"\2\2\2\u009d\u009e\7]\2\2\u009e.\3\2\2\2\u009f\u00a0\7_\2\2\u00a0\60\3"+
		"\2\2\2\u00a1\u00a2\7@\2\2\u00a2\u00a3\7?\2\2\u00a3\62\3\2\2\2\u00a4\u00a5"+
		"\7>\2\2\u00a5\u00a6\7?\2\2\u00a6\64\3\2\2\2\u00a7\u00a8\7@\2\2\u00a8\66"+
		"\3\2\2\2\u00a9\u00aa\7>\2\2\u00aa8\3\2\2\2\u00ab\u00ac\7.\2\2\u00ac:\3"+
		"\2\2\2\u00ad\u00ae\7=\2\2\u00ae<\3\2\2\2\u00af\u00b0\7?\2\2\u00b0\u00b1"+
		"\7?\2\2\u00b1>\3\2\2\2\u00b2\u00b3\7#\2\2\u00b3\u00b4\7?\2\2\u00b4@\3"+
		"\2\2\2\u00b5\u00b6\7(\2\2\u00b6\u00b7\7(\2\2\u00b7B\3\2\2\2\u00b8\u00b9"+
		"\7~\2\2\u00b9\u00ba\7~\2\2\u00baD\3\2\2\2\u00bb\u00bc\7?\2\2\u00bcF\3"+
		"\2\2\2\b\2ipw~\u0083\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}