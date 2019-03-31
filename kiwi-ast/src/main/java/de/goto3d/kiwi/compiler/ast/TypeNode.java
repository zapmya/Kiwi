package de.goto3d.kiwi.compiler.ast;

import de.goto3d.kiwi.compiler.ast.types.Type;

/**
 * Created by da da gru on 10.04.16.
 *
 */
public class TypeNode extends AstNode {

    private final Type type;

    public TypeNode(SourcePosition sourcePosition, Type type) {
        super(sourcePosition);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return null;
    }
}
