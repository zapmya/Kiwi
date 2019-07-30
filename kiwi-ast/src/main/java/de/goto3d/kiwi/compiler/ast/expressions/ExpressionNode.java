package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.ast.types.TypeAwareNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.01.13
 * Time: 11:12
 */
public abstract class ExpressionNode extends AstNode implements TypeAwareNode {

    protected Type type;

    protected ExpressionNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    public abstract boolean isConstant();
}
