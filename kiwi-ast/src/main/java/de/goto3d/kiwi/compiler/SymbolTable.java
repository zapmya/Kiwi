package de.goto3d.kiwi.compiler;

import de.goto3d.kiwi.compiler.ast.expressions.DeclarationAwareNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.types.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by da da gru on 27.05.15.
 *
 */
public class SymbolTable {

    private final DeclarationStore declarationStore = new DeclarationStore();

    private final Map<String, List<ExternalFunctionNode>> functionNameMap   =
            new HashMap<String, List<ExternalFunctionNode>>();

    public void add(ExternalFunctionNode functionNode) {
        String signature = functionNode.getSignature();
        List<ExternalFunctionNode> externalFunctionNodes    = this.functionNameMap.get(signature);
        if ( externalFunctionNodes == null ) {
            externalFunctionNodes   = new ArrayList<ExternalFunctionNode>(1);
            this.functionNameMap.put(signature, externalFunctionNodes);
        }
        externalFunctionNodes.add(functionNode);
    }

    public List<ExternalFunctionNode> findFunctionNodes(String identifier) {
        return this.functionNameMap.get(identifier);
    }

    public ExternalFunctionNode findFirstFunctionNode(String identifier) {
        List<ExternalFunctionNode> nodes = this.functionNameMap.get(identifier);
        return nodes == null || nodes.isEmpty() ? null : nodes.get(0);
    }

    public void insertIdentifier(IdentifierNode identifierNode, ScopeStack scopeStack) {
        this.declarationStore.insertIdentifier(identifierNode, scopeStack);
    }

    public void insertDeclaration(DeclarationAwareNode declarationNode, ScopeStack scopeStack) {
        this.declarationStore.insertDeclaration(declarationNode, scopeStack);

    }

    public Type getType(IdentifierNode identifierNode) {
        return this.declarationStore.getType(identifierNode);
    }

    public List<DeclarationItem> getDeclarations(IdentifierNode identifierNode) {
        return this.declarationStore.getDeclarations(identifierNode);
    }
}
