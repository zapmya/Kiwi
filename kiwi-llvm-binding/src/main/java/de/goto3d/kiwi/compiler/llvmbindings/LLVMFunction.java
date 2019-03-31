package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCallConv;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 22:47
 */
public class LLVMFunction extends LLVMValue {

    private final LLVMArgument[] arguments;

/*    private  LLVMFunction(SWIGTYPE_p_LLVMOpaqueValue swigtype_p_llvmOpaqueValue) {
        super(swigtype_p_llvmOpaqueValue);

        // build argument array
        this.arguments  = this.getArguments();
    }*/

    public LLVMFunction(String name, LLVMBaseType returnType, TypeAndName[] typeAndNames, LLVMModule module) {
        super(
                LLVMCore.LLVMAddFunction(
                    module.swigtype_p_llvmOpaqueModule,
                    name,
                    createSignature(returnType, typeAndNames).swigtype_p_llvmOpaqueType
                )
        );

        // build argument array
        this.arguments  = this.getArguments(typeAndNames);
    }

    private static LLVMFunctionType createSignature(LLVMBaseType returnType, TypeAndName[] typeAndNames) {
        int numParams               = typeAndNames.length;
        LLVMBaseType[] paramTypes   = new LLVMBaseType[numParams];
        for ( int i = 0; i < numParams; i++ ) {
            paramTypes[i]   = typeAndNames[i].type;
        }
        return new LLVMFunctionType(returnType, paramTypes);
    }

    protected static LLVMFunction getInstance(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        return LLVMValue.getInstance(swigtype_p_llvmOpaqueValue, LLVMFunction.class);
    }

    private LLVMArgument[] getArguments(TypeAndName[] typeAndNames) {
        // build argument array
        int numArgs         = (int) LLVMCore.LLVMCountParams(this.swigtype_p_llvmOpaqueValue);
        LLVMArgument[] args = new LLVMArgument[numArgs];
        for ( int i = 0; i < numArgs; i++ ) {
            args[i] = LLVMArgument.getInstance(
                    LLVMCore.LLVMGetParam(this.swigtype_p_llvmOpaqueValue, i),
                    typeAndNames[i].name
            );
        }
        return args;
    }

    public LLVMBasicBlock getEntryBlock() {
        return new LLVMBasicBlock(
                LLVMCore.LLVMGetEntryBasicBlock(this.swigtype_p_llvmOpaqueValue)
        );
    }

    public void setCallingConvention(LLVMCallConv callingConvention) {
        LLVMCore.LLVMSetFunctionCallConv(this.swigtype_p_llvmOpaqueValue, callingConvention.swigValue());
    }

    public LLVMCallConv getCallingConvention() {
        return LLVMCallConv.swigToEnum((int)LLVMCore.LLVMGetFunctionCallConv(this.swigtype_p_llvmOpaqueValue));
    }

    public LLVMArgument[] getArguments() {
        return arguments;
    }
}
