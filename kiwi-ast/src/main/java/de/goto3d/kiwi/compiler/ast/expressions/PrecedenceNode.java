package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.types.Type;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 10:24
 */
public class PrecedenceNode extends ExpressionNode {

    private final ExpressionNode expressionNode;

    public PrecedenceNode(SourcePosition sourcePosition, ExpressionNode expressionNode) {
        super(sourcePosition);
        this.expressionNode = expressionNode;
    }

    @Override
    public Type getType() {
        return this.expressionNode.getType();
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }
}
