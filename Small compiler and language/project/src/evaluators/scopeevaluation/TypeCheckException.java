package evaluators.scopeevaluation;

import static evaluators.scopeevaluation.table.Entry.*;

/**
 * TypeCheckException manages exceptions found when checking static type.
 *
 * Offers many static methods to generate exceptions for generic type exceptions.
 */
public class TypeCheckException extends RuntimeException{
    private String message;
    public TypeCheckException(String message){
        super(message);
        this.message = message;
    }

    public static TypeCheckException generateClassMismatch(String operator, int clazz){
        return new TypeCheckException("Class mismatch. Attempting to use operator \"" +
                operator + "\" on type " + classString(clazz) + ".");
    }

    public static TypeCheckException generateAssignmentClassMismatch(String identifier, int expected, int actual){
        return new TypeCheckException("Attempting to assign identifier \"" +
                identifier + "\" a value of type " + classString(actual) + ". " +
                classString(expected) + " type expected.");
    }

    public static TypeCheckException generateArgumentMismatch(int argument, int expected, int actual){
        return new TypeCheckException("Argument "+ argument +" wrong type. Type " +
                classString(expected) + " expected, " + classString(actual) + " given.");
    }

    public static TypeCheckException generateReturnMismatch(int expected, int actual){
        return new TypeCheckException("Return type for function does not match signature. " +
                classString(expected) + " expected, " + classString(actual) + " found.");
    }

    //Converts integer class value to String
    private static String classString(int clazz){
        switch (clazz) {
            case TYPE_INT : return "int";
            case TYPE_FLOAT : return "float";
            case TYPE_BOOLEAN : return "boolean";
            case TYPE_STRING : return "string";
            case TYPE_VOID : return "void";
        }
        throw new RuntimeException("No such class found when generating string!");
    }

    private static String begin(int lineNum){
        return "Line number " + lineNum + ": ";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
