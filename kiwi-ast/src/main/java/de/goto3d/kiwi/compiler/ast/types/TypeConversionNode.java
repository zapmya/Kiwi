package de.goto3d.kiwi.compiler.ast.types;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.03.14
 * Time: 06:57
 */
public class TypeConversionNode extends ExpressionNode {

    private final ExpressionNode expressionNode;

    private final boolean implicitCast;

    private final Type sourceType;

    public TypeConversionNode(SourcePosition sourcePosition, ExpressionNode expressionNode, boolean implicitCast) {
        super(sourcePosition);
        this.expressionNode = expressionNode;
        this.sourceType     = expressionNode.getType();
        this.implicitCast   = implicitCast;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }

    public Type getSourceType() {
        return sourceType;
    }

    public boolean isImplicitCast() {
        return implicitCast;
    }
}
