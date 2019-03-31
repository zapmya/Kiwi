package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 18.01.12
 * Time: 16:38
 */
public class LLVMStructType extends LLVMAggregateType {
    
    public LLVMStructType(LLVMType[] types, boolean packed) {
        super(
                LLVMCore.LLVMStructType(
                    LLVMType.convertArray(types).cast(),
                    types.length,
                    packed ? 1 : 0
                )
        );
    }

    public LLVMStructType(LLVMContext context, LLVMType[] types, boolean packed) {
        super(
                LLVMCore.LLVMStructTypeInContext(
                        context.swigtype_p_llvmOpaqueContext,
                        LLVMType.convertArray(types).cast(),
                        types.length,
                        packed ? 1 : 0
                )
        );
    }
}
