package de.goto3d.kiwi.compiler.codegenerator.functions;

import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMFunction;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.03.13
 * Time: 07:00
 */
public class ExternalFunctionGenerator<T extends ExternalFunctionNode> extends CodeGeneratorBase<T> {

    protected final PrototypeGenerator prototypeGenerator;

    public ExternalFunctionGenerator(CodeGeneratorVisitor visitor, PrototypeGenerator prototypeGenerator) {
        super(visitor);
        this.prototypeGenerator = prototypeGenerator;
    }

    @Override
    public LLVMFunction generateCode(T functionNode) {
        return prototypeGenerator.generateCode(functionNode);
    }
}
