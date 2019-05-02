package evaluators.scopeevaluation.table;

import java.util.ArrayList;
import java.util.List;

/**
 * SymbolLookup encapsulates a single List of Entry values. It offers simple methods
 * for looking up values and inserting values.
 *
 */
public class SymbolLookup {
    private List<Entry> entries;
    private String scopeAddress;
    public SymbolLookup(String scopeAddress){
        entries = new ArrayList<>();
        this.scopeAddress = scopeAddress;
    }

    public void insertNewVariableEntry(String identifier, int type){
        if(entryExistsUnderIdentifier(identifier))
            throw new TableException("Variable already exists in this scope with identifier \""
                    + identifier +"\". Scope: " + scopeAddress);
        else if(entryExistsUnderIdentifier("#"+identifier))
            throw new TableException("Function already exists in this scope with this identifier \""
                    + identifier + "()\". Scope: " +scopeAddress);
        else
            entries.add(new VariableEntry(identifier, type));
    }

    public void insertNewFunctionEntry(String identifier, int varType, int[] arguments){
        if(entryExistsUnderIdentifier(identifier))
            throw new TableException("Function already exists in this scope with this identifier \""
                    + identifier.substring(1) +"()\". Scope: " + scopeAddress);
        else if(entryExistsUnderIdentifier(identifier.substring(1)))
            throw new TableException("Variable with identifier \"" + identifier.substring(1) +
                    "\" already exists within this scope. Scope: " + scopeAddress);
        else
            entries.add(new FunctionEntry(identifier, varType, arguments));
    }

    public boolean entryExistsUnderIdentifier(String identifier){
        for(Entry entry : entries){
            if(identifier.equals(entry.getIdentifier()))
                return true;
        }
        return false;

    }

    public List<Entry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return entries.toString();
    }

}
