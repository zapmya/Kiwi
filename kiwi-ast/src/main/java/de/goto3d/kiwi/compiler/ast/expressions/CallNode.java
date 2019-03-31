package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.01.13
 * Time: 06:52
 */
public class CallNode extends ExpressionNode {

    private final IdentifierNode identifierNode;
    private final ExpressionListNode expressionListNode;

    // TODO: add signature

    public CallNode(
            SourcePosition sourcePosition, IdentifierNode identifierNode, ExpressionListNode expressionListNode
    ) {
        super(sourcePosition);
        this.identifierNode = identifierNode;
        this.expressionListNode = expressionListNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }

    public ExpressionListNode getExpressionListNode() {
        return expressionListNode;
    }
}
