package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMBasicBlockRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 07.01.12
 * Time: 08:38
 */
public class LLVMBasicBlock {
    
    protected final SWIGTYPE_p_LLVMBasicBlockRef swigtype_p_llvmOpaqueBasicBlock;

    protected LLVMBasicBlock(SWIGTYPE_p_LLVMBasicBlockRef swigtype_p_llvmOpaqueBasicBlock) {
        this.swigtype_p_llvmOpaqueBasicBlock = swigtype_p_llvmOpaqueBasicBlock;
    }

    public LLVMBasicBlock(LLVMFunction function, String name) {
        this.swigtype_p_llvmOpaqueBasicBlock    = LLVMCore.LLVMAppendBasicBlock(
                function.swigtype_p_llvmOpaqueValue,
                name
        );
    }

    public LLVMBasicBlock(LLVMContext context, LLVMFunction function, String name) {
        this.swigtype_p_llvmOpaqueBasicBlock    = LLVMCore.LLVMAppendBasicBlockInContext(
                context.swigtype_p_llvmOpaqueContext,
                function.swigtype_p_llvmOpaqueValue,
                name
        );
    }

    public LLVMFunction getParent() {
        return LLVMFunction.getInstance(LLVMCore.LLVMGetBasicBlockParent(this.swigtype_p_llvmOpaqueBasicBlock));
    }
}
