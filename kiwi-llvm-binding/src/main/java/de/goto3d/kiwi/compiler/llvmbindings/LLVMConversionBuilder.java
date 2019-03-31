package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 13:43
 */
public abstract class LLVMConversionBuilder extends LLVMMemoryBuilder {
    
    protected LLVMConversionBuilder(LLVMContext context) {
        super(context);
    }
    
    public LLVMValue createTruncate(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildTrunc(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createZExt(LLVMValue value, LLVMType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildZExt(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createSExt(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildSExt(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createFpTrunc(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildFPTrunc(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createFpExt(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildFPExt(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createFpToUI(LLVMValue value, LLVMType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildFPToUI(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createFpToSI(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildFPToSI(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createUIToFp(LLVMValue value, LLVMType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildUIToFP(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createSIToFp(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildSIToFP(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createPtrToInt(LLVMValue value, LLVMType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildPtrToInt(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createIntToPtr(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildIntToPtr(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }

    public LLVMValue createBitCast(LLVMValue value, LLVMBaseType destinationType, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildBitCast(
                    this.swigtype_p_llvmOpaqueBuilder,
                    value.swigtype_p_llvmOpaqueValue,
                    destinationType.swigtype_p_llvmOpaqueType,
                    name
                )
        );
    }
}
