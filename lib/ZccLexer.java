// Generated from Zcc.g4 by ANTLR 4.9.2
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
		VOID=1, INT=2, RETURN=3, CONST=4, IDENT=5, DECIMAL_CONST=6, OCTAL_CONST=7, 
		HEXADECIMAL_CONST=8, WHITE_SPACE=9, ADD=10, SUB=11, MUL=12, DIV=13, MOD=14, 
		LPAREN=15, RPAREN=16, LBRACE=17, RBRACE=18, LBRACK=19, RBRACK=20, COMMA=21, 
		SEMECOLON=22, ASSIGN=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"VOID", "INT", "RETURN", "CONST", "IDENT", "DECIMAL_CONST", "OCTAL_CONST", 
			"HEXADECIMAL_CONST", "WHITE_SPACE", "ADD", "SUB", "MUL", "DIV", "MOD", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "COMMA", 
			"SEMECOLON", "ASSIGN"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u0087\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\7\6J\n\6\f\6\16\6M\13\6\3\7\3\7\7\7Q\n\7\f\7\16"+
		"\7T\13\7\3\b\3\b\7\bX\n\b\f\b\16\b[\13\b\3\t\3\t\3\t\3\t\5\ta\n\t\3\t"+
		"\6\td\n\t\r\t\16\te\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\27\3\27\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\3\2\t\5\2C\\aac|\6\2\62;C\\aac|\3\2\63;\3\2\62;\3\2\639\5\2\62;C"+
		"Hc|\5\2\13\f\17\17\"\"\2\u008b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\66\3\2\2\2\7:\3\2\2\2\t"+
		"A\3\2\2\2\13G\3\2\2\2\rN\3\2\2\2\17U\3\2\2\2\21`\3\2\2\2\23g\3\2\2\2\25"+
		"k\3\2\2\2\27m\3\2\2\2\31o\3\2\2\2\33q\3\2\2\2\35s\3\2\2\2\37u\3\2\2\2"+
		"!w\3\2\2\2#y\3\2\2\2%{\3\2\2\2\'}\3\2\2\2)\177\3\2\2\2+\u0081\3\2\2\2"+
		"-\u0083\3\2\2\2/\u0085\3\2\2\2\61\62\7x\2\2\62\63\7q\2\2\63\64\7k\2\2"+
		"\64\65\7f\2\2\65\4\3\2\2\2\66\67\7k\2\2\678\7p\2\289\7v\2\29\6\3\2\2\2"+
		":;\7t\2\2;<\7g\2\2<=\7v\2\2=>\7w\2\2>?\7t\2\2?@\7p\2\2@\b\3\2\2\2AB\7"+
		"e\2\2BC\7q\2\2CD\7p\2\2DE\7u\2\2EF\7v\2\2F\n\3\2\2\2GK\t\2\2\2HJ\t\3\2"+
		"\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\f\3\2\2\2MK\3\2\2\2NR\t\4"+
		"\2\2OQ\t\5\2\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\16\3\2\2\2TR\3"+
		"\2\2\2UY\7\62\2\2VX\t\6\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\20"+
		"\3\2\2\2[Y\3\2\2\2\\]\7\62\2\2]a\7z\2\2^_\7\62\2\2_a\7Z\2\2`\\\3\2\2\2"+
		"`^\3\2\2\2ac\3\2\2\2bd\t\7\2\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2"+
		"f\22\3\2\2\2gh\t\b\2\2hi\3\2\2\2ij\b\n\2\2j\24\3\2\2\2kl\7-\2\2l\26\3"+
		"\2\2\2mn\7/\2\2n\30\3\2\2\2op\7,\2\2p\32\3\2\2\2qr\7\61\2\2r\34\3\2\2"+
		"\2st\7\'\2\2t\36\3\2\2\2uv\7*\2\2v \3\2\2\2wx\7+\2\2x\"\3\2\2\2yz\7}\2"+
		"\2z$\3\2\2\2{|\7\177\2\2|&\3\2\2\2}~\7]\2\2~(\3\2\2\2\177\u0080\7_\2\2"+
		"\u0080*\3\2\2\2\u0081\u0082\7.\2\2\u0082,\3\2\2\2\u0083\u0084\7=\2\2\u0084"+
		".\3\2\2\2\u0085\u0086\7?\2\2\u0086\60\3\2\2\2\b\2KRY`e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}