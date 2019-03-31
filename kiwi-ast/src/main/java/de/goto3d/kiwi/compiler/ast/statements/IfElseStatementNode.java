package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.RelationExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 12.01.13
 * Time: 21:14
 */
public class IfElseStatementNode extends IfStatementNode {

    private final BlockNode elseBlockNode;

    public IfElseStatementNode(
            SourcePosition sourcePosition, RelationExpressionNode expressionNode,
            BlockNode blockNode, BlockNode elseBlockNode
    ) {
        super(sourcePosition, expressionNode, blockNode);
        this.elseBlockNode  = elseBlockNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public BlockNode getElseBlockNode() {
        return elseBlockNode;
    }
}
