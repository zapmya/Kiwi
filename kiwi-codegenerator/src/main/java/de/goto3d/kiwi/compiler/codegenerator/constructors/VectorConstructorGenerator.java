package de.goto3d.kiwi.compiler.codegenerator.constructors;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.constructors.VectorConstructorNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeGenerator;
import de.goto3d.kiwi.compiler.llvmbindings.*;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by da gru on 29.12.14.
 *
 */
public class VectorConstructorGenerator extends CodeGeneratorBase<VectorConstructorNode> {

    private final TypeGenerator typeGenerator;

    public VectorConstructorGenerator(CodeGeneratorVisitor visitor, TypeGenerator typeGenerator) {
        super(visitor);
        this.typeGenerator  = typeGenerator;
    }

    @Override
    public LLVMValue generateCode(VectorConstructorNode vectorConstructorNode) {

        List<ExpressionNode> items          = vectorConstructorNode.getExpressionListNode().getItems();
        final int numItems                  = items.size();
        final int numConstantExporessiosn   = this.countConstantExpressions(items);

        LLVMValue[] values  = new LLVMValue[numItems];

        LLVMBuilder builder                 = this.visitor.getBuilder();

        if ( numConstantExporessiosn < numItems ) {
            // create new alloca
            LLVMBaseType llvmBaseType   = this.typeGenerator.generateCode(vectorConstructorNode.getType());
            LLVMPointer vecPointer      = builder.createAlloca(llvmBaseType, "vecPtr");
            LLVMVector llvmVector       = builder.createLoad(vecPointer, "vec").cast(LLVMVector.class);

            for (int i = 0; i < numItems; i++) {
                ExpressionNode expressionNode   = items.get(i);
                values[i]                       = expressionNode.accept(this.visitor);
                LLVMValue index                 = LLVMConstantInt.getInstance(
                        LLVMIntTypes.int32Type(), String.valueOf(i), 10
                );

                llvmVector  = builder.createInsertElement(llvmVector, values[i], index, "vecpos_"+i);
            }
            return llvmVector;
        }

        // generate values
        for (int i = 0; i < numItems; i++) {
            ExpressionNode expressionNode   = items.get(i);
            values[i]                       = expressionNode.accept(this.visitor);
        }

        return LLVMVector.getInstance(values);
    }

    private int countConstantExpressions(List<ExpressionNode> expressionNodes) {
        int cnt = 0;
        for (ExpressionNode expressionNode : expressionNodes) {
            if ( expressionNode.isConstant() ) {
                cnt++;
            }
        }
        return cnt;
    }
}
