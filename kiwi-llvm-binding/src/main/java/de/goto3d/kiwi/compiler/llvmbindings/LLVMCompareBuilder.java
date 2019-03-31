package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMIntPredicate;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMRealPredicate;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 13.01.12
 * Time: 21:57
 */
public abstract class LLVMCompareBuilder extends LLVMBinaryBuilder {
    
    protected LLVMCompareBuilder(LLVMContext context) {
        super(context);
    }
    
    public LLVMValue createICmp(LLVMIntPredicate predicate, LLVMValue leftValue, LLVMValue rightValue, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildICmp(
                        this.swigtype_p_llvmOpaqueBuilder,
                        predicate,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
    }

    public LLVMValue createFCmp(LLVMRealPredicate predicate, LLVMValue leftValue, LLVMValue rightValue, String name) {
        return new LLVMValue(
                LLVMCore.LLVMBuildFCmp(
                        this.swigtype_p_llvmOpaqueBuilder,
                        predicate,
                        leftValue.swigtype_p_llvmOpaqueValue,
                        rightValue.swigtype_p_llvmOpaqueValue,
                        name
                )
        );
    }
}
