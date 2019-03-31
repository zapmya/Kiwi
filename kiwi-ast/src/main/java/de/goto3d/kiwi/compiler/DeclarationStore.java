package de.goto3d.kiwi.compiler;

import de.goto3d.kiwi.compiler.ast.expressions.DeclarationAwareNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.types.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 12.06.14
 * Time: 08:08
 */
public class DeclarationStore {

    private Map<String, List<DeclarationItem>> nameDeclarationMap =
            new HashMap<String, List<DeclarationItem>>();

    private Map<IdentifierNode, ScopeStack> identifierScopeMap  = new HashMap<IdentifierNode, ScopeStack>();

    public void insertIdentifier(IdentifierNode identifierNode, ScopeStack scopeStack) {

        // create shallow copy of scope stack
        scopeStack = new ScopeStack(scopeStack);

        // store identifier node
        this.identifierScopeMap.put(identifierNode, scopeStack);
    }

    public void insertDeclaration(DeclarationAwareNode declarationAwareNode, ScopeStack scopeStack) {

        // create shallow copy of scope stack
        scopeStack = new ScopeStack(scopeStack);

        // update declaration map
        IdentifierNode identifierNode = declarationAwareNode.getDeclarationNode().getIdentifierNode();
        String name = identifierNode.getIdentifier();

        List<DeclarationItem> declarationItems  = this.nameDeclarationMap.get(name);
        if ( declarationItems == null ) {
            declarationItems   = new ArrayList<DeclarationItem>(1);
            this.nameDeclarationMap.put(name, declarationItems);
        }
        declarationItems.add(new DeclarationItem(declarationAwareNode, scopeStack));

        // update identifier map
        this.identifierScopeMap.put(identifierNode, scopeStack);
    }

    public Type getType(IdentifierNode identifierNode) {

        // lookup scope
        ScopeStack scopeStack   = this.identifierScopeMap.get(identifierNode);
        // anything found ?
        if ( scopeStack == null ) {
            // no -> that's it
            return null;
        }

        // get identifier name
        String name = identifierNode.getIdentifier();

        // lookup matching declaration nodes
        List<DeclarationItem> declarationItems  = this.nameDeclarationMap.get(name);
        if ( declarationItems == null || declarationItems.isEmpty() ) {
            return null;
        }

        // find declaration within scope
        for (DeclarationItem declarationItem : declarationItems) {
            // is declaration reachable ?
            if ( declarationItem.scopeStack.isParentOf(scopeStack) ) {
                // yes -> return it
                return declarationItem.declarationAwareNode.getDeclarationNode().getType();
            }
        }

        // nothing found
        return null;
    }

    public List<DeclarationItem> getDeclarations(IdentifierNode identifierNode) {
        // lookup matching declaration nodes
        List<DeclarationItem> declarationItems      = this.nameDeclarationMap.get(identifierNode.getIdentifier());
        if ( declarationItems == null ) {
            return null;
        }

        // get identifier's scope
        ScopeStack scopeStack   = this.identifierScopeMap.get(identifierNode);
        // does it have a scope ?
        if ( scopeStack == null ) {
            // no -> so we do not care
            // we are only interested in variable identifiers
            return null;
        }

        List<DeclarationItem> declarationWithinScope= new ArrayList<DeclarationItem>(declarationItems.size());
        for (DeclarationItem declarationItem : declarationItems) {
            // is declaration reachable ?
            if ( !declarationItem.scopeStack.isParentOf(scopeStack) ) {
                // no -> skip it
                continue;
            }
            // yes -> add it to final list
            declarationWithinScope.add(declarationItem);
        }
        return declarationWithinScope;
    }

    public ScopeStack getScopeStack(IdentifierNode identifierNode) {
        return this.identifierScopeMap.get(identifierNode);
    }
}
