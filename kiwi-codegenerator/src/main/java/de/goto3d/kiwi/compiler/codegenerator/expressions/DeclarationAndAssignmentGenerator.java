package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.DeclarationAndAssignmentNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.VariableStore;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeGenerator;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBaseType;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBuilder;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMPointer;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 11.02.13
 * Time: 06:35
 */
public class DeclarationAndAssignmentGenerator extends CodeGeneratorBase<DeclarationAndAssignmentNode> {

    private final TypeGenerator typeGenerator;

    public DeclarationAndAssignmentGenerator(CodeGeneratorVisitor visitor, TypeGenerator typeGenerator) {
        super(visitor);
        this.typeGenerator = typeGenerator;
    }

    @Override
    public LLVMValue generateCode(DeclarationAndAssignmentNode assignmentNode) {

        // Emit the initializer before adding the variable to scope, this prevents
        // the initializer from referencing the variable itself, and permits stuff
        // like this:
        //  var a = 1 in
        //    var a = a in ...   # refers to outer 'a'.
        LLVMValue expressionValue   = assignmentNode.getExpressionNode().accept(this.visitor);

        LLVMBuilder builder         = this.visitor.getBuilder();
        // create new alloca
        LLVMBaseType llvmBaseType   = this.typeGenerator.generateCode(assignmentNode.getType());
        String varName              = assignmentNode.getIdentifierNode().getIdentifier();
        LLVMPointer varPointer      = builder.createAlloca(llvmBaseType, varName);
        // and store it
        VariableStore variableStore = this.visitor.getCurrentVariableStore();
        variableStore.setVariable(varName, varPointer);

        return builder.createStore(expressionValue, varPointer);
    }
}
