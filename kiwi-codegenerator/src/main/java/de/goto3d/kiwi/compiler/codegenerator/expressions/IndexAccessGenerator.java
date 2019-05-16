package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.expressions.IndexAccessNode;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.VariableStore;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMPointer;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMVector;

import java.util.List;

/**
 * Created by da da gru on 07.01.16.
 *
 */
public class IndexAccessGenerator extends CodeGeneratorBase<IndexAccessNode>{

    public IndexAccessGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(IndexAccessNode indexAccessNode) {

        List<ExpressionNode> indexExpressions   = indexAccessNode.getDimExpressionsNode().getItems();
        final int numIndexExpressions           = indexExpressions.size();
        LLVMValue[] indexValues                 = new LLVMValue[numIndexExpressions];
        for (int i = 0; i < numIndexExpressions; i++) {
            ExpressionNode indexExpressionNode  = indexExpressions.get(i);
            indexValues[i]  = indexExpressionNode.accept(this.visitor);
        }

        IdentifierNode identifierNode   = indexAccessNode.getIdentifierNode();
        String varName                  = identifierNode.getIdentifier();
        VariableStore variableStore     = this.visitor.getCurrentVariableStore();
        LLVMPointer ptr                 = variableStore.findVariable(varName);

        Type type                       = identifierNode.getType();
        // is it a vector type ?
        if ( type.isVectorType() ) {
            // yes -> generate vector extraction code
            // load vector
            LLVMVector vector   = this.visitor.getBuilder().createLoad(ptr, varName).cast(LLVMVector.class);
            return this.visitor.getBuilder().createExtractElement(vector, indexValues[0], "elem");
        }

        // no -> it has to be an array type
        LLVMPointer arrayPtr= this.visitor.getBuilder().createLoad(ptr, varName).cast(LLVMPointer.class);
        LLVMPointer elemPtr = this.visitor.getBuilder().createGetElementPtr(arrayPtr, indexValues, "elemPtr");
        return this.visitor.getBuilder().createLoad(elemPtr,"elem");
    }
}
