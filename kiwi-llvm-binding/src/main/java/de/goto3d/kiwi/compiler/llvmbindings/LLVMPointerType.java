package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by da da gru on 05.06.16.
 *
 */
public class LLVMPointerType extends LLVMBaseType {

    private LLVMPointerType(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        super(swigtype_p_llvmOpaqueType);
    }

    public static LLVMPointerType createPointerType(LLVMBaseType baseType) {
        return new LLVMPointerType(LLVMCore.LLVMPointerType(baseType.swigtype_p_llvmOpaqueType, 0));
    }

}
