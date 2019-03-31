package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 21:19
 */
public abstract class LLVMAggregateType extends LLVMType {

    protected LLVMAggregateType(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        super(swigtype_p_llvmOpaqueType);
    }
}
