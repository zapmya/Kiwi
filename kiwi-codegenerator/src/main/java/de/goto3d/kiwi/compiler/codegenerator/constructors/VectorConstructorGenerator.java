package de.goto3d.kiwi.compiler.codegenerator.constructors;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.constructors.VectorConstructorNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMVector;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

import java.util.List;

/**
 * Created by da gru on 29.12.14.
 *
 */
public class VectorConstructorGenerator extends CodeGeneratorBase<VectorConstructorNode> {

    public VectorConstructorGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(VectorConstructorNode vectorConstructorNode) {

        List<ExpressionNode> items  = vectorConstructorNode.getExpressionListNode().getItems();

        // generate values
        final int numItems  = items.size();
        LLVMValue[] values  = new LLVMValue[numItems];
        for (int i = 0; i < numItems; i++) {
            ExpressionNode expressionNode   = items.get(i);
            values[i]                       = expressionNode.accept(this.visitor);
        }

        return LLVMVector.getInstance(values);
    }
}
