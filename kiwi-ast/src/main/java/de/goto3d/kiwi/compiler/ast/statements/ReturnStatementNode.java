package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 14:08
 */
public class ReturnStatementNode extends StatementNode {

    private ExpressionNode expressionNode;

    public ReturnStatementNode(
            SourcePosition sourcePosition, ExpressionNode expressionNode
    ) {
        super(sourcePosition);
        this.expressionNode = expressionNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }

    public void setExpressionNode(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }
}
