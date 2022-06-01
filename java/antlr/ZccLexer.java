package antlr;// Generated from Zcc.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ZccLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, VOID=3, INT=4, RETURN=5, WHILE=6, BREAK=7, CONTINUE=8, CONST=9, 
		IDENT=10, DECIMAL_CONST=11, OCTAL_CONST=12, HEXADECIMAL_CONST=13, WHITE_SPACE=14, 
		ADD=15, SUB=16, MUL=17, DIV=18, MOD=19, NOT=20, LPAREN=21, RPAREN=22, 
		LBRACE=23, RBRACE=24, LBRACK=25, RBRACK=26, GE=27, LE=28, GT=29, LT=30, 
		COMMA=31, SEMECOLON=32, EQ=33, NE=34, AND=35, OR=36, ASSIGN=37;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "VOID", "INT", "RETURN", "WHILE", "BREAK", "CONTINUE", 
			"CONST", "IDENT", "DECIMAL_CONST", "OCTAL_CONST", "HEXADECIMAL_CONST", 
			"WHITE_SPACE", "ADD", "SUB", "MUL", "DIV", "MOD", "NOT", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "LBRACK", "RBRACK", "GE", "LE", "GT", "LT", "COMMA", 
			"SEMECOLON", "EQ", "NE", "AND", "OR", "ASSIGN"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'void'", "'int'", "'return'", "'while'", "'break'", 
			"'continue'", "'const'", null, null, null, null, null, "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'!'", "'('", "')'", "'{'", "'}'", "'['", "']'", 
			"'>='", "'<='", "'>'", "'<'", "','", "';'", "'=='", "'!='", "'&&'", "'||'", 
			"'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "VOID", "INT", "RETURN", "WHILE", "BREAK", "CONTINUE", 
			"CONST", "IDENT", "DECIMAL_CONST", "OCTAL_CONST", "HEXADECIMAL_CONST", 
			"WHITE_SPACE", "ADD", "SUB", "MUL", "DIV", "MOD", "NOT", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "LBRACK", "RBRACK", "GE", "LE", "GT", "LT", "COMMA", 
			"SEMECOLON", "EQ", "NE", "AND", "OR", "ASSIGN"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\'\u00d8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\7\13\u0083\n\13\f\13\16\13\u0086"+
		"\13\13\3\f\3\f\7\f\u008a\n\f\f\f\16\f\u008d\13\f\3\r\3\r\7\r\u0091\n\r"+
		"\f\r\16\r\u0094\13\r\3\16\3\16\3\16\3\16\5\16\u009a\n\16\3\16\6\16\u009d"+
		"\n\16\r\16\16\16\u009e\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3"+
		"\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3"+
		"\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\2\2\'\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'\3\2\t\5\2C\\aac|\6\2\62;C\\aac|\3\2\63;\3\2\62;\3\2\62"+
		"9\5\2\62;CHc|\5\2\13\f\17\17\"\"\2\u00dc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3M\3"+
		"\2\2\2\5P\3\2\2\2\7U\3\2\2\2\tZ\3\2\2\2\13^\3\2\2\2\re\3\2\2\2\17k\3\2"+
		"\2\2\21q\3\2\2\2\23z\3\2\2\2\25\u0080\3\2\2\2\27\u0087\3\2\2\2\31\u008e"+
		"\3\2\2\2\33\u0099\3\2\2\2\35\u00a0\3\2\2\2\37\u00a4\3\2\2\2!\u00a6\3\2"+
		"\2\2#\u00a8\3\2\2\2%\u00aa\3\2\2\2\'\u00ac\3\2\2\2)\u00ae\3\2\2\2+\u00b0"+
		"\3\2\2\2-\u00b2\3\2\2\2/\u00b4\3\2\2\2\61\u00b6\3\2\2\2\63\u00b8\3\2\2"+
		"\2\65\u00ba\3\2\2\2\67\u00bc\3\2\2\29\u00bf\3\2\2\2;\u00c2\3\2\2\2=\u00c4"+
		"\3\2\2\2?\u00c6\3\2\2\2A\u00c8\3\2\2\2C\u00ca\3\2\2\2E\u00cd\3\2\2\2G"+
		"\u00d0\3\2\2\2I\u00d3\3\2\2\2K\u00d6\3\2\2\2MN\7k\2\2NO\7h\2\2O\4\3\2"+
		"\2\2PQ\7g\2\2QR\7n\2\2RS\7u\2\2ST\7g\2\2T\6\3\2\2\2UV\7x\2\2VW\7q\2\2"+
		"WX\7k\2\2XY\7f\2\2Y\b\3\2\2\2Z[\7k\2\2[\\\7p\2\2\\]\7v\2\2]\n\3\2\2\2"+
		"^_\7t\2\2_`\7g\2\2`a\7v\2\2ab\7w\2\2bc\7t\2\2cd\7p\2\2d\f\3\2\2\2ef\7"+
		"y\2\2fg\7j\2\2gh\7k\2\2hi\7n\2\2ij\7g\2\2j\16\3\2\2\2kl\7d\2\2lm\7t\2"+
		"\2mn\7g\2\2no\7c\2\2op\7m\2\2p\20\3\2\2\2qr\7e\2\2rs\7q\2\2st\7p\2\2t"+
		"u\7v\2\2uv\7k\2\2vw\7p\2\2wx\7w\2\2xy\7g\2\2y\22\3\2\2\2z{\7e\2\2{|\7"+
		"q\2\2|}\7p\2\2}~\7u\2\2~\177\7v\2\2\177\24\3\2\2\2\u0080\u0084\t\2\2\2"+
		"\u0081\u0083\t\3\2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\26\3\2\2\2\u0086\u0084\3\2\2\2\u0087"+
		"\u008b\t\4\2\2\u0088\u008a\t\5\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2"+
		"\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\30\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008e\u0092\7\62\2\2\u008f\u0091\t\6\2\2\u0090\u008f\3\2\2\2"+
		"\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\32"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096\7\62\2\2\u0096\u009a\7z\2\2\u0097"+
		"\u0098\7\62\2\2\u0098\u009a\7Z\2\2\u0099\u0095\3\2\2\2\u0099\u0097\3\2"+
		"\2\2\u009a\u009c\3\2\2\2\u009b\u009d\t\7\2\2\u009c\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\34\3\2\2"+
		"\2\u00a0\u00a1\t\b\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\17\2\2\u00a3"+
		"\36\3\2\2\2\u00a4\u00a5\7-\2\2\u00a5 \3\2\2\2\u00a6\u00a7\7/\2\2\u00a7"+
		"\"\3\2\2\2\u00a8\u00a9\7,\2\2\u00a9$\3\2\2\2\u00aa\u00ab\7\61\2\2\u00ab"+
		"&\3\2\2\2\u00ac\u00ad\7\'\2\2\u00ad(\3\2\2\2\u00ae\u00af\7#\2\2\u00af"+
		"*\3\2\2\2\u00b0\u00b1\7*\2\2\u00b1,\3\2\2\2\u00b2\u00b3\7+\2\2\u00b3."+
		"\3\2\2\2\u00b4\u00b5\7}\2\2\u00b5\60\3\2\2\2\u00b6\u00b7\7\177\2\2\u00b7"+
		"\62\3\2\2\2\u00b8\u00b9\7]\2\2\u00b9\64\3\2\2\2\u00ba\u00bb\7_\2\2\u00bb"+
		"\66\3\2\2\2\u00bc\u00bd\7@\2\2\u00bd\u00be\7?\2\2\u00be8\3\2\2\2\u00bf"+
		"\u00c0\7>\2\2\u00c0\u00c1\7?\2\2\u00c1:\3\2\2\2\u00c2\u00c3\7@\2\2\u00c3"+
		"<\3\2\2\2\u00c4\u00c5\7>\2\2\u00c5>\3\2\2\2\u00c6\u00c7\7.\2\2\u00c7@"+
		"\3\2\2\2\u00c8\u00c9\7=\2\2\u00c9B\3\2\2\2\u00ca\u00cb\7?\2\2\u00cb\u00cc"+
		"\7?\2\2\u00ccD\3\2\2\2\u00cd\u00ce\7#\2\2\u00ce\u00cf\7?\2\2\u00cfF\3"+
		"\2\2\2\u00d0\u00d1\7(\2\2\u00d1\u00d2\7(\2\2\u00d2H\3\2\2\2\u00d3\u00d4"+
		"\7~\2\2\u00d4\u00d5\7~\2\2\u00d5J\3\2\2\2\u00d6\u00d7\7?\2\2\u00d7L\3"+
		"\2\2\2\b\2\u0084\u008b\u0092\u0099\u009e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}