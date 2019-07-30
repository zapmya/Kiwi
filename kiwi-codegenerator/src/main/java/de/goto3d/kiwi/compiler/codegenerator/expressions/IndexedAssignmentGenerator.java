package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.expressions.IndexedAssignmentNode;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.VariableStore;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMPointer;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMVector;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 11.02.13
 * Time: 06:35
 */
public class IndexedAssignmentGenerator extends CodeGeneratorBase<IndexedAssignmentNode> {

    public IndexedAssignmentGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(IndexedAssignmentNode assignmentNode) {

        // Emit the initializer before adding the variable to scope, this prevents
        // the initializer from referencing the variable itself, and permits stuff
        // like this:
        //  var a = 1 in
        //    var a = a in ...   # refers to outer 'a'.
        LLVMValue expressionValue   = assignmentNode.getExpressionNode().accept(this.visitor);

        // get variable name
        IdentifierNode identifierNode   = assignmentNode.getIdentifierNode();
        String varName  = identifierNode.getIdentifier();
        VariableStore variableStore = this.visitor.getCurrentVariableStore();
        LLVMPointer varPointer  = variableStore.findVariable(varName);

        // get index values
        List<ExpressionNode> indexExpressions   = assignmentNode.getIndexAccessNode().getDimExpressionsNode().getItems();
        final int numIndexExpressions           = indexExpressions.size();
        LLVMValue[] indexValues                 = new LLVMValue[numIndexExpressions];
        for (int i = 0; i < numIndexExpressions; i++) {
            ExpressionNode indexExpressionNode  = indexExpressions.get(i);
            indexValues[i]  = indexExpressionNode.accept(this.visitor);
        }

        // distinguish array from vector types
        Type type   = identifierNode.getType();
        // is it a vector type ?
        if ( type.isVectorType() ) {
            // yes -> generate code for element insertion
            // load vector
            LLVMVector vector   = this.visitor.getBuilder().createLoad(varPointer, varName).cast(LLVMVector.class);
            // and insert element
            LLVMValue newVector = this.visitor.getBuilder().createInsertElement(vector, expressionValue, indexValues[0], "elem");

            return this.visitor.getBuilder().createStore(newVector, varPointer);
        }

        // no -> it has to be an array type
        // load array pointer
        LLVMPointer arrayPtr= this.visitor.getBuilder().createLoad(varPointer, varName).cast(LLVMPointer.class);
        LLVMPointer elemPtr = this.visitor.getBuilder().createGetElementPtr(arrayPtr, indexValues, "elemPtr");
        return this.visitor.getBuilder().createStore(expressionValue, elemPtr);
    }
}
