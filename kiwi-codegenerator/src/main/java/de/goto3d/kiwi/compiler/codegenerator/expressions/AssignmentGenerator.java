package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.AssignmentNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.VariableStore;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMPointer;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 11.02.13
 * Time: 06:35
 */
public class AssignmentGenerator extends CodeGeneratorBase<AssignmentNode> {

    public AssignmentGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(AssignmentNode assignmentNode) {

        // Emit the initializer before adding the variable to scope, this prevents
        // the initializer from referencing the variable itself, and permits stuff
        // like this:
        //  var a = 1 in
        //    var a = a in ...   # refers to outer 'a'.
        LLVMValue expressionValue   = assignmentNode.getExpressionNode().accept(this.visitor);

        String varName  = assignmentNode.getIdentifierNode().getIdentifier();
        // variable already defined ?
        VariableStore variableStore = this.visitor.getCurrentVariableStore();
        LLVMPointer varPointer  = variableStore.findVariable(varName);

        return this.visitor.getBuilder().createStore(expressionValue, varPointer);
    }
}
