package de.goto3d.kiwi.compiler.parser.visitors;

import de.goto3d.kiwi.compiler.SymbolTable;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.statements.ReturnStatementNode;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.ast.visitors.TraversingVisitor;
import de.goto3d.kiwi.compiler.typeconversion.TypeConverter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 22.04.14
 * Time: 17:20
 */
public class TypeConversionVisitor extends TraversingVisitor<AstNode> {

    private final TypeConverter implicitTypeConverter   = new TypeConverter(true);
    private final TypeConverter explicitTypeConverter   = new TypeConverter(false);

    private final SymbolTable symbolTable;

    private InternalFunctionNode currentFunctionNode;

    public TypeConversionVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public AstNode visit(InternalFunctionNode internalFunctionNode) {

        this.currentFunctionNode    = internalFunctionNode;

        super.visit(internalFunctionNode);

        this.currentFunctionNode    = null;

        return internalFunctionNode;
    }

    @Override
    public AstNode visit(AssignmentNode assignmentNode) {
        IdentifierNode identifierNode   = assignmentNode.getIdentifierNode();
        Type targetType                 = identifierNode.getType();

        return this.visit(assignmentNode, targetType);
    }

    @Override
    public AstNode visit(IndexedAssignmentNode assignmentNode) {
        IndexAccessNode identifierNode  = assignmentNode.getIndexAccessNode();
        Type targetType                 = identifierNode.getType();

        return this.visit(assignmentNode, targetType);
    }

    @Override
    public AstNode visit(DeclarationAndAssignmentNode assignmentNode) {
        Type targetType                 = assignmentNode.getType();

        return this.visit(assignmentNode, targetType);
    }

    private AstNode visit(AssignmentNode assignmentNode, Type targetType) {
        ExpressionNode expressionNode   = (ExpressionNode) assignmentNode.getExpressionNode().accept(this);

        expressionNode                  = this.implicitTypeConverter.insertConverterNode(
                expressionNode, targetType
        );

        assignmentNode.setExpressionNode(expressionNode);

        return assignmentNode;
    }

    @Override
    public AstNode visit(CallNode callNode) {

        // traverse children
        super.visit(callNode);

        // lookup function in map
        ExternalFunctionNode functionNode       = this.symbolTable.findFirstFunctionNode(
                callNode.getIdentifierNode().getIdentifier()
        );
        // does function exist ?
        if ( functionNode == null ) {
            // no -> we are done.
            // NB: Errors will be processed during the validation phase
            return callNode;
        }

        // convert arguments
        List<ExpressionNode> expressionNodes    = callNode.getExpressionListNode().getItems();
        List<DeclarationNode> declarationNodes  = functionNode.getArgumentListNode().getItems();
        final int numArgs   = expressionNodes.size();
        for (int i = 0; i < numArgs; i++) {
            ExpressionNode expressionNode   = expressionNodes.get(i);
            DeclarationNode declarationNode = declarationNodes.get(i);

            Type targetType                 = declarationNode.getType();

            expressionNode                  = this.implicitTypeConverter.insertConverterNode(
                    expressionNode, targetType
            );

            expressionNodes.set(i, expressionNode);
        }

        return callNode;
    }

    @Override
    public AstNode visit(OperationNode operationNode) {

        // convert expressions
        ExpressionNode leftHandExpression   = (ExpressionNode)operationNode.getLeftHandExpression().accept(this);
        ExpressionNode rightHandExpression  = (ExpressionNode)operationNode.getRightHandExpression().accept(this);

        Type targetType                     = this.implicitTypeConverter.determineTargetType(
                leftHandExpression,rightHandExpression
        );
        leftHandExpression                  = this.implicitTypeConverter.insertConverterNode(
                leftHandExpression, targetType
        );
        rightHandExpression                 = this.implicitTypeConverter.insertConverterNode(
                rightHandExpression, targetType
        );

        operationNode.setLeftHandExpression(leftHandExpression);
        operationNode.setRightHandExpression(rightHandExpression);
        operationNode.setType(targetType);

        return operationNode;
    }

    @Override
    public AstNode visit(RelationExpressionNode relExpressionNode) {

        // convert expressions
        ExpressionNode leftHandExpression   = (ExpressionNode)relExpressionNode.getLeftHandExpression().accept(this);
        ExpressionNode rightHandExpression  = (ExpressionNode)relExpressionNode.getRightHandExpression().accept(this);

        Type targetType                     = this.implicitTypeConverter.determineTargetType(
                leftHandExpression,rightHandExpression
        );
        leftHandExpression                  = this.implicitTypeConverter.insertConverterNode(
                leftHandExpression, targetType
        );
        rightHandExpression                 = this.implicitTypeConverter.insertConverterNode(
                rightHandExpression, targetType
        );

        relExpressionNode.setLeftHandExpression(leftHandExpression);
        relExpressionNode.setRightHandExpression(rightHandExpression);

        return relExpressionNode;
    }

    @Override
    public AstNode visit(ReturnStatementNode returnStatementNode) {

        ExpressionNode expressionNode   = (ExpressionNode)returnStatementNode.getExpressionNode().accept(this);

        Type targetType                 = this.currentFunctionNode.getType();

        expressionNode                  = this.implicitTypeConverter.insertConverterNode(
                expressionNode, targetType
        );

        returnStatementNode.setExpressionNode(expressionNode);

        return returnStatementNode;
    }

}
