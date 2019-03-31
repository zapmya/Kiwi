package de.goto3d.kiwi.compiler.codegenerator;

import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementNode;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 01.04.13
 * Time: 07:57
 */
public class BlockGenerator extends CodeGeneratorBase<BlockNode> {

    protected BlockGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(BlockNode blockNode) {

        // generate code for statements
        for (StatementNode node : blockNode.getStatementListNode().getItems()) {
            node.accept(this.visitor);
        }

        return null;
    }
}
