package evaluators.scopeevaluation.table;

/**
 * Entry class is abstract.
 * Holds the necessary information for a single extended Entry class.
 */
public abstract class Entry {
    //Static values to identify types from external classes using Entry classes
    public static final int TYPE_INT = 1, TYPE_FLOAT = 2, TYPE_BOOLEAN = 3, TYPE_STRING = 4, TYPE_VOID = 5;
    private String identifier;
    private int varType;
    Entry(String identifier, int varType){
        this.identifier = identifier;
        this.varType = varType;
    }

    public int getVarType() {
        return varType;
    }

    public String getIdentifier(){
        return identifier;
    }
}
