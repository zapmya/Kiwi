package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 28.09.13
 * Time: 19:18
 */
public class DeclarationAndAssignmentNode extends AssignmentNode implements DeclarationAwareNode {

    private final DeclarationNode declarationNode;

    public DeclarationAndAssignmentNode(
            SourcePosition sourcePosition, DeclarationNode declarationNode, ExpressionNode expressionNode
    ) {
        super(sourcePosition, declarationNode.getIdentifierNode(), expressionNode);
        this.declarationNode    = declarationNode;
        this.type               = declarationNode.getType();

        System.out.println(declarationNode.getIdentifierNode().getIdentifier() + ": " + sourcePosition);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public DeclarationNode getDeclarationNode() {
        return declarationNode;
    }
}
