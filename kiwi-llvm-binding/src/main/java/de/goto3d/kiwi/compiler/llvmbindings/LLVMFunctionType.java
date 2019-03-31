package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 22:39
 */
public class LLVMFunctionType extends LLVMBaseType {
    
    private LLVMFunctionType(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        super(swigtype_p_llvmOpaqueType);
    }

    public LLVMFunctionType(LLVMBaseType returnType, LLVMBaseType[] paramTypes) {
        this(returnType, paramTypes, false);
    }

    public LLVMFunctionType(LLVMBaseType returnType, LLVMBaseType[] paramTypes, boolean varArg) {
        super(
                LLVMCore.LLVMFunctionType(
                        returnType.swigtype_p_llvmOpaqueType,
                        LLVMBaseType.convertArray(paramTypes).cast(),
                        paramTypes.length, varArg ? 1 : 0
            )
        );
    }
}
