package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 22:29
 */
public class LLVMArgument extends LLVMValue {

    private LLVMArgument(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        super(swigtype_p_llvmOpaqueValue);
    }

    protected static LLVMArgument getInstance(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue, String name) {
        LLVMArgument argument = LLVMValue.getInstance(swigtype_p_llvmOpaqueValue, LLVMArgument.class);
        argument.setName(name);
        return argument;
    }
}
