// Generated from C:/Users/Fergal/Documents/AAA Uni Work/Third Year/CE305/Assignment 2\whileGrammar.g4 by ANTLR 4.7.2
package generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link whileGrammarParser}.
 */
public interface whileGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(whileGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(whileGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#codeblock}.
	 * @param ctx the parse tree
	 */
	void enterCodeblock(whileGrammarParser.CodeblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#codeblock}.
	 * @param ctx the parse tree
	 */
	void exitCodeblock(whileGrammarParser.CodeblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(whileGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(whileGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(whileGrammarParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(whileGrammarParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(whileGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(whileGrammarParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(whileGrammarParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(whileGrammarParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#pexp}.
	 * @param ctx the parse tree
	 */
	void enterPexp(whileGrammarParser.PexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#pexp}.
	 * @param ctx the parse tree
	 */
	void exitPexp(whileGrammarParser.PexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(whileGrammarParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(whileGrammarParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#decs}.
	 * @param ctx the parse tree
	 */
	void enterDecs(whileGrammarParser.DecsContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#decs}.
	 * @param ctx the parse tree
	 */
	void exitDecs(whileGrammarParser.DecsContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#control}.
	 * @param ctx the parse tree
	 */
	void enterControl(whileGrammarParser.ControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#control}.
	 * @param ctx the parse tree
	 */
	void exitControl(whileGrammarParser.ControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#ifst}.
	 * @param ctx the parse tree
	 */
	void enterIfst(whileGrammarParser.IfstContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#ifst}.
	 * @param ctx the parse tree
	 */
	void exitIfst(whileGrammarParser.IfstContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#then}.
	 * @param ctx the parse tree
	 */
	void enterThen(whileGrammarParser.ThenContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#then}.
	 * @param ctx the parse tree
	 */
	void exitThen(whileGrammarParser.ThenContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#elsest}.
	 * @param ctx the parse tree
	 */
	void enterElsest(whileGrammarParser.ElsestContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#elsest}.
	 * @param ctx the parse tree
	 */
	void exitElsest(whileGrammarParser.ElsestContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#whilest}.
	 * @param ctx the parse tree
	 */
	void enterWhilest(whileGrammarParser.WhilestContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#whilest}.
	 * @param ctx the parse tree
	 */
	void exitWhilest(whileGrammarParser.WhilestContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(whileGrammarParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(whileGrammarParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#paraconditional}.
	 * @param ctx the parse tree
	 */
	void enterParaconditional(whileGrammarParser.ParaconditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#paraconditional}.
	 * @param ctx the parse tree
	 */
	void exitParaconditional(whileGrammarParser.ParaconditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(whileGrammarParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(whileGrammarParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#typefunction}.
	 * @param ctx the parse tree
	 */
	void enterTypefunction(whileGrammarParser.TypefunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#typefunction}.
	 * @param ctx the parse tree
	 */
	void exitTypefunction(whileGrammarParser.TypefunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#voidfunction}.
	 * @param ctx the parse tree
	 */
	void enterVoidfunction(whileGrammarParser.VoidfunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#voidfunction}.
	 * @param ctx the parse tree
	 */
	void exitVoidfunction(whileGrammarParser.VoidfunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#funcdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncdeclaration(whileGrammarParser.FuncdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#funcdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncdeclaration(whileGrammarParser.FuncdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#funcarguments}.
	 * @param ctx the parse tree
	 */
	void enterFuncarguments(whileGrammarParser.FuncargumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#funcarguments}.
	 * @param ctx the parse tree
	 */
	void exitFuncarguments(whileGrammarParser.FuncargumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#rtrn}.
	 * @param ctx the parse tree
	 */
	void enterRtrn(whileGrammarParser.RtrnContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#rtrn}.
	 * @param ctx the parse tree
	 */
	void exitRtrn(whileGrammarParser.RtrnContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#functioncall}.
	 * @param ctx the parse tree
	 */
	void enterFunctioncall(whileGrammarParser.FunctioncallContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#functioncall}.
	 * @param ctx the parse tree
	 */
	void exitFunctioncall(whileGrammarParser.FunctioncallContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(whileGrammarParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(whileGrammarParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link whileGrammarParser#printcall}.
	 * @param ctx the parse tree
	 */
	void enterPrintcall(whileGrammarParser.PrintcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link whileGrammarParser#printcall}.
	 * @param ctx the parse tree
	 */
	void exitPrintcall(whileGrammarParser.PrintcallContext ctx);
}