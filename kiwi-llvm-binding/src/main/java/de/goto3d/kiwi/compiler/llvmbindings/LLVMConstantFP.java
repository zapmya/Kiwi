package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 21:46
 */
public class LLVMConstantFP extends LLVMValue {

    private LLVMConstantFP(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        super(swigtype_p_llvmOpaqueValue);
    }

    protected static LLVMConstantFP getInstance(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        return LLVMValue.getInstance(swigtype_p_llvmOpaqueValue, LLVMConstantFP.class);
    }

    public static LLVMConstantFP getInstance(LLVMRealTypes realType, String string) {
        return LLVMConstantFP.getInstance(LLVMCore.LLVMConstRealOfString(realType.swigtype_p_llvmOpaqueType, string));
    }

    public static LLVMConstantFP getInstance(LLVMRealTypes realType, double value) {
        return LLVMConstantFP.getInstance(LLVMCore.LLVMConstReal(realType.swigtype_p_llvmOpaqueType, value));
    }
}
