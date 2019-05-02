package evaluators.scopeevaluation.table;

/**
 * VariableEntry class is standard entry class for a standard variable.
 */
public class VariableEntry extends Entry {

    VariableEntry(String identifier, int varType){
        super(identifier, varType);
    }

    @Override
    public String toString() {
        return "(VariableEntry: id;"+getIdentifier()+" type;"+getVarType()+")";
    }
}
