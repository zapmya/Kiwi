package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.NegateNode;
import de.goto3d.kiwi.compiler.ast.types.RawType;
import de.goto3d.kiwi.compiler.ast.types.Type;
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
        RawType rawType = negateNode.getType().getRawType();
        switch(rawType) {
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
                return this.visitor.getBuilder().createNeg(expressionValue, "negtmp");
            case FLOAT:
            case DOUBLE:
                return this.visitor.getBuilder().createFNeg(expressionValue, "negtmp");
            default:
                // TODO: add error handling code
                return null;
        }
    }
}
