// Generated from C:/Users/Fergal/Documents/AAA Uni Work/Third Year/CE305/Assignment 2\whileGrammar.g4 by ANTLR 4.7.2
package generated;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class whileGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, ASS=5, LBR=6, RBR=7, LPARA=8, RPARA=9, 
		SC=10, IF=11, ELSE=12, WHILE=13, DO=14, ADD=15, SUB=16, MULT=17, DIV=18, 
		EXPO=19, MODULUS=20, LTHAN=21, GTHAN=22, LTHANEQ=23, GTHANEQ=24, EQUALITY=25, 
		INEQUALITY=26, AND=27, OR=28, NOT=29, FLOAT=30, ID=31, INT=32, FLTP=33, 
		BOOL=34, STRING=35, DECINT=36, DECFLOAT=37, DECBOOL=38, COMMENT=39, WS=40;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "ASS", "LBR", "RBR", "LPARA", "RPARA", 
			"SC", "IF", "ELSE", "WHILE", "DO", "ADD", "SUB", "MULT", "DIV", "EXPO", 
			"MODULUS", "LTHAN", "GTHAN", "LTHANEQ", "GTHANEQ", "EQUALITY", "INEQUALITY", 
			"AND", "OR", "NOT", "FLOAT", "ID", "INT", "FLTP", "BOOL", "STRING", "DECINT", 
			"DECFLOAT", "DECBOOL", "COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'$void'", "','", "'Return'", "'print'", "'='", "'{'", "'}'", "'('", 
			"')'", "';'", "'if'", "'else'", "'while'", "'do'", "'+'", "'-'", "'*'", 
			"'/'", "'^'", "'%'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&&'", 
			"'||'", "'!'", null, null, null, "'.'", null, null, "'$int'", "'$float'", 
			"'$boolean'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "ASS", "LBR", "RBR", "LPARA", "RPARA", 
			"SC", "IF", "ELSE", "WHILE", "DO", "ADD", "SUB", "MULT", "DIV", "EXPO", 
			"MODULUS", "LTHAN", "GTHAN", "LTHANEQ", "GTHANEQ", "EQUALITY", "INEQUALITY", 
			"AND", "OR", "NOT", "FLOAT", "ID", "INT", "FLTP", "BOOL", "STRING", "DECINT", 
			"DECFLOAT", "DECBOOL", "COMMENT", "WS"
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


	public whileGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "whileGrammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u00fd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\7\37\u00ad\n\37\f\37\16\37"+
		"\u00b0\13\37\3 \3 \7 \u00b4\n \f \16 \u00b7\13 \3!\6!\u00ba\n!\r!\16!"+
		"\u00bb\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u00c9\n#\3$\3$\7$\u00cd\n"+
		"$\f$\16$\u00d0\13$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\7(\u00ed\n(\f(\16(\u00f0\13(\3"+
		"(\3(\3(\3(\3(\3)\6)\u00f8\n)\r)\16)\u00f9\3)\3)\4\u00ce\u00ee\2*\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*\3\2\6\3\2\62;\3\2c|\7\2//\62;C\\aac|\5\2\13\f\17"+
		"\17\"\"\2\u0103\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\3S\3\2\2\2\5Y\3\2\2\2\7[\3\2\2\2\tb\3\2\2\2\13h\3\2\2\2\rj\3\2\2\2"+
		"\17l\3\2\2\2\21n\3\2\2\2\23p\3\2\2\2\25r\3\2\2\2\27t\3\2\2\2\31w\3\2\2"+
		"\2\33|\3\2\2\2\35\u0082\3\2\2\2\37\u0085\3\2\2\2!\u0087\3\2\2\2#\u0089"+
		"\3\2\2\2%\u008b\3\2\2\2\'\u008d\3\2\2\2)\u008f\3\2\2\2+\u0091\3\2\2\2"+
		"-\u0093\3\2\2\2/\u0095\3\2\2\2\61\u0098\3\2\2\2\63\u009b\3\2\2\2\65\u009e"+
		"\3\2\2\2\67\u00a1\3\2\2\29\u00a4\3\2\2\2;\u00a7\3\2\2\2=\u00a9\3\2\2\2"+
		"?\u00b1\3\2\2\2A\u00b9\3\2\2\2C\u00bd\3\2\2\2E\u00c8\3\2\2\2G\u00ca\3"+
		"\2\2\2I\u00d3\3\2\2\2K\u00d8\3\2\2\2M\u00df\3\2\2\2O\u00e8\3\2\2\2Q\u00f7"+
		"\3\2\2\2ST\7&\2\2TU\7x\2\2UV\7q\2\2VW\7k\2\2WX\7f\2\2X\4\3\2\2\2YZ\7."+
		"\2\2Z\6\3\2\2\2[\\\7T\2\2\\]\7g\2\2]^\7v\2\2^_\7w\2\2_`\7t\2\2`a\7p\2"+
		"\2a\b\3\2\2\2bc\7r\2\2cd\7t\2\2de\7k\2\2ef\7p\2\2fg\7v\2\2g\n\3\2\2\2"+
		"hi\7?\2\2i\f\3\2\2\2jk\7}\2\2k\16\3\2\2\2lm\7\177\2\2m\20\3\2\2\2no\7"+
		"*\2\2o\22\3\2\2\2pq\7+\2\2q\24\3\2\2\2rs\7=\2\2s\26\3\2\2\2tu\7k\2\2u"+
		"v\7h\2\2v\30\3\2\2\2wx\7g\2\2xy\7n\2\2yz\7u\2\2z{\7g\2\2{\32\3\2\2\2|"+
		"}\7y\2\2}~\7j\2\2~\177\7k\2\2\177\u0080\7n\2\2\u0080\u0081\7g\2\2\u0081"+
		"\34\3\2\2\2\u0082\u0083\7f\2\2\u0083\u0084\7q\2\2\u0084\36\3\2\2\2\u0085"+
		"\u0086\7-\2\2\u0086 \3\2\2\2\u0087\u0088\7/\2\2\u0088\"\3\2\2\2\u0089"+
		"\u008a\7,\2\2\u008a$\3\2\2\2\u008b\u008c\7\61\2\2\u008c&\3\2\2\2\u008d"+
		"\u008e\7`\2\2\u008e(\3\2\2\2\u008f\u0090\7\'\2\2\u0090*\3\2\2\2\u0091"+
		"\u0092\7>\2\2\u0092,\3\2\2\2\u0093\u0094\7@\2\2\u0094.\3\2\2\2\u0095\u0096"+
		"\7>\2\2\u0096\u0097\7?\2\2\u0097\60\3\2\2\2\u0098\u0099\7@\2\2\u0099\u009a"+
		"\7?\2\2\u009a\62\3\2\2\2\u009b\u009c\7?\2\2\u009c\u009d\7?\2\2\u009d\64"+
		"\3\2\2\2\u009e\u009f\7#\2\2\u009f\u00a0\7?\2\2\u00a0\66\3\2\2\2\u00a1"+
		"\u00a2\7(\2\2\u00a2\u00a3\7(\2\2\u00a38\3\2\2\2\u00a4\u00a5\7~\2\2\u00a5"+
		"\u00a6\7~\2\2\u00a6:\3\2\2\2\u00a7\u00a8\7#\2\2\u00a8<\3\2\2\2\u00a9\u00aa"+
		"\5A!\2\u00aa\u00ae\5C\"\2\u00ab\u00ad\t\2\2\2\u00ac\u00ab\3\2\2\2\u00ad"+
		"\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af>\3\2\2\2"+
		"\u00b0\u00ae\3\2\2\2\u00b1\u00b5\t\3\2\2\u00b2\u00b4\t\4\2\2\u00b3\u00b2"+
		"\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"@\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00ba\t\2\2\2\u00b9\u00b8\3\2\2\2"+
		"\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bcB\3"+
		"\2\2\2\u00bd\u00be\7\60\2\2\u00beD\3\2\2\2\u00bf\u00c0\7V\2\2\u00c0\u00c1"+
		"\7t\2\2\u00c1\u00c2\7w\2\2\u00c2\u00c9\7g\2\2\u00c3\u00c4\7H\2\2\u00c4"+
		"\u00c5\7c\2\2\u00c5\u00c6\7n\2\2\u00c6\u00c7\7u\2\2\u00c7\u00c9\7g\2\2"+
		"\u00c8\u00bf\3\2\2\2\u00c8\u00c3\3\2\2\2\u00c9F\3\2\2\2\u00ca\u00ce\7"+
		"$\2\2\u00cb\u00cd\13\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2"+
		"\2\2\u00d1\u00d2\7$\2\2\u00d2H\3\2\2\2\u00d3\u00d4\7&\2\2\u00d4\u00d5"+
		"\7k\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7v\2\2\u00d7J\3\2\2\2\u00d8\u00d9"+
		"\7&\2\2\u00d9\u00da\7h\2\2\u00da\u00db\7n\2\2\u00db\u00dc\7q\2\2\u00dc"+
		"\u00dd\7c\2\2\u00dd\u00de\7v\2\2\u00deL\3\2\2\2\u00df\u00e0\7&\2\2\u00e0"+
		"\u00e1\7d\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7q\2\2\u00e3\u00e4\7n\2\2"+
		"\u00e4\u00e5\7g\2\2\u00e5\u00e6\7c\2\2\u00e6\u00e7\7p\2\2\u00e7N\3\2\2"+
		"\2\u00e8\u00e9\7\61\2\2\u00e9\u00ea\7`\2\2\u00ea\u00ee\3\2\2\2\u00eb\u00ed"+
		"\13\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ef\3\2\2\2"+
		"\u00ee\u00ec\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2"+
		"\7`\2\2\u00f2\u00f3\7\61\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\b(\2\2\u00f5"+
		"P\3\2\2\2\u00f6\u00f8\t\5\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2"+
		"\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc"+
		"\b)\2\2\u00fcR\3\2\2\2\n\2\u00ae\u00b5\u00bb\u00c8\u00ce\u00ee\u00f9\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}