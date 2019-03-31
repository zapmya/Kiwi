package de.goto3d.kiwi.compiler.ast.constructors;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.NumberNode;
import de.goto3d.kiwi.compiler.ast.types.PointerType;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;

/**
 * Created by da gru on 29.12.14.
 *
 */
public class NativeArrayConstructorNode extends ExpressionNode {

    private final NumberNode address;

    public NativeArrayConstructorNode(SourcePosition sourcePosition, NumberNode address) {
        super(sourcePosition);
        this.address    = address;
        this.type       = new PointerType(new PrimitiveType(RawType.BYTE));
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public NumberNode getAddress() {
        return address;
    }
}
