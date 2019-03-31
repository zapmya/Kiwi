package de.goto3d.kiwi.compiler.ast.constructors;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.DimExpressionsNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.types.Type;

/**
 * Created by da gru on 29.12.14.
 *
 */
public class ArrayConstructorNode extends ExpressionNode {

    private final DimExpressionsNode dimExpressionsNode;

    public ArrayConstructorNode(SourcePosition sourcePosition, Type type, DimExpressionsNode dimExpressionsNode) {
        super(sourcePosition);
        this.dimExpressionsNode = dimExpressionsNode;
        this.type               = type;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public DimExpressionsNode getDimExpressionsNode() {
        return dimExpressionsNode;
    }
}
