package de.goto3d.kiwi.compiler.codegenerator.functions;

import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.VariableStore;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeGenerator;
import de.goto3d.kiwi.compiler.llvmbindings.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.03.13
 * Time: 08:48
 */
public class InternalFunctionGenerator extends ExternalFunctionGenerator<InternalFunctionNode> {

    private final TypeGenerator typeGenerator   = new TypeGenerator();

    public InternalFunctionGenerator(CodeGeneratorVisitor visitor, PrototypeGenerator prototypeGenerator) {
        super(visitor, prototypeGenerator);
    }

    @Override
    public LLVMFunction generateCode(InternalFunctionNode functionNode) {
        LLVMFunction function   = super.generateCode(functionNode);

        this.visitor.setCurrentFunction(function);

        // create new block
        LLVMBasicBlock block    = new LLVMBasicBlock(function, functionNode.getName()+"_entry");
        this.visitor.getBuilder().setInsertPosition(block);

        // create "stores" for parameters
        VariableStore variableStore             = this.visitor.getCurrentVariableStore();
        LLVMBuilder builder                     = this.visitor.getBuilder();
        LLVMArgument[] arguments                = function.getArguments();
        List<DeclarationNode> declarationNodes  = functionNode.getArgumentListNode().getItems();
        final int numArgs           = arguments.length;
        for (int i = 0; i < numArgs; i++) {
            LLVMArgument arg    = arguments[i];
            String name         = arg.getName();
            LLVMPointer pointer = builder.createAlloca(
                    this.typeGenerator.generateCode(declarationNodes.get(i).getType()), name
            );
            variableStore.setVariable(name, pointer);
            builder.createStore(arg, pointer);
        }

        // generate code for statement block
        functionNode.getBlockNode().accept(this.visitor);

        this.visitor.setCurrentFunction(null);

        return function;
    }
}