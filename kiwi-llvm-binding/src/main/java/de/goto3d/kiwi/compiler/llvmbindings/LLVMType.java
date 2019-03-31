package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 22:37
 */
public class LLVMType extends LLVMBaseType {

    protected LLVMType(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        super(swigtype_p_llvmOpaqueType);
    }
    
    public static LLVMType voidType() {
        return new LLVMType(LLVMCore.LLVMVoidType());
    }
    public static LLVMType voidType(LLVMContext context) {
        return new LLVMType(LLVMCore.LLVMVoidTypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
}
