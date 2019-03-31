package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 07.01.12
 * Time: 21:30
 */
public class LLVMConstantInt extends LLVMValue {

    private LLVMConstantInt(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        super(swigtype_p_llvmOpaqueValue);
    }

    protected static LLVMConstantInt getInstance(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        return LLVMValue.getInstance(swigtype_p_llvmOpaqueValue, LLVMConstantInt.class);
    }

    public static LLVMConstantInt getInstance(LLVMIntTypes intType, String string, int radix) {
        return LLVMConstantInt.getInstance(
                LLVMCore.LLVMConstIntOfString(intType.swigtype_p_llvmOpaqueType, string, (short)radix)
        );
    }

    public static LLVMConstantInt getInstance(LLVMIntTypes intType, BigInteger value, boolean signExtend) {
       return LLVMConstantInt.getInstance(
                LLVMCore.LLVMConstInt(
                        intType.swigtype_p_llvmOpaqueType,
                        value,
                        signExtend ? 1 : 0
                )
        );
    }

}
