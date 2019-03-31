package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMTypeRefArray;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 22:33
 */
public abstract class LLVMBaseType {

    protected SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType;

    protected LLVMBaseType(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        this.swigtype_p_llvmOpaqueType  = swigtype_p_llvmOpaqueType;
    }

    public static LLVMTypeRefArray convertArray(LLVMBaseType[] baseTypes) {
        int numItems                    = baseTypes.length;
        LLVMTypeRefArray typeRefArray   = new LLVMTypeRefArray(numItems);
        for ( int i = 0; i < numItems; i++ ) {
            LLVMBaseType baseType   = baseTypes[i];
            typeRefArray.setitem(i, baseType.swigtype_p_llvmOpaqueType);
        }
        return typeRefArray;
    }
}
