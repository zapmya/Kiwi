package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.RelationExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 12.01.13
 * Time: 21:16
 */
public class WhileStatementNode extends SimpleStatementNode<RelationExpressionNode> {

    private final BlockNode blockNode;

    public WhileStatementNode(
            SourcePosition sourcePosition, RelationExpressionNode relationExpressionNode, BlockNode blockNode
    ) {
        super(sourcePosition,relationExpressionNode);
        this.blockNode  = blockNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public BlockNode getBlockNode() {
        return blockNode;
    }
}
