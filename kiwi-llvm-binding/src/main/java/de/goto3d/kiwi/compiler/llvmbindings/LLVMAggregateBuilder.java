package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 21:21
 */
public abstract class LLVMAggregateBuilder extends LLVMVectorBuilder {

    protected LLVMAggregateBuilder(LLVMContext context) {
        super(context);
    }
    
    public LLVMValue createExtractValue(LLVMAggregate aggregate, long index, String name) {
        return new LLVMValue(
                    LLVMCore.LLVMBuildExtractValue(
                            this.swigtype_p_llvmOpaqueBuilder,
                            aggregate.swigtype_p_llvmOpaqueValue,
                            index,
                            name
                    )
        );
    }
    
    public LLVMValue createInsertValue(LLVMAggregate aggregate, LLVMValue element, long index, String name) {
        return new LLVMValue(
                    LLVMCore.LLVMBuildInsertValue(
                            this.swigtype_p_llvmOpaqueBuilder,
                            aggregate.swigtype_p_llvmOpaqueValue,
                            element.swigtype_p_llvmOpaqueValue,
                            index,
                            name
                    )
        );
    }
    
    public LLVMPointer createGetElementPtr(LLVMPointer pointer, LLVMValue[] indices, String name) {
        return LLVMPointer.getInstance(
                LLVMCore.LLVMBuildGEP(
                    this.swigtype_p_llvmOpaqueBuilder,
                    pointer.swigtype_p_llvmOpaqueValue,
                    LLVMValue.convertArray(indices).cast(),
                    indices.length,
                    name
                )
        );
    }
}
