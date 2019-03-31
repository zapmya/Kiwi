package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 12.01.13
 * Time: 21:07
 */
public class SimpleStatementNode<E extends ExpressionNode> extends StatementNode {

    private final E expressionNode;

    public SimpleStatementNode(SourcePosition sourcePosition, E expressionNode) {
        super(sourcePosition);
        this.expressionNode = expressionNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public E getExpressionNode() {
        return expressionNode;
    }
}
