package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 14:34
 */
public abstract class LLVMVectorBuilder extends LLVMBitwiseBinaryBuilder {

    protected LLVMVectorBuilder(LLVMContext context) {
        super(context);
    }
    
    public LLVMValue createExtractElement(LLVMVector vector, LLVMValue index, String name) {
        return new LLVMValue(
                  LLVMCore.LLVMBuildExtractElement(
                            this.swigtype_p_llvmOpaqueBuilder,
                            vector.swigtype_p_llvmOpaqueValue,
                            index.swigtype_p_llvmOpaqueValue,
                            name
                )
        );
    }

    public LLVMVector createInsertElement(LLVMVector vector, LLVMValue element, LLVMValue index, String name) {
        return LLVMVector.getInstance(
                  LLVMCore.LLVMBuildInsertElement(
                            this.swigtype_p_llvmOpaqueBuilder,
                            vector.swigtype_p_llvmOpaqueValue,
                            element.swigtype_p_llvmOpaqueValue,
                            index.swigtype_p_llvmOpaqueValue,
                            name
                  )
        );
    }

    public LLVMValue createShuffleVector(LLVMVector v1, LLVMVector v2, LLVMValue mask, String name) {
        return new LLVMValue(
                  LLVMCore.LLVMBuildShuffleVector(
                            this.swigtype_p_llvmOpaqueBuilder,
                            v1.swigtype_p_llvmOpaqueValue,
                            v2.swigtype_p_llvmOpaqueValue,
                            mask.swigtype_p_llvmOpaqueValue,
                            name
                  )
        );
    }
}
