package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.01.13
 * Time: 07:00
 */
public class OperationNode extends ExpressionNode {

    private final Operator operator;
    private ExpressionNode leftHandExpression;
    private ExpressionNode rightHandExpression;

    public OperationNode(
            SourcePosition sourcePosition, Operator operator, ExpressionNode leftHandExpression, ExpressionNode rightHandExpression
    ) {
        super(sourcePosition);
        this.operator = operator;
        this.leftHandExpression = leftHandExpression;
        this.rightHandExpression = rightHandExpression;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public Operator getOperator() {
        return operator;
    }

    public ExpressionNode getLeftHandExpression() {
        return leftHandExpression;
    }

    public void setLeftHandExpression(ExpressionNode leftHandExpression) {
        this.leftHandExpression = leftHandExpression;
    }

    public ExpressionNode getRightHandExpression() {
        return rightHandExpression;
    }

    public void setRightHandExpression(ExpressionNode rightHandExpression) {
        this.rightHandExpression= rightHandExpression;
    }

    @Override
    public boolean isConstant() {
        return this.leftHandExpression.isConstant() && this.rightHandExpression.isConstant();
    }
}
