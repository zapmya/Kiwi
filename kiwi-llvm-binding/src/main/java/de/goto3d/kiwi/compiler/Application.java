package de.goto3d.kiwi.compiler;

import de.goto3d.kiwi.compiler.llvmbindings.*;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.01.12
 * Time: 23:41
 */
public class Application {

    public static void main(String[] args) {

        LLVMContext context = LLVMContext.getGlobalContext();

        LLVMModule module   = new LLVMModule("Das Modul", context);

        LLVMBaseType returnType = LLVMType.voidType();

        TypeAndName[] typeAndNames  = new TypeAndName[]{
                new TypeAndName(LLVMRealTypes.doubleType(), "x"),
                new TypeAndName(LLVMRealTypes.doubleType(), "y")
        };
        LLVMFunction function   = new LLVMFunction("add",returnType,typeAndNames, module);

        LLVMBasicBlock basicBlock   = new LLVMBasicBlock(context, function, "entry");

        LLVMBuilder builder = new LLVMBuilder(context);
        builder.setInsertPosition(basicBlock);

        LLVMValue leftValue = LLVMConstantFP.getInstance(LLVMRealTypes.doubleType(context), 7);
        LLVMValue rightValue= LLVMConstantFP.getInstance(LLVMRealTypes.doubleType(context), 6);

        LLVMValue addResult = builder.createFAdd(leftValue, rightValue, "addtmp");
        LLVMValue otherValue= LLVMConstantFP.getInstance(LLVMRealTypes.doubleType(context), 10);

        LLVMValue mulResult = builder.createFMul(addResult, otherValue, "multmp");

        builder.createRet(mulResult);

        module.dump();
    }
}
