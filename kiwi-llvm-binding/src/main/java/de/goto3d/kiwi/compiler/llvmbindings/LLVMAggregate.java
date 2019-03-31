package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 18.01.12
 * Time: 16:48
 */
public abstract class LLVMAggregate extends LLVMValue {

    private LLVMAggregate(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        super(swigtype_p_llvmOpaqueValue);
    }

    protected static LLVMAggregate getInstance(SWIGTYPE_p_LLVMValueRef value) {
        return LLVMValue.getInstance(value, LLVMAggregate.class);
    }
}
