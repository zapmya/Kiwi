package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.NumberNode;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.*;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 02.04.13
 * Time: 08:49
 */
public class NumberGenerator extends CodeGeneratorBase<NumberNode> {

    public NumberGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(NumberNode numberNode) {
        switch(((PrimitiveType)numberNode.getType()).getRawType()) {
            case FLOAT:
                return LLVMConstantFP.getInstance(LLVMRealTypes.floatType(), numberNode.getValue().floatValue());
            case DOUBLE:
                return LLVMConstantFP.getInstance(LLVMRealTypes.doubleType(), numberNode.getValue().doubleValue());
            case BYTE:
                return LLVMConstantInt.getInstance(LLVMIntTypes.int8Type(), (BigInteger)numberNode.getValue(), true);
            case SHORT:
                return LLVMConstantInt.getInstance(LLVMIntTypes.int16Type(), (BigInteger)numberNode.getValue(), true);
            case CHAR:
                return LLVMConstantInt.getInstance(LLVMIntTypes.int16Type(), (BigInteger)numberNode.getValue(), false);
            case INT:
                return LLVMConstantInt.getInstance(LLVMIntTypes.int32Type(), (BigInteger)numberNode.getValue(), true);
            case LONG:
                return LLVMConstantInt.getInstance(LLVMIntTypes.int64Type(), (BigInteger)numberNode.getValue(), true);
            default:
                return null;
        }
    }
}
