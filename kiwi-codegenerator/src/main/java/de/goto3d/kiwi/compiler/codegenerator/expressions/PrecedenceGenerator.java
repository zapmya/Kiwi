package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.PrecedenceNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 02.04.13
 * Time: 08:45
 */
public class PrecedenceGenerator extends CodeGeneratorBase<PrecedenceNode> {

    public PrecedenceGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(PrecedenceNode precedenceNode) {
        return precedenceNode.getExpressionNode().accept(this.visitor);
    }
}
