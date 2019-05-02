package evaluators.ast;

import java.util.Arrays;

/**
 * AST class represents a single node of an Abstract Syntax Tree.
 * AST has a single value and any number of children.
 */
public class AST {
    final private Object value;
    final private AST[] children;
    public AST(Object value, AST... children){
        this.value = value;
        this.children = children;
    }

    public AST(Object value){
        this.value = value;
        this.children = null;
    }

    public boolean isLeaf(){
        return children == null;
    }

    public AST[] getChildren() {
        return children;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        if(children!=null)
            return "(" + value + " : " + Arrays.toString(children) + ")";
        else
            return value.toString();
    }
}
