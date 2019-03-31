package de.goto3d.kiwi.compiler.ast.constructors;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionListNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.types.RawType;
import de.goto3d.kiwi.compiler.ast.types.VectorType;

/**
 * Created by da gru on 29.12.14.
 *
 */
public class VectorConstructorNode extends ExpressionNode {

    private final ExpressionListNode expressionListNode;

    public VectorConstructorNode(SourcePosition sourcePosition, ExpressionListNode expressionListNode) {
        super(sourcePosition);
        this.expressionListNode = expressionListNode;
        // TODO: determine RawType
        this.type               = new VectorType(RawType.INT, expressionListNode.size());
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }


    public ExpressionListNode getExpressionListNode() {
        return expressionListNode;
    }
}
