package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.02.13
 * Time: 06:39
 */
public class RelationExpressionNode extends ExpressionNode {

    private final Relation relation;
    private ExpressionNode leftHandExpression;
    private ExpressionNode rightHandExpression;

    public RelationExpressionNode(
            SourcePosition sourcePosition, Relation relation,
            ExpressionNode leftHandExpression, ExpressionNode rightHandExpression
    ) {
        super(sourcePosition);
        this.relation = relation;
        this.leftHandExpression = leftHandExpression;
        this.rightHandExpression = rightHandExpression;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public Relation getRelation() {
        return relation;
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
        this.rightHandExpression = rightHandExpression;
    }

    @Override
    public boolean isConstant() {
        return this.leftHandExpression.isConstant() && this.rightHandExpression.isConstant();
    }
}
