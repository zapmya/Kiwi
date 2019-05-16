package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 12.01.13
 * Time: 21:09
 */
public class IfStatementNode extends SimpleStatementNode<ExpressionNode> {

    private final BlockNode blockNode;

    public IfStatementNode(
            SourcePosition sourcePosition, ExpressionNode expressionNode, BlockNode blockNode
    ) {
        super(sourcePosition,expressionNode);
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
