package evaluators.scopeevaluation;

import evaluators.scopeevaluation.table.Entry;
import evaluators.scopeevaluation.table.FunctionEntry;
import evaluators.scopeevaluation.table.SymbolLookup;
import evaluators.scopeevaluation.table.TableException;

import java.util.ArrayList;
import java.util.List;

/**
 * ScopeTree encapsulates the scope of a single statement block. Whether this is
 * a function, an if statement body or while statement.
 *
 * ScopeTree holds its own SymbolLookup object to manage values within its own scope.
 * ScopeTree also allows for child ScopeTree objects. These branches represent a nested
 * scope.
 *
 * ScopeTree objects can get all values of their scope by referencing the parent node's
 * SymbolLookup.
 */
public class ScopeTree {
    private ScopeTree parent;
    private SymbolLookup table;
    private List<ScopeTree> children;
    private String codeblockType;
    private int childNumOfParent;
    private int numOfChildren;
    public ScopeTree(ScopeTree parent, String codeblockType, int childNum){
        this.parent = parent;
        this.codeblockType = codeblockType;
        this.childNumOfParent = childNum;
        children = new ArrayList<>();
        numOfChildren = 0;
        table = new SymbolLookup(getScopeAddress());
    }

    //Checks that an identifier exists within the scope
    public boolean identifierExistsInThisScope(String identifier){
        boolean found = table.entryExistsUnderIdentifier(identifier);
        //If function id check for variables, if variable check for function id
        if(!found){
            if(identifier.charAt(0) == '#') {
                found = table.entryExistsUnderIdentifier(identifier.substring(1));
                if(found)
                    throw new TableException("Cannot declare function " + identifier.substring(1) +
                            "(). Variable already exists under this identifier visible to this scope.");
            }
            else {
                found = table.entryExistsUnderIdentifier("#" + identifier);
                if(found)
                    throw new TableException("Cannot declare variable " + identifier +
                            ". Function already exists under this identifier visible to this scope.");
            }
        }
        if(found)
            return true;
        else {
            if(parent!=null)
                return parent.identifierExistsInThisScope(identifier);
        }
        return false;
    }

    //Inserts a single variable entry
    public void insertVariableEntry(String identifier, int clazz){
        try {
            if (identifierExistsInThisScope(identifier))
                throw new TableException("Identifier \"" + identifier + "\" already visible within this scope. " +
                        "Cannot be re-declared.");
            table.insertNewVariableEntry(identifier, clazz);
        }
        catch(TableException te){
            throw new TableException(te.getMessage() + " Scope: " + getScopeAddress());
        }
    }

    //Inserts a single function entry
    public void insertFunctionEntry(String identifier, int varType, int[] arguments){
        if(identifierExistsInThisScope(identifier))
            throw new TableException("An identifier \"" + identifier + "\" already visible within this scope (" +
                    getScopeAddress() + ". Cannot be re-declared.");
        table.insertNewFunctionEntry(identifier, varType, arguments);
    }

    //Creates a new ScopeTree child and returns its value
    public ScopeTree newScope(String codeblockType){
        ScopeTree temp = new ScopeTree(this, codeblockType, numOfChildren);
        children.add(temp);
        numOfChildren++;
        return temp;
    }

    //Gets a type from a given identifier from scope
    public int getTypeFromIdentifier(String identifier){
        for(Entry entry : allEntriesAtThisScope()){
            if(entry.getIdentifier().equals(identifier))
                return entry.getVarType();
        }
        if(identifier.charAt(0)=='#')
            throw new TableException("No function within this scope with identifier "
                    + identifier.substring(1) + "(). Scope: " + getScopeAddress());
        else
            throw new TableException("No variable within this scope with identifier "
                    + identifier + ". Scope: " + getScopeAddress());
    }

    //Gets argument types for a function from function identifier
    public int[] getArgumentsFromFunction(String identifier){
        for(Entry entry : allEntriesAtThisScope()){
            if(entry.getClass() == FunctionEntry.class)
                if(entry.getIdentifier().equals(identifier))
                    return ((FunctionEntry)entry).getArguments();
        }
        if(identifier.charAt(0)=='#')
            throw new TableException("No function within this scope with identifier " +
                    identifier.substring(1) + "(). Scope: " + getScopeAddress());
        else
            throw new TableException("No variable within this scope with identifier " +
                    identifier + ". Scope: " + getScopeAddress());
    }

    //Gets codeblock type, such as "if" or "while" or function name
    public String getCodeblockType() {
        return codeblockType;
    }

    //Gets all Entry values in scope
    private List<Entry> allEntriesAtThisScope(){
        List<Entry> entries = new ArrayList<>();
        entries.addAll(table.getEntries());
        if(parent!=null)
            entries.addAll(parent.allEntriesAtThisScope());
        return entries;
    }

    //References parent ScopeTree node
    public ScopeTree getParent() {
        return parent;
    }

    //Gets a scope address as String
    private String getScopeAddress(){
        if(parent!=null)
            return parent.getScopeAddress() + childNumOfParent;
        else
            return "0";
    }

    @Override
    public String toString() {
        return codeblockType +"#"+ getScopeAddress() + " - children: " + children;
    }
}
