package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.01.13
 * Time: 11:26
 */
public class NumberNode extends ExpressionNode {

    private final Number value;

    public NumberNode(SourcePosition sourcePosition, float value) {
        super(sourcePosition);
        this.type = new PrimitiveType(RawType.FLOAT);
        this.value = value;
    }

    public NumberNode(SourcePosition sourcePosition, double value) {
        super(sourcePosition);
        this.type = new PrimitiveType(RawType.DOUBLE);
        this.value = value;
    }

    public NumberNode(SourcePosition sourcePosition, BigInteger value) {
        super(sourcePosition);
        this.type = new PrimitiveType(RawType.INT);
        this.value = value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public Number getValue() {
        return value;
    }

    @Override
    public boolean isConstant() {
        return true;
    }
}
