package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMModuleRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 21:10
 */
public class LLVMModule {
    
    protected SWIGTYPE_p_LLVMModuleRef swigtype_p_llvmOpaqueModule;
    
    public LLVMModule(String name, LLVMContext context) {
        this.swigtype_p_llvmOpaqueModule    =
                LLVMCore.LLVMModuleCreateWithNameInContext(name, context.swigtype_p_llvmOpaqueContext);
    }

    public void dump() {
        LLVMCore.LLVMDumpModule(this.swigtype_p_llvmOpaqueModule);
    }

    public String print() {
        return LLVMCore.LLVMPrintModuleToString(this.swigtype_p_llvmOpaqueModule);
    }
    public LLVMFunction getFunction(String functionSignature) {
        return LLVMFunction.getInstance(
                LLVMCore.LLVMGetNamedFunction(this.swigtype_p_llvmOpaqueModule, functionSignature)
        );
    }
}
