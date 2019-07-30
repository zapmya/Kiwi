package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by da da gru on 07.01.16.
 *
 */
public class IndexAccessNode extends ExpressionNode {

    private final IdentifierNode identifierNode;

    private final DimExpressionsNode dimExpressionsNode;

    public IndexAccessNode(
            SourcePosition sourcePosition, IdentifierNode identifierNode, DimExpressionsNode dimExpressionsNode
    ) {
        super(sourcePosition);

        this.identifierNode = identifierNode;
        this.dimExpressionsNode = dimExpressionsNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }

    public DimExpressionsNode getDimExpressionsNode() {
        return dimExpressionsNode;
    }

    @Override
    public boolean isConstant() {
        return false;
    }
}
