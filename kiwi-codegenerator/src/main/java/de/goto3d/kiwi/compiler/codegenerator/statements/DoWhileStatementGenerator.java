package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.ast.statements.DoWhileStatementNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBasicBlock;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBuilder;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMFunction;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.04.13
 * Time: 19:55
 */
public class DoWhileStatementGenerator extends CodeGeneratorBase<DoWhileStatementNode> {

    public DoWhileStatementGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(DoWhileStatementNode doWhileStatementNode) {

        LLVMBuilder builder             = this.visitor.getBuilder();

        LLVMFunction function           = this.visitor.getCurrentFunction();

        // add loop block
        LLVMBasicBlock loopBlock        = new LLVMBasicBlock(function, "loop_block");

        // construct loop condition & loop end block already here so we can reference it in later break statements
        // within the loop block!!!
        LLVMBasicBlock conditionBlock   = new LLVMBasicBlock(function, "loop_condition");
        LLVMBasicBlock loopEndBlock     = new LLVMBasicBlock(function, "loop_end");
        this.visitor.pushLoopEntry(new LoopEntry(conditionBlock, loopEndBlock));

        // build loop block
        builder.createBranch(loopBlock);
        builder.setInsertPosition(loopBlock);
        doWhileStatementNode.getBlockNode().accept(this.visitor);

        // add condition block
        builder.createBranch(conditionBlock);
        // fill condition block
        builder.setInsertPosition(conditionBlock);
        LLVMValue relationValue         = doWhileStatementNode.getExpressionNode().accept(this.visitor);
        builder.createConditionalBranch(relationValue, loopBlock, loopEndBlock);

        // build end block
        builder.setInsertPosition(loopEndBlock);

        return null;
    }
}
