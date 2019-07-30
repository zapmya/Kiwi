package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.01.13
 * Time: 06:42
 */
public class AssignmentNode extends ExpressionNode {

    private final IdentifierNode identifierNode;
    private ExpressionNode expressionNode;

    public AssignmentNode(
            SourcePosition sourcePosition, IdentifierNode identifierNode, ExpressionNode expressionNode
    ) {
        super(sourcePosition);
        this.identifierNode = identifierNode;
        this.expressionNode = expressionNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }

    public void setExpressionNode(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
}
