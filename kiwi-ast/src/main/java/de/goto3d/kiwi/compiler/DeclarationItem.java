package de.goto3d.kiwi.compiler;

import de.goto3d.kiwi.compiler.ast.expressions.DeclarationAwareNode;

/**
 * Created by da gru on 31.05.15.
 */
public class DeclarationItem {

    public final DeclarationAwareNode declarationAwareNode;

    public final ScopeStack scopeStack;

    protected DeclarationItem(DeclarationAwareNode declarationAwareNode, ScopeStack scopeStack) {
        this.declarationAwareNode = declarationAwareNode;
        this.scopeStack = scopeStack;
    }

}
