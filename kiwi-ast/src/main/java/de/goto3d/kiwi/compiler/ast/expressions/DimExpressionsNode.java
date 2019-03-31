package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by da da gru on 23.04.16.
 *
 */
public class DimExpressionsNode extends ExpressionListNode {

    public DimExpressionsNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    public DimExpressionsNode(SourcePosition sourcePosition, ExpressionNode expressionNode) {
        super(sourcePosition, expressionNode);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
