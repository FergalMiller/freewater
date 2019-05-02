package evaluators;

import evaluators.ast.AST;
import evaluators.ast.ASTException;
import evaluators.scopeevaluation.ScopeTree;
import evaluators.scopeevaluation.TypeCheckException;
import generated.whileGrammarParser.*;

import static evaluators.scopeevaluation.table.Entry.*;

/**
 * ASTManager class offers complete management of an AST object.
 *
 * ASTManager offers 3 main management tools:
 * > Creation of an AST object using a ProgramContext object generated from ANTLR
 * > Static type checking of the AST
 * > Generating a string output of the target language (Python)
 *
 * ASTManager offers 3 subclasses for each:
 * > ASTBuilder
 * > TypeChecking
 * > PythonCodeGenerator
 *
 */

public class ASTManager {
    private AST root;
    public ASTManager(AST tree){
        this.root = tree;
    }

    public static AST buildAST(ProgramContext program){
        return new ASTBuilder(program).build();
    }

    public AST getTree() {
        return root;
    }

    //Generates String object containing python code text
    public String generatePythonCode(){
        return new PythonCodeGenerator().generate();
    }

    //Check type of AST
    public void checkType(){
        new TypeChecking().check();
    }

    //Helper method used by subclasses to find if a value is in the
    private final static String[] NUMOPS = {"+","-","/","*", "%", "^"};
    private final static String[] LOGICOPS = {"!", "<", "<=", ">", ">=","==", "!=", "&&", "||"};
    private boolean arrayContains(String[] array, String contains){
        for(String s : array){
            if(s.equals(contains))
                return true;
        }
        return false;
    }

    /*
    Sub-classes
     */

    /**
     * Checks static typing by traversing each node of the AST of the parent class
     * and managing symbol lookups through a ScopeTree object.
     */
    private class TypeChecking{
        private ScopeTree programScope;
        private void check(){
            programScope = new ScopeTree(null, "PROGRAM", 0);
            traverseNode(root, programScope);
        }

        private void traverseNode(AST node, ScopeTree scope){
            String nodeValue = node.getValue().toString();
            switch(nodeValue){
                case "PROGRAM" :
                    for(AST child : node.getChildren()){
                        traverseNode(child, scope);
                    }
                    break;

                case "$" :
                    checkDeclaration(node, scope);
                    break;

                case "=" :
                    checkAssignment(node, scope);
                    break;

                case "function" :
                    ScopeTree funcScope = scope.newScope(node.getChildren()[0].getChildren()[1].getValue().toString());
                    checkFuncDec(node.getChildren()[0], scope, funcScope);
                    traverseNode(node.getChildren()[1], funcScope);
                    break;

                case "call" :
                    checkFuncCall(node, scope);
                    break;

                case "if" :
                case "while" :
                case "print" :
                    ScopeTree blockScope = scope.newScope(nodeValue);
                    checkPrintcall(node, blockScope);
                    for(AST child : node.getChildren()){
                        traverseNode(child, blockScope);
                    }
                    break;
                case "then" :
                case "else" :
                case "do" :
                    //Checks all types within the scope
                    for(AST child : node.getChildren()){
                        traverseNode(child, scope);
                    }
                    break;

                case "return" :
                    checkReturn(node, scope);
                    break;
            }
        }

        private void checkReturn(AST returnNode, ScopeTree scope){
            boolean inScope = false;
            String identifierOfFunction = "";

            //Temporary scope object created to find scope of the function encapsulating the return statement
            ScopeTree tempScope = scope;
            //Incrementally increases scope range until it finds the scope of the function. Gets type of function.
            while(!inScope) {
                identifierOfFunction = tempScope.getCodeblockType();
                if(identifierOfFunction.charAt(0) == '#')
                    inScope = true;

                tempScope = tempScope.getParent();
                if(tempScope == null)
                    throw new TypeCheckException("Return statement found outside of a function body.");
            }

            //Ensures that function type is the same as returned value
            int expectedType = scope.getTypeFromIdentifier(identifierOfFunction);
            int type = getVarType(returnNode.getChildren()[0], scope);
            if(expectedType != type)
                if(!(expectedType==TYPE_FLOAT && type==TYPE_INT))
                    throw TypeCheckException.generateReturnMismatch(expectedType, type);
        }

        private void checkFuncCall(AST funcCall, ScopeTree scope){
            String identifier = funcCall.getChildren()[0].getValue().toString();
            if(!scope.identifierExistsInThisScope(identifier))
                throw new TypeCheckException("No function exists in this scope with identifier " + identifier);
            else{
                int[] argsExpected = scope.getArgumentsFromFunction(identifier);
                AST argsNode = funcCall.getChildren()[1];
                int numOfArgsGiven;
                if(argsNode.isLeaf())
                    numOfArgsGiven = 0;
                else
                    numOfArgsGiven = argsNode.getChildren().length;

                if(argsExpected.length != numOfArgsGiven)
                    throw new TypeCheckException("Number of arguments supplied do not match function signature. " +
                            numOfArgsGiven + " arguments given, " + argsExpected.length + " arguments expected.");

                if(numOfArgsGiven > 0) {
                    int[] argsGiven = new int[numOfArgsGiven];
                    int i = 0;
                    for (AST child : argsNode.getChildren()) {
                        argsGiven[i] = getVarType(child, scope);
                        i++;
                    }
                    for (i = 0; i < numOfArgsGiven; i++) {
                        if (argsGiven[i] != argsExpected[i])
                            if (!(argsGiven[i] == TYPE_INT && argsExpected[i] == TYPE_FLOAT))
                                throw TypeCheckException.generateArgumentMismatch(i + 1, argsExpected[i], argsGiven[i]);
                    }
                }

            }


        }

        private void checkFuncDec(AST funcDec, ScopeTree scope, ScopeTree functionScope){
            int type;
            String typeAsString = funcDec.getChildren()[0].getValue().toString();
            switch (typeAsString){
                case "int" : type = TYPE_INT; break;
                case "float" : type = TYPE_FLOAT; break;
                case "boolean" : type = TYPE_BOOLEAN; break;
                case "void" : type = TYPE_VOID; break;
                default: throw new TypeCheckException("No type found for " + typeAsString);
            }
            String identifier = funcDec.getChildren()[1].getValue().toString();
            int[] args;
            if(funcDec.getChildren().length>2){
                AST argNode = funcDec.getChildren()[2];
                args = new int[argNode.getChildren().length];
                int i = 0;
                for(AST decs : argNode.getChildren()){
                    int argType;
                    String decTypeAsString = decs.getChildren()[0].getValue().toString();
                    switch (decTypeAsString){
                        case "int" : argType = TYPE_INT; break;
                        case "float" : argType = TYPE_FLOAT; break;
                        case "boolean" : argType = TYPE_BOOLEAN; break;
                        default: throw new TypeCheckException("No type found for " + decTypeAsString);
                    }
                    functionScope.insertVariableEntry(decs.getChildren()[1].getValue().toString(), argType);
                    args[i] = argType;
                    i++;
                }
                scope.insertFunctionEntry(identifier, type, args);
            }
            else{
                args = new int[0];
                scope.insertFunctionEntry(identifier, type, args);
            }
        }

        //Checks a bracketed expression / condition / string
        private void checkPrintcall(AST printNode, ScopeTree scope){
            //Uses getVarType to type check the argument
            getVarType(printNode.getChildren()[0], scope);
        }

        private void checkAssignment(AST assignmentNode, ScopeTree scope){
            String identifier = assignmentNode.getChildren()[0].getValue().toString();
            int actual = getVarType(assignmentNode.getChildren()[1], scope);
            int expected = scope.getTypeFromIdentifier(identifier);
            if(expected == TYPE_FLOAT && actual == TYPE_INT)
                return;
            if(expected != actual)
                throw TypeCheckException.generateAssignmentClassMismatch(identifier, expected, actual);
        }

        private void checkDeclaration(AST declarationNode, ScopeTree scope){
            AST typeNode = declarationNode.getChildren()[0];
            int type;
            switch (typeNode.getValue().toString()){
                case "int" : type = TYPE_INT; break;
                case "float" : type = TYPE_FLOAT; break;
                case "boolean" : type = TYPE_BOOLEAN; break;
                default: throw new TypeCheckException("No type found for " + typeNode.getValue());
            }
            AST right = declarationNode.getChildren()[1];
            //Simple declaration
            if(right.isLeaf())
                scope.insertVariableEntry(right.getValue().toString(), type);
            //Declaration with assignment
            else{
                AST identifier = right.getChildren()[0];
                int valueType = getVarType(right.getChildren()[1], scope);
                if(valueType != type) {
                    //Does allow integer values to be loaded into Float types (not vice versa)
                    if(!(type == TYPE_FLOAT && valueType==TYPE_INT))
                        throw TypeCheckException.generateAssignmentClassMismatch(identifier.getValue().toString(),
                                type, valueType);
                }
                scope.insertVariableEntry(identifier.getValue().toString(),
                        type);
            }
        }

        private int getVarType(AST node, ScopeTree scope){
            String strValue = node.getValue().toString();
            //Node is logic operator
            if(arrayContains(LOGICOPS, strValue)){
                if(strValue.equals("!")){
                    int childType = getVarType(node.getChildren()[0], scope);
                    if(childType == TYPE_BOOLEAN)
                        return TYPE_BOOLEAN;
                    else
                        throw TypeCheckException.generateClassMismatch(strValue, childType);
                }
                else{
                    //Checks that both operands of the operator are Boolean value
                    AST left = node.getChildren()[0], right = node.getChildren()[1];
                    int leftType = getVarType(left, scope);
                    int rightType = getVarType(right, scope);

                    switch(strValue){
                        case "&&" :
                        case "||" :
                            if(leftType != TYPE_BOOLEAN)
                                throw TypeCheckException.generateClassMismatch(strValue, leftType);
                            if(rightType != TYPE_BOOLEAN)
                                throw TypeCheckException.generateClassMismatch(strValue, rightType);
                            return TYPE_BOOLEAN;
                        case ">" :
                        case ">=" :
                        case "<" :
                        case "<=" :
                            if(leftType == TYPE_BOOLEAN)
                                throw TypeCheckException.generateClassMismatch(strValue, leftType);
                            if(rightType == TYPE_BOOLEAN)
                                throw TypeCheckException.generateClassMismatch(strValue, rightType);
                            return TYPE_BOOLEAN;
                        case "==" :
                        case "!=" :
                            if(leftType == TYPE_BOOLEAN && rightType == TYPE_BOOLEAN)
                                return TYPE_BOOLEAN;
                            if(leftType == TYPE_BOOLEAN || rightType == TYPE_BOOLEAN)
                                throw new TypeCheckException("Attempting to use operator " + strValue +
                                    " on types " + leftType + " and " + rightType);
                            return TYPE_BOOLEAN;
                    }
                    return TYPE_BOOLEAN;
                }
            }
            //Node is a numeric operator
            else if(arrayContains(NUMOPS, strValue)){
                int leftType = getVarType(node.getChildren()[0], scope);
                int rightType = getVarType(node.getChildren()[1], scope);

                if(leftType == TYPE_BOOLEAN)
                    throw TypeCheckException.generateClassMismatch(strValue, leftType);
                if(rightType == TYPE_BOOLEAN)
                    throw TypeCheckException.generateClassMismatch(strValue, rightType);

                //If either of the operands are float the end value is always float. Division always returns Float.
                if(leftType == TYPE_FLOAT || rightType == TYPE_FLOAT || strValue.equals("/"))
                    return TYPE_FLOAT;
                if(leftType != TYPE_INT)
                    throw TypeCheckException.generateClassMismatch(strValue, leftType);
                if(rightType != TYPE_INT)
                    throw TypeCheckException.generateClassMismatch(strValue, rightType);
                return TYPE_INT;
            }
            else if(strValue.equals("call")){
                return scope.getTypeFromIdentifier(node.getChildren()[0].getValue().toString());
            }
            else if(strValue.equals("True") || strValue.equals("False"))
                return TYPE_BOOLEAN;
            else if(strValue.contains("."))
                return TYPE_FLOAT;
            else if(strValue.contains("\""))
                return TYPE_STRING;
            else if(Character.isDigit(strValue.charAt(0)) || strValue.charAt(0) == '-')
                return TYPE_INT;
            //Must be identifier. Checks scope for matching ID
            else
                //If no identifier found then exception is thrown up the chain
                return scope.getTypeFromIdentifier(strValue);
        }

    }

    /**
     * Generates python code from the AST object of the parent class
     *
     * Traverses nodes and generates python formatted code to represent the AST.
     *
     * Uses StringBuilder field to append formatted code to final StringBuilder object.
     */
    private class PythonCodeGenerator{
        private StringBuilder pythonBuilder;
        private int tabbing, line;
        private String generate(){
            pythonBuilder = new StringBuilder();
            tabbing = 0; line = 0;
            traverseNode(root);
            newLine();
            return pythonBuilder.toString();
        }

        private void traverseNode(AST node){
            String value = node.getValue().toString();

            switch (value){
                case "PROGRAM":
                case "then" :
                case "else" :
                case "do" :
                    for(AST child : node.getChildren())
                        traverseNode(child);
                    break;
                case "$" :
                    processDeclaration(node);
                    break;
                case "=" :
                    processAssignment(node);
                    break;
                case "if" :
                    processIf(node);
                    break;
                case "while" :
                    processWhile(node);
                    break;
                case "print" :
                    processPrintcall(node);
                    break;
                case "function" :
                    processFunction(node);
                    newLine();newLine();
                    break;
                case "call" :
                    newLine();
                    append(generateFunctionCallString(node));
                    break;
                case "return" :
                    processReturn(node);
                    break;

                default :
                    if(arrayContains(NUMOPS, node.getValue().toString()))
                        System.out.println("Found operator");
            }
        }

        private void processReturn(AST returnNode){
            newLine();
            append("return ");
            AST child = returnNode.getChildren()[0];
            String childValue = child.getValue().toString();
            if(arrayContains(LOGICOPS, childValue))
                append(generateConditionalString(child));
            else
                append(generateExpressionString(child));

        }

        //Generates a String object for a function call node and all its children
        private String generateFunctionCallString(AST funcCallNode){
            StringBuilder functionBuilder = new StringBuilder();
            functionBuilder.append(funcCallNode.getChildren()[0].getValue().toString().substring(1));
            functionBuilder.append("(");
            int i =0;
            if(!funcCallNode.getChildren()[1].isLeaf()) {
                for (AST arg : funcCallNode.getChildren()[1].getChildren()) {
                    if (i != 0)
                        functionBuilder.append(", ");
                    String value = arg.getValue().toString();
                    if (arrayContains(LOGICOPS, value))
                        functionBuilder.append(generateConditionalString(arg));
                    else
                        functionBuilder.append(generateExpressionString(arg));
                    i++;
                }
            }
            functionBuilder.append(")");
            return functionBuilder.toString();
        }

        private void processFunction(AST functionNode){
            newLine();
            String id = functionNode.getChildren()[0].getChildren()[1].getValue().toString().substring(1);
            append("def " + id + "(");
            if(functionNode.getChildren()[0].getChildren().length > 2) {
                AST argNode = functionNode.getChildren()[0].getChildren()[2];
                int i = 0;
                for(AST child : argNode.getChildren()){
                    if(i != 0)
                        append(", ");
                    append(child.getChildren()[1].getValue().toString());
                    i++;
                }
            }
            append("):");
            tabbing++;
            traverseNode(functionNode.getChildren()[1]);
            tabbing--;
        }

        private void processPrintcall(AST printNode){
            newLine();
            append("print(");
            AST child = printNode.getChildren()[0];
            if(child.isLeaf())
                append(child.getValue().toString() + ")");
            else{
                String childValue = child.getValue().toString();
                if(arrayContains(NUMOPS, childValue))
                    append(generateExpressionString(child) + ")");
                else if(childValue.equals("call"))
                    append(generateFunctionCallString(child) + ")");
                else
                    append(generateConditionalString(child) + ")");
            }
        }

        private void processDeclaration(AST decNode){
            AST value = decNode.getChildren()[1];
            if(value.getValue().equals("="))
                processAssignment(value);
        }

        private void processWhile(AST whileNode){
            newLine();
            append("while ");
            append(generateConditionalString(whileNode.getChildren()[0]));
            append(":");
            tabbing++;
            traverseNode(whileNode.getChildren()[1]);
            tabbing--;
        }

        private void processIf(AST ifNode){
            newLine();
            append("if ");
            append(generateConditionalString(ifNode.getChildren()[0]));
            append(":");
            tabbing++;
            traverseNode(ifNode.getChildren()[1]);
            tabbing--;
            if(ifNode.getChildren().length > 2) {
                newLine();
                append("else:");
                tabbing++;
                traverseNode(ifNode.getChildren()[2]);
                tabbing--;
            }
        }

        //Generates a String object for a conditional node and all its children
        private String generateConditionalString(AST conditionalNode){
            String condValue = conditionalNode.getValue().toString();

            switch(condValue){
                case "!" :
                    AST child = conditionalNode.getChildren()[0];
                    if(child.isLeaf())
                        return "not " + generateConditionalString(child);
                    else
                        return "not (" + generateConditionalString(child) + ")";
                case "&&" :
                case "||" :
                case "==" :
                case "!=" :
                    StringBuilder conditionalBuilder = new StringBuilder();
                    final int precedence = operatorPrecedence(condValue);
                    AST left = conditionalNode.getChildren()[0], right = conditionalNode.getChildren()[1];

                    if(arrayContains(NUMOPS, left.getValue().toString()))
                        conditionalBuilder.append(generateExpressionString(left));
                    else
                        conditionalBuilder.append(generateConditionalChildString(left, precedence));

                    //Correctly translates value of operator for python interpretation if needed
                    conditionalBuilder.append(" ");
                    conditionalBuilder.append(
                            (condValue.equals("&&") || condValue.equals("||") ?
                                    (condValue.equals("&&") ? " and " : " or ") :
                                    condValue)
                    );
                    conditionalBuilder.append(" ");

                    if(arrayContains(NUMOPS, right.getValue().toString()))
                        conditionalBuilder.append(generateExpressionString(right));
                    else
                        conditionalBuilder.append(generateConditionalChildString(right, precedence));

                    return conditionalBuilder.toString();
                case ">" :
                case ">=" :
                case "<" :
                case "<=" :
                    return generateExpressionString(conditionalNode.getChildren()[0]) +
                            " " + condValue + " " +
                            generateExpressionString(conditionalNode.getChildren()[1]);
                case "call" :
                    return generateFunctionCallString(conditionalNode);
                default:
                    return condValue;
            }

        }

        //Helper for generateConditionalString
        private String generateConditionalChildString(AST child, int parentPrecedence){
            //Ensures bracketing is correct in line with operator precedence
            if(child.isLeaf())
                return generateConditionalString(child);
            else{
                if(operatorPrecedence(child.getValue().toString()) < parentPrecedence){
                    return "(" +
                            generateConditionalString(child) +
                            ")";
                }
                else
                    return generateConditionalString(child);
            }
        }

        private void processAssignment(AST node){
            newLine();
            append(node.getChildren()[0].getValue() + " = ");
            AST child = node.getChildren()[1];
            if(arrayContains(LOGICOPS, child.getValue().toString()))
                    append(generateConditionalString(child));
            else
                append(generateExpressionString(child));
        }

        //Generates a String object for an expression node and all its children
        private String generateExpressionString(AST expNode){
            if(expNode.isLeaf())
                return expNode.getValue().toString();
            else if(expNode.getValue().toString().equals("call")){
                return generateFunctionCallString(expNode);
            }
                //Traverse children of operator node
            else {

                StringBuilder expBuilder = new StringBuilder();
                AST left = expNode.getChildren()[0];
                AST right = expNode.getChildren()[1];
                //Checks if child is leaf
                if(left.isLeaf())
                    expBuilder.append(left.getValue().toString());
                else{
                    //Append brackets to correctly show order of operations depending on structure
                    boolean precedenceHigher = (operatorPrecedence(expNode.getValue().toString())
                            > operatorPrecedence(left.getValue().toString()));

                    if (precedenceHigher)
                        expBuilder.append("(");
                    expBuilder.append(generateExpressionString(left));
                    if(precedenceHigher)
                        expBuilder.append(")");
                }

                //Append operator value, accounts for source language exponent operator translation to Python exponent operator
                if(expNode.getValue().toString().equals("^"))
                    expBuilder.append("**");
                else
                    expBuilder.append(expNode.getValue().toString());

                if(right.isLeaf())
                    expBuilder.append(right.getValue().toString());
                else {

                    boolean precedenceHigher = operatorPrecedence(expNode.getValue().toString())
                            > operatorPrecedence(right.getValue().toString());

                    if (precedenceHigher)
                        expBuilder.append("(");
                    expBuilder.append(generateExpressionString(right));
                    if (precedenceHigher)
                        expBuilder.append(")");
                }
                return expBuilder.toString();
            }
        }

        private void append(String string){
            pythonBuilder.append(string);
        }

        //Creates new line for StringBuilder while ensuring the correct tabbing is kept.
        private void newLine(){
            line++;
            if(line > 1) {
                pythonBuilder.append('\n');
                for (int i = 0; i < tabbing; i++)
                    pythonBuilder.append('\t');
            }
        }

        //Returns precedence of an operator
        private int operatorPrecedence(String op){
            if(op.equals("call"))
                return 5;
            if(arrayContains(NUMOPS, op)){
                if(op.equals(NUMOPS[0]) || op.equals(NUMOPS[1]))
                    return 1;
                if(op.equals(NUMOPS[5]))
                    return 3;
                return 2;
            }
            else if(arrayContains(LOGICOPS, op)) {
                if(op.equals(LOGICOPS[0]))
                    return 3;
                if(op.equals(LOGICOPS[5]) || op.equals(LOGICOPS[6]))
                    return 2;
                else if(op.equals(LOGICOPS[7])|| op.equals(LOGICOPS[8]))
                    return 0;
                else
                    return 1;
            }
            else
                throw new RuntimeException("No operator found for " + op);
        }
    }

    /**
     * Builds an AST from an ANTLR ProgramContext object
     *
     * Traverses necessary children and recursively passes AST objects up the method call chain
     * to the build() method.
     */
    private static class ASTBuilder {
        private ProgramContext program;

        private ASTBuilder(ProgramContext program){
            this.program = program;
        }

        private AST build(){
            return new AST("PROGRAM", parseChildren(program));
        }

        private AST[] parseChildren(ProgramContext context){
            return parseCodeBlock(context.codeblock());
        }

        private AST[] parseCodeBlock(CodeblockContext context){
            int statementNum = context.statement().size();
            if(statementNum == 0)
                throw new ASTException("Statement block must have at least one statement. Cannot build AST.");
            AST[] asts = new AST[statementNum];
            int i = 0;
            for(StatementContext statement : context.statement()){
                asts[i] = parseStatement(statement);
                i++;
            }
            return asts;
        }

        private AST parseStatement(StatementContext codeblock){
            if(codeblock.declaration() != null)
                return parseDeclaration(codeblock.declaration());

            else if(codeblock.assignment() != null)
                return parseAssignment(codeblock.assignment());

            else if(codeblock.printcall() != null)
                return parsePrintcall(codeblock.printcall());

            else if(codeblock.control() != null)
                return parseControl(codeblock.control());

            else if(codeblock.function() != null)
                return parseFunction(codeblock.function());

            else if(codeblock.functioncall() != null)
                return parseFunctioncall(codeblock.functioncall());

            else if(codeblock.rtrn() != null)
                return parseReturn(codeblock.rtrn());

            throw new ASTException("Error trying to parse codeblock, no relevant child type available.");
        }

        private AST parseFunctioncall(FunctioncallContext funcCall){
            if(funcCall.arguments()!=null){
                AST[] args = parseFuncCallArgs(funcCall.arguments());
                return new AST("call",
                        new AST("#"+funcCall.ID().getText()),
                        new AST("args", args));
            }
            else{
                return new AST("call",
                        new AST("#"+funcCall.ID().getText()));
            }

        }

        private AST[] parseFuncCallArgs(ArgumentsContext argument){
            if(argument.getChildCount() > 0) {
                if (argument.exp() != null) {
                    return new AST[]{parseExpression(argument.exp())};
                } else if (argument.conditional() != null) {
                    return new AST[]{parseConditional(argument.conditional())};
                } else {
                    return appendASTArray(parseFuncCallArgs(argument.arguments(0)),
                            parseFuncCallArgs(argument.arguments(1)));

                }
            }
            else
                return null;
        }

        private AST parseFunction(FunctionContext function){
            if(function.typefunction() != null){
                if(function.typefunction().RBR() == null)
                    throw new ASTException("No closing bracket for function body");
                AST[] body = parseCodeBlock(function.typefunction().codeblock());
                AST declaration = parseFuncDeclaration(function.typefunction().funcdeclaration());
                return new AST("function",
                        declaration,
                        new AST("do", body));
            }
            else if(function.voidfunction() != null){
                if(function.voidfunction().RBR() == null)
                    throw new ASTException("No closing bracket for function body");
                AST[] body = parseCodeBlock(function.voidfunction().codeblock());
                AST declaration;
                if(function.voidfunction().funcarguments().declaration()!=null) {
                     declaration = new AST("$",
                            new AST(function.voidfunction().getChild(0).getText().substring(1)),
                            new AST("#" + function.voidfunction().getChild(1).getText()),
                            new AST("args", parseArguments(function.voidfunction().funcarguments()))
                     );
                }
                else{
                    declaration = new AST("$",
                            new AST(function.voidfunction().getChild(0).getText().substring(1)),
                            new AST("#" + function.voidfunction().getChild(1).getText())
                    );
                }
                return new AST("function",
                        declaration,
                        new AST("do", body));
            }
            throw new ASTException("Could not create function AST");
        }

        private AST parseFuncDeclaration(FuncdeclarationContext funcDec){
            if(funcDec.funcarguments().getChildCount() > 0){
                return new AST("$",
                        new AST(funcDec.declaration().decs().getChild(0).getText().substring(1)),
                        new AST("#"+funcDec.declaration().ID().getText()),
                        new AST("args", parseArguments(funcDec.funcarguments()))
                );
            }
            else{
                return new AST("$",
                        new AST(funcDec.declaration().decs().getChild(0).getText().substring(1)),
                        new AST("#"+funcDec.declaration().ID().getText())
                );
            }

        }

        private AST[] parseArguments(FuncargumentsContext args){
            AST[] arguments = new AST[args.declaration().size()];
            int i = 0;
            for(DeclarationContext decs : args.declaration()){
                arguments[i] = parseDeclaration(decs);
                i++;
            }
            return arguments;
        }

        private AST parseReturn(RtrnContext rtrn){
            if(rtrn.exp()!=null)
                return new AST("return", parseExpression(rtrn.exp()));
            else if(rtrn.conditional()!=null)
                return new AST("return", parseConditional(rtrn.conditional()));
            throw new ASTException("Could not parse return tree value");
        }

        private AST parsePrintcall(PrintcallContext print){
            if(print.conditional()!=null)
                return new AST("print",
                        parseConditional(print.conditional()));
            else if(print.exp() != null)
                return new AST("print",
                        parseExpression(print.exp()));
            else if(print.STRING() != null)
                return new AST("print",
                        new AST(print.STRING()));
            throw new ASTException("No valid printcall parse");
        }

        private AST parseDeclaration(DeclarationContext declaration){
            if(declaration.assignment() != null){
                return new AST("$",
                        new AST(declaration.decs().getText().substring(1)),
                        parseAssignment(declaration.assignment()));
            }
            else if(declaration.ID() != null){
                return new AST("$",
                        new AST(declaration.decs().getText().substring(1)),
                        new AST(declaration.ID().getText()));
            }
            throw new ASTException("Improper declaration found when creating AST");
        }

        private AST parseControl(ControlContext control){
            if(control.ifst() != null){
                return new AST("if", parseIf(control.ifst()));
            }
            else if(control.whilest() != null){
                return new AST("while", parseWhile(control.whilest()));
            }
            throw new RuntimeException("No control matching");
        }

        private AST[] parseIf(IfstContext ifst){
            AST[] asts;
            if(ifst.elsest() != null){
                asts = new AST[3];
                asts[2] = new AST("else", parseCodeBlock(ifst.elsest().codeblock()));
            }
            else
                asts = new AST[2];
            asts[1] = new AST("then", parseCodeBlock(ifst.then().codeblock()));
            asts[0] = parseConditional(ifst.conditional());
            return asts;
        }

        private AST[] parseWhile(WhilestContext whilest){
            AST[] asts = new AST[2];
            asts[0] = parseConditional(whilest.conditional());
            asts[1] = new AST("do", parseCodeBlock(whilest.codeblock()));
            return asts;
        }

        private AST parseConditional(ConditionalContext conditional){
            //Not
            if(conditional.NOT() != null)
                return new AST("!", parseConditional(conditional.conditional(0)));

            else if(conditional.paraconditional()!=null)
                return parseConditional(conditional.paraconditional().conditional());

            else if(conditional.ID() != null)
                return new AST(conditional.ID().getText());

            else if(conditional.BOOL() != null)
                return new AST(conditional.BOOL().getText());

            //Compound conditional
            else if(conditional.conditional().size() > 1){
                return new AST(conditional.getChild(1).getText(),
                        parseConditional(conditional.conditional(0)),
                        parseConditional(conditional.conditional(1)));
            }

            else if(conditional.functioncall() != null){
                return parseFunctioncall(conditional.functioncall());
            }

            else if(conditional.exp() != null)
                try {
                    return new AST(conditional.getChild(1).getText(),
                            parseExpression(conditional.exp().get(0)),
                            parseExpression(conditional.exp().get(1)));
                }
                catch(NullPointerException e){
                    throw new ASTException("Conditional statement failed to parse. Not enough expressions");
                }

            throw new ASTException("No parse able for conditional");
        }

        private AST parseAssignment(AssignmentContext assignment){
            if(assignment.exp()!=null)
                return new AST("=", new AST(assignment.getChild(0).getText()), parseExpression(assignment.exp()));
            else if(assignment.conditional() != null)
                return new AST("=", new AST(assignment.getChild(0).getText()),
                        parseConditional(assignment.conditional()));
            else
                throw new ASTException("Unable to parse assignment tree");
        }

        private AST parseExpression(ExpContext expression){
            if(expression.operand() != null){
                return new AST(generateOperandString(expression.operand()));
            }
            else if(expression.pexp() != null)
                return parseExpression(expression.pexp().exp());
            else if(expression.functioncall() != null)
                return parseFunctioncall(expression.functioncall());
            else
                return new AST(expression.getChild(1).getText(),
                        parseExpression(expression.exp().get(0)),
                        parseExpression(expression.exp().get(1)));
        }

        private String generateOperandString(OperandContext operand){

            if(operand.SUB() != null)
                return operand.SUB().getText() + operand.getChild(1).getText();
            else
                return operand.getChild(0).getText();
        }

        //Helper method to append two arrays of AST objects into a single non-sorted array
        private AST[] appendASTArray(AST[] arr1, AST[] arr2){
            int len = arr1.length + arr2.length;
            AST[] temp = new AST[len];
            int i = 0;
            for(AST element : arr1){
                temp[i] = element; i++;
            }
            for(AST element : arr2){
                temp[i] = element; i++;
            }
            return temp;
        }
    }

}
