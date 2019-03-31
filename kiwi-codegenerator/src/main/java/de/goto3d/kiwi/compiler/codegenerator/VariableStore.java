package de.goto3d.kiwi.compiler.codegenerator;

import de.goto3d.kiwi.compiler.llvmbindings.LLVMPointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 01.04.13
 * Time: 07:35
 */
public class VariableStore {

    private final VariableStore parent;

    private final Map<String, LLVMPointer> variables  = new HashMap<String, LLVMPointer>();

    public VariableStore() {
        this(null);
    }

    public VariableStore(VariableStore parent) {
        this.parent = parent;
    }

    private VariableStore getParent() {
        return parent;
    }

    private LLVMPointer getVariable(String name) {
        return this.variables.get(name);
    }

    public void setVariable(String name, LLVMPointer pointer) {
        this.variables.put(name, pointer);
    }

    public LLVMPointer findVariable(String name) {
        VariableStore store = this;
        do {
            LLVMPointer pointer = store.getVariable(name);
            if ( pointer != null ) {
                return pointer;
            }
            store   = store.getParent();
        } while ( store != null );

        return null;
    }
}
