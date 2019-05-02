package evaluators.scopeevaluation.table;

/**
 * TableException class is used to manage any exceptions found when looking for
 * identifiers in a SymbolLookup
 */
public class TableException extends RuntimeException {
    private String message;
    public TableException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
