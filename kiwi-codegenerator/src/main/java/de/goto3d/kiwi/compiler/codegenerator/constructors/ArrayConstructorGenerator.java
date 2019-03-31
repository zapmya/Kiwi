package de.goto3d.kiwi.compiler.codegenerator.constructors;

import de.goto3d.kiwi.compiler.ast.constructors.ArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.functions.PrototypeGenerator;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeGenerator;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBaseType;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMCall;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMFunction;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by da da gru on 13.04.16.
 *
 */
public class ArrayConstructorGenerator extends CodeGeneratorBase<ArrayConstructorNode> {

    private final PrototypeGenerator prototypeGenerator;

    private final TypeGenerator typeGenerator;

    public ArrayConstructorGenerator(
            CodeGeneratorVisitor visitor,
            PrototypeGenerator prototypeGenerator,
            TypeGenerator typeGenerator) {
        super(visitor);
        this.prototypeGenerator = prototypeGenerator;
        this.typeGenerator      = typeGenerator;
    }


    @Override
    public LLVMValue generateCode(ArrayConstructorNode astNode) {

        // Look up the name in the global module table.
        String functionName                     = "kiwi_lang_alloc";
        LLVMFunction function                   = this.prototypeGenerator.getFunction(functionName);

        // generate code for size parameter
        DimExpressionsNode dimExpressionNode = astNode.getDimExpressionsNode();
        List<ExpressionNode> expressionNodes = dimExpressionNode.getItems();
        // TODO: add support for multiple dimensions
        final int numExpressions = expressionNodes.size();
        LLVMValue[] parameters  = new LLVMValue[numExpressions];
        RawType rawType = astNode.getType().getRawType();
        for ( int i = 0; i < numExpressions; i++ ) {
            // TODO: add multiple of raw type size
            ExpressionNode expressionNode = expressionNodes.get(i);
            // is it a byte type ?
            if ( rawType != RawType.BYTE ) {
                // no -> insert multiply node
                NumberNode multiplier = new NumberNode(null, new BigInteger(String.valueOf(rawType.bits/8)));
                expressionNode = new OperationNode(null, Operator.MULTIPLICATION, expressionNode, multiplier);
                expressionNode.setType(new PrimitiveType(RawType.INT));
            }
            parameters[i]   = expressionNode.accept(this.visitor);
        }

        // yes -> create usual call
        LLVMCall callValue = this.visitor.getBuilder().createCall(function, parameters, functionName);
        callValue.setCallingConvention(function.getCallingConvention());

        // do we need to cast the resulting pointer ?
        if ( astNode.getType().getRawType() == RawType.BYTE ) {
            // no -> we are done
            return callValue;
        }

        // yes -> add bitcast
        LLVMBaseType destType   = this.typeGenerator.generateCode(astNode.getType());

        return this.visitor.getBuilder().createBitCast(callValue, destType, "casted_"+functionName);
    }
}
