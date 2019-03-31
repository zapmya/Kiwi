package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 11:36
 */
public abstract class LLVMMemoryBuilder extends LLVMAggregateBuilder {

    protected LLVMMemoryBuilder(LLVMContext context) {
        super(context);
    }
    
    public LLVMPointer createAlloca(LLVMBaseType type, String name) {
        return LLVMPointer.getInstance(
                LLVMCore.LLVMBuildAlloca(
                        this.swigtype_p_llvmOpaqueBuilder,
                        type.swigtype_p_llvmOpaqueType,
                        name
                    )
                );
    }

    public LLVMValue createLoad(LLVMPointer pointer, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildLoad(
                        this.swigtype_p_llvmOpaqueBuilder,
                        pointer.swigtype_p_llvmOpaqueValue,
                        name
                    )
                );
    }

    public LLVMValue createStore(LLVMValue value, LLVMPointer pointer) {
        return new LLVMValue(
                LLVMCore.LLVMBuildStore(
                        this.swigtype_p_llvmOpaqueBuilder,
                        value.swigtype_p_llvmOpaqueValue,
                        pointer.swigtype_p_llvmOpaqueValue
                    )
                );
    }
}
