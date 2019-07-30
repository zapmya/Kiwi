package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 22.06.14
 * Time: 20:28
 */
public class VoidExpressionNode extends ExpressionNode {

    public VoidExpressionNode(SourcePosition sourcePosition) {
        super(sourcePosition);

        this.type   = new PrimitiveType(RawType.VOID);
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
