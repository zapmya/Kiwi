package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMTypeRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 21:49
 */
public class LLVMRealTypes extends LLVMBaseType {
    
    private LLVMRealTypes(SWIGTYPE_p_LLVMTypeRef swigtype_p_llvmOpaqueType) {
        super(swigtype_p_llvmOpaqueType);
    }

    /**< 32 bit floating point type */
    public static LLVMRealTypes floatType() {
        return new LLVMRealTypes(LLVMCore.LLVMFloatType());
    }
    /**< 64 bit floating point type */
    public static LLVMRealTypes doubleType() {
        return new LLVMRealTypes(LLVMCore.LLVMDoubleType());
    }
    /**< 80 bit floating point type (X87) */
    public static LLVMRealTypes x86_FP80Type() {
        return new LLVMRealTypes(LLVMCore.LLVMX86FP80Type());
    }
    /**< 128 bit floating point type (112-bit mantissa)*/
    public static LLVMRealTypes fp128Type() {
        return new LLVMRealTypes(LLVMCore.LLVMFP128Type());
    }
    /**< 128 bit floating point type (two 64-bits) */
    public static LLVMRealTypes ppc_FP128Type() {
        return new LLVMRealTypes(LLVMCore.LLVMPPCFP128Type());
    }

    /**< 32 bit floating point type */
    public static LLVMRealTypes floatType(LLVMContext context) {
        return new LLVMRealTypes(LLVMCore.LLVMFloatTypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /**< 64 bit floating point type */
    public static LLVMRealTypes doubleType(LLVMContext context) {
        return new LLVMRealTypes(LLVMCore.LLVMDoubleTypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /**< 80 bit floating point type (X87) */
    public static LLVMRealTypes x86_FP80Type(LLVMContext context) {
        return new LLVMRealTypes(LLVMCore.LLVMX86FP80TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /**< 128 bit floating point type (112-bit mantissa)*/
    public static LLVMRealTypes fp128Type(LLVMContext context) {
        return new LLVMRealTypes(LLVMCore.LLVMFP128TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
    /**< 128 bit floating point type (two 64-bits) */
    public static LLVMRealTypes ppc_FP128Type(LLVMContext context) {
        return new LLVMRealTypes(LLVMCore.LLVMPPCFP128TypeInContext(context.swigtype_p_llvmOpaqueContext));
    }
}
