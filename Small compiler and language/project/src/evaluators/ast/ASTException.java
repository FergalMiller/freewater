package evaluators.ast;

/**
 * ASTException class is used to manage any exceptions found when trying to generate an AST.
 */
public class ASTException extends RuntimeException{
    private String message;
    public ASTException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static ASTException generateException(String what, String where){
        return new ASTException("AST encountered error trying to " + what + " in " + where);
    }
}
