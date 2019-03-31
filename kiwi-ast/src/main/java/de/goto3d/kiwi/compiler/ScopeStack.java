package de.goto3d.kiwi.compiler;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 22.06.14
 * Time: 08:33
 */

public class ScopeStack {

    private final Stack<ScopeEntry> scopeEntries;

    public ScopeStack() {
        this.scopeEntries = new Stack<ScopeEntry>();
    }

    @SuppressWarnings("unchecked")
    public ScopeStack(ScopeStack scopeStack) {
        this.scopeEntries = (Stack<ScopeEntry>) scopeStack.scopeEntries.clone();
    }

    public void push(BlockNode blockNode) {
        this.scopeEntries.push(new ScopeEntry(blockNode));
    }

    public void push(ExternalFunctionNode functionNode) {
        this.scopeEntries.push(new ScopeEntry(functionNode));
    }

    public void pop() {
        this.scopeEntries.pop();
    }

    public boolean isParentOf(ScopeStack scopeStack) {

        // is this scope stack "older" than the given scope stack ?
        int parentSize  = this.scopeEntries.size();
        if ( parentSize > scopeStack.scopeEntries.size() ) {
            // yes -> this cannot be a parent !
            return false;
        }

        // no -> let's compare the scope's elements
        for (int i = 0; i < parentSize; i++) {
            // are nodes equal ?
            if ( this.scopeEntries.get(i).astNode != scopeStack.scopeEntries.get(i).astNode ) {
                // no -> that's it
                return false;
            }
        }

        // all tests passed -> we have found a match
        return true;
    }

    private final static class ScopeEntry {
        private final AstNode astNode;
        private final String name;

        private static int scopeEntryCnt;

        public ScopeEntry(BlockNode blockNode) {
            this.astNode    = blockNode;
            this.name       = "b"+(scopeEntryCnt++);
        }

        public ScopeEntry(ExternalFunctionNode functionNode) {
            this.astNode    = functionNode;
            this.name       = "f"+(scopeEntryCnt++);
        }
    }
}
