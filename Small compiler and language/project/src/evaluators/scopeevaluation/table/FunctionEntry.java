package evaluators.scopeevaluation.table;

import java.util.Arrays;

/**
 * FunctionEntry class allows for a single entry of a function definition type.
 *
 * Functions need different entry class to hold their argument types.
 */
public class FunctionEntry extends Entry {
    private int[] arguments;
    FunctionEntry(String identifier, int varType, int[] arguments){
        super(identifier, varType);
        this.arguments = arguments;
    }

    public int[] getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "(FunctionEntry: id;"+getIdentifier()+" type;"+ getVarType() + " arguments;"+ Arrays.toString(arguments)+")";
    }
}
