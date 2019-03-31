package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.NegateNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 02.04.13
 * Time: 08:57
 */
public class NegateGenerator extends CodeGeneratorBase<NegateNode> {

    public NegateGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(NegateNode negateNode) {
        LLVMValue expressionValue   = negateNode.getOperand().accept(this.visitor);
        return this.visitor.getBuilder().createFNeg(expressionValue, "negtmp");
    }
}
