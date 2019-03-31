package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.ast.statements.IfElseStatementNode;
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
 * Time: 21:09
 */
public class IfElseStatementGenerator extends CodeGeneratorBase<IfElseStatementNode> {

    public IfElseStatementGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(IfElseStatementNode ifStatementNode) {
        LLVMValue relationValue = ifStatementNode.getExpressionNode().accept(this.visitor);

        LLVMFunction function       = this.visitor.getCurrentFunction();

        LLVMBasicBlock trueBlock    = new LLVMBasicBlock(function, "iftrue");
        LLVMBasicBlock elseBlock    = new LLVMBasicBlock(function, "ifelse");
        LLVMBasicBlock endIfBlock   = new LLVMBasicBlock(function, "endif");

        LLVMBuilder builder         = this.visitor.getBuilder();
        builder.createConditionalBranch(relationValue, trueBlock, elseBlock);

        // build true block
        builder.setInsertPosition(trueBlock);
        ifStatementNode.getBlockNode().accept(this.visitor);
        builder.createBranch(endIfBlock);

        // build else block
        builder.moveBlockAfter(elseBlock, builder.getInsertBlock());
        builder.setInsertPosition(elseBlock);
        ifStatementNode.getElseBlockNode().accept(this.visitor);
        builder.createBranch(endIfBlock);

        // build end if block
        builder.moveBlockAfter(endIfBlock, builder.getInsertBlock());
        builder.setInsertPosition(endIfBlock);

        return null;
    }
}
