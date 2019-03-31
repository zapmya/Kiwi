package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 11:33
 */
public abstract class LLVMTerminatorBuilder extends LLVMBuilderBase {

    protected LLVMTerminatorBuilder(LLVMContext context) {
        super(context);
    }

    public LLVMValue createRet(LLVMValue value) {
        return new LLVMValue(LLVMCore.LLVMBuildRet(this.swigtype_p_llvmOpaqueBuilder, value.swigtype_p_llvmOpaqueValue));
    }

    public LLVMValue createRetVoid() {
        return new LLVMValue(LLVMCore.LLVMBuildRetVoid(this.swigtype_p_llvmOpaqueBuilder));
    }

    public LLVMValue createBranch(LLVMBasicBlock destinationBlock) {
        return new LLVMValue(
                LLVMCore.LLVMBuildBr(
                        this.swigtype_p_llvmOpaqueBuilder,
                        destinationBlock.swigtype_p_llvmOpaqueBasicBlock
                )
        );
    }

    public void moveBlockBefore(LLVMBasicBlock blockToMove, LLVMBasicBlock position) {
        LLVMCore.LLVMMoveBasicBlockBefore(
                blockToMove.swigtype_p_llvmOpaqueBasicBlock, position.swigtype_p_llvmOpaqueBasicBlock
        );
    }

    public void moveBlockAfter(LLVMBasicBlock blockToMove, LLVMBasicBlock position) {
        LLVMCore.LLVMMoveBasicBlockAfter(
                blockToMove.swigtype_p_llvmOpaqueBasicBlock, position.swigtype_p_llvmOpaqueBasicBlock
        );
    }

    public LLVMValue createConditionalBranch(LLVMValue condition, LLVMBasicBlock ifBlock, LLVMBasicBlock elseBlock) {
        return new LLVMValue(
                LLVMCore.LLVMBuildCondBr(
                        this.swigtype_p_llvmOpaqueBuilder,
                        condition.swigtype_p_llvmOpaqueValue,
                        ifBlock.swigtype_p_llvmOpaqueBasicBlock,
                        elseBlock.swigtype_p_llvmOpaqueBasicBlock
                )
        );
    }
}
