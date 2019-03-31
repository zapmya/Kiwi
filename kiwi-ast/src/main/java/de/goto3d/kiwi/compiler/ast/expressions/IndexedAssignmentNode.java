package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by da da gru on 22.03.16.
 *
 */
public class IndexedAssignmentNode extends AssignmentNode {

    private final IndexAccessNode indexAccessNode;

    public IndexedAssignmentNode(
            SourcePosition sourcePosition, IndexAccessNode indexAccessNode, ExpressionNode expressionNode
    ) {
        super(sourcePosition, indexAccessNode.getIdentifierNode(), expressionNode);

        this.indexAccessNode    = indexAccessNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public IndexAccessNode getIndexAccessNode() {
        return indexAccessNode;
    }
}
