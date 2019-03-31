package de.goto3d.kiwi.compiler.ast.functions;

import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.expressions.ArgumentListNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 17.01.13
 * Time: 08:44
 */
public class InternalFunctionNode extends ExternalFunctionNode {

    private final BlockNode blockNode;

    public InternalFunctionNode(
            SourcePosition sourcePosition, DeclarationNode declarationNode,
            ArgumentListNode argumentListNode, BlockNode blockNode
    ) {
        super(sourcePosition, declarationNode, argumentListNode);
        this.blockNode = blockNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public BlockNode getBlockNode() {
        return blockNode;
    }
}
