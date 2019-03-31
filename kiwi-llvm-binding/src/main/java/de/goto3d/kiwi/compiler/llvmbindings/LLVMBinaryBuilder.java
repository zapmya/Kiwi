package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 11:29
 */
public abstract class LLVMBinaryBuilder extends LLVMTerminatorBuilder {

    protected LLVMBinaryBuilder(LLVMContext context) {
        super(context);
    }

    public LLVMValue createAdd(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue add  = new LLVMValue(
                LLVMCore.LLVMBuildAdd(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return add;
    }
    public LLVMValue createFAdd(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue fAdd  = new LLVMValue(
                LLVMCore.LLVMBuildFAdd(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return fAdd;
    }

    public LLVMValue createSub(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue sub  = new LLVMValue(
                LLVMCore.LLVMBuildSub(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return sub;
    }
    public LLVMValue createFSub(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue fSub  = new LLVMValue(
                LLVMCore.LLVMBuildFSub(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return fSub;
    }

    public LLVMValue createMul(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue mul  = new LLVMValue(
                LLVMCore.LLVMBuildMul(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return mul;
    }
    public LLVMValue createFMul(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue fMul  = new LLVMValue(
                LLVMCore.LLVMBuildFMul(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return fMul;
    }

    public LLVMValue createUDiv(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue uDiv  = new LLVMValue(
                LLVMCore.LLVMBuildUDiv(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return uDiv;
    }
    public LLVMValue createSDiv(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue sDiv  = new LLVMValue(
                LLVMCore.LLVMBuildSDiv(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return sDiv;
    }
    public LLVMValue createFDiv(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue fDiv  = new LLVMValue(
                LLVMCore.LLVMBuildFDiv(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return fDiv;
    }

    public LLVMValue createURem(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue uRem  = new LLVMValue(
                LLVMCore.LLVMBuildURem(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return uRem;
    }
    public LLVMValue createSRem(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue sRem  = new LLVMValue(
                LLVMCore.LLVMBuildSRem(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return sRem;
    }
    public LLVMValue createFRem(LLVMValue leftValue, LLVMValue rightValue, String name) {
        LLVMValue fRem  = new LLVMValue(
                LLVMCore.LLVMBuildFRem(
                        this.swigtype_p_llvmOpaqueBuilder,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return fRem;
    }

    public LLVMValue createNeg(LLVMValue value, String name) {
        LLVMValue neg  = new LLVMValue(
                LLVMCore.LLVMBuildNeg(
                        this.swigtype_p_llvmOpaqueBuilder,
                        value.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return neg;
    }
    public LLVMValue createFNeg(LLVMValue value, String name) {
        LLVMValue fNeg  = new LLVMValue(
                LLVMCore.LLVMBuildFNeg(
                        this.swigtype_p_llvmOpaqueBuilder,
                        value.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
        return fNeg;
    }

}
