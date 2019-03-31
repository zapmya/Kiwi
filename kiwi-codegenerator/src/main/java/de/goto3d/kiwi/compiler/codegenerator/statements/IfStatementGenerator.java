package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.ast.statements.IfStatementNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBasicBlock;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBuilder;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMFunction;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.04.13
 * Time: 09:06
 */
public class IfStatementGenerator extends CodeGeneratorBase<IfStatementNode> {

    public IfStatementGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(IfStatementNode ifStatementNode) {

        LLVMBuilder builder         = this.visitor.getBuilder();

        LLVMValue relationValue     = ifStatementNode.getExpressionNode().accept(this.visitor);

        LLVMFunction function       = this.visitor.getCurrentFunction();

        LLVMBasicBlock trueBlock    = new LLVMBasicBlock(function, "iftrue");
        LLVMBasicBlock falseBlock   = new LLVMBasicBlock(function, "iffalse");

        builder.createConditionalBranch(relationValue, trueBlock, falseBlock);

        // build true block
        builder.setInsertPosition(trueBlock);
        ifStatementNode.getBlockNode().accept(this.visitor);
        builder.createBranch(falseBlock);

        // build false block
        builder.moveBlockAfter(falseBlock, builder.getInsertBlock());
        builder.setInsertPosition(falseBlock);

        return null;
    }
}
