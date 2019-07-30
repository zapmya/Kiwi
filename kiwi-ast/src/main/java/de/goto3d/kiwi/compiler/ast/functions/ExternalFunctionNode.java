package de.goto3d.kiwi.compiler.ast.functions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ArgumentListNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.02.13
 * Time: 20:56
 */
public class ExternalFunctionNode extends ExpressionNode {

    private final DeclarationNode declarationNode;

    private final ArgumentListNode argumentListNode;

    public ExternalFunctionNode(
            SourcePosition sourcePosition, DeclarationNode declarationNode, ArgumentListNode argumentListNode
    ) {
        super(sourcePosition);
        this.declarationNode    = declarationNode;
        this.argumentListNode   = argumentListNode;
        this.type               = declarationNode.getType();
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public ArgumentListNode getArgumentListNode() {
        return argumentListNode;
    }

    public DeclarationNode getDeclarationNode() {
        return declarationNode;
    }

    public String getName() {
        return this.declarationNode.getIdentifierNode().getIdentifier();
    }

    public String getSignature() {
        return this.declarationNode.getIdentifierNode().getIdentifier();
    }

    @Override
    public boolean isConstant() {
        return false;
    }
}
