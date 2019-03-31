package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.statements.ReturnStatementNode;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 30.04.13
 * Time: 18:21
 */
public class ReturnStatementGenerator extends CodeGeneratorBase<ReturnStatementNode> {

    public ReturnStatementGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(ReturnStatementNode returnStatementNode) {

        ExpressionNode expressionNode   = returnStatementNode.getExpressionNode();
        Type type                       = expressionNode.getType();

        if ( type.getClass() != PrimitiveType.class ) {
            throw new IllegalArgumentException("currently only primitive types are supported");
        }
        PrimitiveType primitiveType   = (PrimitiveType)type;

        if ( primitiveType.getRawType() == RawType.VOID ) {
            this.visitor.getBuilder().createRetVoid();
        } else {
            LLVMValue returnValue = expressionNode.accept(this.visitor);
            this.visitor.getBuilder().createRet(returnValue);
        }

        return null;
    }
}
