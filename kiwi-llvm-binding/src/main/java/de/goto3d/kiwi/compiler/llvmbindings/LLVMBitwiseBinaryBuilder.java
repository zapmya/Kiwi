package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 11:31
 */
public abstract class LLVMBitwiseBinaryBuilder extends LLVMCompareBuilder {

    protected LLVMBitwiseBinaryBuilder(LLVMContext context) {
        super(context);
    }

    public LLVMValue createOr(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue or  = new LLVMValue(
                LLVMCore.LLVMBuildOr(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return or;
    }
    public LLVMValue createAnd(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue and  = new LLVMValue(
                LLVMCore.LLVMBuildAnd(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return and;
    }
    public LLVMValue createXor(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue xor  = new LLVMValue(
                LLVMCore.LLVMBuildXor(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return xor;
    }
    public LLVMValue createNot(LLVMValue value, String name) {
        LLVMValue neg  = new LLVMValue(
                LLVMCore.LLVMBuildNot(
                        this.swigtype_p_llvmOpaqueBuilder,
                        value.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return neg;
    }
    public LLVMValue createShl(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue shl  = new LLVMValue(
                LLVMCore.LLVMBuildShl(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return shl;
    }
    public LLVMValue createLShr(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue lShr  = new LLVMValue(
                LLVMCore.LLVMBuildLShr(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return lShr;
    }
    public LLVMValue createAShr(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue aShr  = new LLVMValue(
                LLVMCore.LLVMBuildAShr(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return aShr;
    }
}
