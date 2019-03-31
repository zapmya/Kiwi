package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMBuilderRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 11:26
 */
public abstract class LLVMBuilderBase {

    protected SWIGTYPE_p_LLVMBuilderRef swigtype_p_llvmOpaqueBuilder;

    protected LLVMBuilderBase() {
        this.swigtype_p_llvmOpaqueBuilder = LLVMCore.LLVMCreateBuilder();
    }

    protected LLVMBuilderBase(LLVMContext context) {
        this.swigtype_p_llvmOpaqueBuilder = LLVMCore.LLVMCreateBuilderInContext(context.swigtype_p_llvmOpaqueContext);
    }

    public void setInsertPosition(LLVMBasicBlock block) {
        LLVMCore.LLVMPositionBuilderAtEnd(this.swigtype_p_llvmOpaqueBuilder, block.swigtype_p_llvmOpaqueBasicBlock);
    }

    public void clearInsertionPosition() {
        LLVMCore.LLVMClearInsertionPosition(this.swigtype_p_llvmOpaqueBuilder);
    }

    public LLVMBasicBlock getInsertBlock() {
        return new LLVMBasicBlock(LLVMCore.LLVMGetInsertBlock(this.swigtype_p_llvmOpaqueBuilder));
    }
}
