package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 07.01.12
 * Time: 21:34
 */
public class LLVMIntTypes extends LLVMBaseType {
    
    private LLVMIntTypes(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        super(swigtype_p_llvmOpaqueType);
    }

    /** 1 bit integer type */
    public static LLVMIntTypes int1Type() {
        return new LLVMIntTypes(LLVMCore.LLVMInt1Type());
    }
    /** 8 bit integer type */
    public static LLVMIntTypes int8Type() {
        return new LLVMIntTypes(LLVMCore.LLVMInt8Type());
    }
    /** 16 bit integer type */
    public static LLVMIntTypes int16Type() {
        return new LLVMIntTypes(LLVMCore.LLVMInt16Type());
    }
    /** 32 bit integer type */
    public static LLVMIntTypes int32Type() {
        return new LLVMIntTypes(LLVMCore.LLVMInt32Type());
    }
    /** 64 bit integer type */
    public static LLVMIntTypes int64Type() {
        return new LLVMIntTypes(LLVMCore.LLVMInt64Type());
    }
    /** n bit integer type */
    public static LLVMIntTypes intType(long numBits) {
        return new LLVMIntTypes(LLVMCore.LLVMIntType(numBits));
    }

    /** 1 bit integer type */
    public static LLVMIntTypes int1Type(LLVMContext context) {
        return new LLVMIntTypes(LLVMCore.LLVMInt1TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /** 8 bit integer type */
    public static LLVMIntTypes int8Type(LLVMContext context) {
        return new LLVMIntTypes(LLVMCore.LLVMInt8TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /** 16 bit integer type */
    public static LLVMIntTypes int16Type(LLVMContext context) {
        return new LLVMIntTypes(LLVMCore.LLVMInt16TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /** 32 bit integer type */
    public static LLVMIntTypes int32Type(LLVMContext context) {
        return new LLVMIntTypes(LLVMCore.LLVMInt32TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /** 64 bit integer type */
    public static LLVMIntTypes int64Type(LLVMContext context) {
        return new LLVMIntTypes(LLVMCore.LLVMInt64TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /** n bit integer type */
    public static LLVMIntTypes intType(LLVMContext context, long numBits) {
        return new LLVMIntTypes(LLVMCore.LLVMIntTypeInContext(context.swigtype_p_llvmOpaqueContext,numBits));
    }
}
