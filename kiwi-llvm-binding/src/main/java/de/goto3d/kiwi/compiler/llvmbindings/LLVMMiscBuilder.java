package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 22:02
 */
public abstract class LLVMMiscBuilder extends LLVMConversionBuilder {
    
    protected LLVMMiscBuilder(LLVMContext context) {
        super(context);
    }
    
    public LLVMValue createPhi(LLVMType type, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildPhi(
                        this.swigtype_p_llvmOpaqueBuilder,
                        type.swigtype_p_llvmOpaqueType,
                        name
                )
        );
    }

    public LLVMCall createCall(LLVMFunction function, LLVMValue[] parameters, String name) {
        return LLVMCall.getInstance(
                LLVMCore.LLVMBuildCall(
                        this.swigtype_p_llvmOpaqueBuilder,
                        function.swigtype_p_llvmOpaqueValue,
                        LLVMValue.convertArray(parameters).cast(),
                        parameters.length,
                        name
                ),
                LLVMCall.class
        );
    }
}
