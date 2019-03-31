package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 21:46
 */
public class LLVMVector extends LLVMValue {

    private LLVMVector(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        super(swigtype_p_llvmOpaqueValue);
    }

    protected static LLVMVector getInstance(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        return LLVMValue.getInstance(swigtype_p_llvmOpaqueValue, LLVMVector.class);
    }

    public static LLVMVector getInstance(LLVMValue[] values) {
        return LLVMVector.getInstance(
                LLVMCore.LLVMConstVector(LLVMValue.convertArray(values).cast(), values.length)
        );
    }
}
