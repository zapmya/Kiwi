package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.ast.statements.WhileStatementNode;
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
public class WhileStatementGenerator extends CodeGeneratorBase<WhileStatementNode> {

    public WhileStatementGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(WhileStatementNode whileStatementNode) {

        LLVMBuilder builder             = this.visitor.getBuilder();

        LLVMFunction function           = this.visitor.getCurrentFunction();

        // add condition block
        LLVMBasicBlock conditionBlock   = new LLVMBasicBlock(function, "condition");
        builder.createBranch(conditionBlock);

        // fill condition block
        builder.setInsertPosition(conditionBlock);
        LLVMValue relationValue         = whileStatementNode.getExpressionNode().accept(this.visitor);
        LLVMBasicBlock loopBlock        = new LLVMBasicBlock(function, "loop");
        LLVMBasicBlock loopEndBlock     = new LLVMBasicBlock(function, "loopend");
        builder.createConditionalBranch(relationValue, loopBlock, loopEndBlock);

        // build loop block
        builder.setInsertPosition(loopBlock);
        whileStatementNode.getBlockNode().accept(this.visitor);
        builder.createBranch(conditionBlock);

        // build end block
        builder.moveBlockAfter(loopEndBlock, builder.getInsertBlock());
        builder.setInsertPosition(loopEndBlock);

        return null;
    }
}
