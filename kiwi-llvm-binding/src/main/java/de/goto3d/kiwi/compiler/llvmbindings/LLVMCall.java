package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCallConv;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.04.13
 * Time: 14:53
 */
public class LLVMCall extends LLVMValue {

    private LLVMCall(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        super(swigtype_p_llvmOpaqueValue);
    }

    protected static LLVMCall getInstance(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        return LLVMValue.getInstance(swigtype_p_llvmOpaqueValue, LLVMCall.class);
    }

    public void setCallingConvention(LLVMCallConv callingConvention) {
        LLVMCore.LLVMSetInstructionCallConv(this.swigtype_p_llvmOpaqueValue, callingConvention.swigValue());
    }
}
