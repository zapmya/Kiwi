package de.goto3d.kiwi.compiler.codegenerator.expressions;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.Relation;
import de.goto3d.kiwi.compiler.ast.expressions.RelationExpressionNode;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBuilder;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMIntPredicate;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMRealPredicate;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.04.13
 * Time: 17:36
 */
public class RelationalExpressionGenerator  extends CodeGeneratorBase<RelationExpressionNode> {

    public RelationalExpressionGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(RelationExpressionNode relationExpressionNode) {

        ExpressionNode leftHandExpression   = relationExpressionNode.getLeftHandExpression();
        Type sourceType                     = leftHandExpression.getType();
        if ( sourceType.getClass() != PrimitiveType.class ) {
            throw new UnsupportedOperationException("Currently only primitive types are supported");
        }
        PrimitiveType primitiveType         = (PrimitiveType)sourceType;

        LLVMValue leftValue = leftHandExpression.accept(this.visitor);
        if ( leftValue == null ) {
            // TODO: add error handling code
            return null;
        }
        LLVMValue rightValue = relationExpressionNode.getRightHandExpression().accept(this.visitor);
        if ( rightValue == null ) {
            // TODO: add error handling code
            return null;
        }

        switch(primitiveType.getRawType()) {
            case FLOAT:
            case DOUBLE:
                return createRealComparisonCode(relationExpressionNode.getRelation(), leftValue, rightValue);
            default:
                return createIntegerComparisonCode(relationExpressionNode.getRelation(), leftValue, rightValue);
        }
    }

    private LLVMValue createIntegerComparisonCode(Relation relation, LLVMValue leftValue, LLVMValue rightValue) {
        LLVMBuilder builder = this.visitor.getBuilder();
        switch(relation) {
            case EQUAL:
                return builder.createICmp(LLVMIntPredicate.LLVMIntEQ, leftValue, rightValue, "cmptmp");
            case GREATER_OR_EQUAL:
                return builder.createICmp(LLVMIntPredicate.LLVMIntSGE, leftValue, rightValue, "cmptmp");
            case GREATER_THAN:
                return builder.createICmp(LLVMIntPredicate.LLVMIntSGT, leftValue, rightValue, "cmptmp");
            case LOWER_OR_EQUAL:
                return builder.createICmp(LLVMIntPredicate.LLVMIntSLE, leftValue, rightValue, "cmptmp");
            case LOWER_THAN:
                return builder.createICmp(LLVMIntPredicate.LLVMIntSLT, leftValue, rightValue, "cmptmp");
            case NOT_EQUAL:
                return builder.createICmp(LLVMIntPredicate.LLVMIntNE, leftValue, rightValue, "cmptmp");
            default:
                // TODO: add error handling code
                return null;
        }
    }

    private LLVMValue createRealComparisonCode(Relation relation, LLVMValue leftValue, LLVMValue rightValue) {
        LLVMBuilder builder = this.visitor.getBuilder();
        switch(relation) {
            case EQUAL:
                return builder.createFCmp(LLVMRealPredicate.LLVMRealOEQ, leftValue, rightValue, "cmptmp");
            case GREATER_OR_EQUAL:
                return builder.createFCmp(LLVMRealPredicate.LLVMRealOGE, leftValue, rightValue, "cmptmp");
            case GREATER_THAN:
                return builder.createFCmp(LLVMRealPredicate.LLVMRealOGT, leftValue, rightValue, "cmptmp");
            case LOWER_OR_EQUAL:
                return builder.createFCmp(LLVMRealPredicate.LLVMRealOLE, leftValue, rightValue, "cmptmp");
            case LOWER_THAN:
                return builder.createFCmp(LLVMRealPredicate.LLVMRealOLT, leftValue, rightValue, "cmptmp");
            case NOT_EQUAL:
                return builder.createFCmp(LLVMRealPredicate.LLVMRealONE, leftValue, rightValue, "cmptmp");
            default:
                // TODO: add error handling code
                return null;
        }
    }
}
