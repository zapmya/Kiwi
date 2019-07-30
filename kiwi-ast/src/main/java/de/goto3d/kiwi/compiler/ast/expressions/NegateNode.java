package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.01.13
 * Time: 12:08
 */
public class NegateNode extends ExpressionNode {

    private final ExpressionNode operand;

    public NegateNode(SourcePosition sourcePosition, ExpressionNode expressionNode) {
        super(sourcePosition);
        this.operand = expressionNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public ExpressionNode getOperand() {
        return operand;
    }

    @Override
    public boolean isConstant() {
        return this.operand.isConstant();
    }
}
