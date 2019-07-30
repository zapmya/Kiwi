package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.01.13
 * Time: 06:34
 */
public class IdentifierNode extends ExpressionNode {

    private final String identifier;

    public IdentifierNode(SourcePosition sourcePosition, String identifier) {
        super(sourcePosition);
        this.identifier = identifier;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
}
