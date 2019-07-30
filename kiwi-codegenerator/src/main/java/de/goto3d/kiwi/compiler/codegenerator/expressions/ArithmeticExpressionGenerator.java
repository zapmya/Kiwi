package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.OperationNode;
import de.goto3d.kiwi.compiler.ast.expressions.Operator;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBuilder;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.02.13
 * Time: 06:54
 */
public class ArithmeticExpressionGenerator extends CodeGeneratorBase<OperationNode> {

    public ArithmeticExpressionGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(OperationNode operationNode) {

        ExpressionNode leftHandExpression = operationNode.getLeftHandExpression();
        LLVMValue leftValue = leftHandExpression.accept(this.visitor);
        if ( leftValue == null ) {
            // TODO: add error handling code
            return null;
        }
        ExpressionNode rightHandExpression = operationNode.getRightHandExpression();
        LLVMValue rightValue = rightHandExpression.accept(this.visitor);
        if ( rightValue == null ) {
            // TODO: add error handling code
            return null;
        }

        // determine target type
        // NB: the parser inserts type conversion nodes
        // This way we can be sure the left hand and right hand types are equal
        Type targetType = operationNode.getType();

        if ( !(targetType instanceof PrimitiveType) ) {
            throw new IllegalArgumentException("type MUST be a primitive type");
        }
        PrimitiveType primitiveTargetType   = (PrimitiveType)targetType;

        switch(targetType.getRawType()) {
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
                return this.generateIntOperation(leftValue, rightValue, operationNode.getOperator());
            case FLOAT:
            case DOUBLE:
                return this.generateFloatOperation(leftValue, rightValue, operationNode.getOperator());
            default:
                // TODO: add error handling code
                return null;
        }
    }

    private LLVMValue generateIntOperation(LLVMValue leftValue, LLVMValue rightValue, Operator operator) {
        LLVMBuilder builder = this.visitor.getBuilder();
        switch(operator) {
            case ADDITION:
                return builder.createAdd(leftValue, rightValue, "iaddtmp");
            case SUBTRACTION:
                return builder.createSub(leftValue, rightValue, "isubtmp");
            case MULTIPLICATION:
                return builder.createMul(leftValue, rightValue, "imultmp");
            case DIVISION:
                return builder.createSDiv(leftValue, rightValue, "idivtmp");
            case REMAINDER:
                return builder.createSRem(leftValue, rightValue, "iremtmp");
            default:
                return null;
        }
    }

    private LLVMValue generateFloatOperation(LLVMValue leftValue, LLVMValue rightValue, Operator operator) {
        LLVMBuilder builder = this.visitor.getBuilder();
        switch(operator) {
            case ADDITION:
                return builder.createFAdd(leftValue, rightValue, "faddtmp");
            case SUBTRACTION:
                return builder.createFSub(leftValue, rightValue, "fsubtmp");
            case MULTIPLICATION:
                return builder.createFMul(leftValue, rightValue, "fmultmp");
            case DIVISION:
                return builder.createFDiv(leftValue, rightValue, "fdivtmp");
            case REMAINDER:
                return builder.createFRem(leftValue, rightValue, "fremtmp");
            default:
                return null;
        }
    }
}
