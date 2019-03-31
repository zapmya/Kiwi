package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMPointer;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 02.04.13
 * Time: 09:04
 */
public class IdentifierGenerator extends CodeGeneratorBase<IdentifierNode> {

    public IdentifierGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(IdentifierNode identifierNode) {

        String varName      = identifierNode.getIdentifier();
        LLVMPointer alloca  = this.visitor.getCurrentVariableStore().findVariable(varName);

        return this.visitor.getBuilder().createLoad(alloca, varName);
    }
}
