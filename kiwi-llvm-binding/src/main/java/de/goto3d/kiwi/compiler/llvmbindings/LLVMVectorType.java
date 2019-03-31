package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by da gru on 30.12.14.
 */
public class LLVMVectorType extends LLVMBaseType {

    private LLVMVectorType(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        super(swigtype_p_llvmOpaqueType);
    }

    public static LLVMVectorType createVectorType(LLVMBaseType baseType, int dimensions) {
        return new LLVMVectorType(LLVMCore.LLVMVectorType(baseType.swigtype_p_llvmOpaqueType, dimensions));
    }
}
