package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.types.Type;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 21.09.13
 * Time: 17:59
 */
public class DeclarationNode extends ExpressionNode implements DeclarationAwareNode {

    private final IdentifierNode identifierNode;

    public DeclarationNode(SourcePosition sourcePosition, Type type, IdentifierNode identifierNode) {
        super(sourcePosition);
        this.type           = type;
        this.identifierNode = identifierNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }

    @Override
    public DeclarationNode getDeclarationNode() {
        return this;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
}
