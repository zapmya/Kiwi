package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by da da gru on 20.04.16.
 *
 */
public class DimNode extends ExpressionNode {

    private int dimensions;

    public DimNode(SourcePosition sourcePosition, int dimensions) {
        super(sourcePosition);
        this.dimensions = dimensions;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public int getDimensions() {
        return dimensions;
    }
}
