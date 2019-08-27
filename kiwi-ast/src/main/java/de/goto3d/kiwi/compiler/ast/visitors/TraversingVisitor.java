package de.goto3d.kiwi.compiler.ast.visitors;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.classes.ClassNode;
import de.goto3d.kiwi.compiler.ast.compilation.CompilationItemsNode;
import de.goto3d.kiwi.compiler.ast.constructors.ArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.constructors.NativeArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.constructors.VectorConstructorNode;
import de.goto3d.kiwi.compiler.ast.statements.*;
import de.goto3d.kiwi.compiler.ast.types.StructNode;
import de.goto3d.kiwi.compiler.ast.types.TypeConversionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.06.14
 * Time: 09:09
 */
public abstract class TraversingVisitor<T extends AstNode> implements Visitor<T> {

    @Override
    @SuppressWarnings("unchecked")
    public T visit(NumberNode numberNode) {
        // this is a terminal! We cannot step down any further.
        return (T) numberNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(NegateNode negateNode) {
        // traverse node
        negateNode.getOperand().accept(this);
        return (T) negateNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(IdentifierNode identifierNode) {
        // this is a terminal! We cannot step down any further.
        return (T) identifierNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(AssignmentNode assignmentNode) {
        // traverse nodes
        assignmentNode.getIdentifierNode().accept(this);
        assignmentNode.getExpressionNode().accept(this);

        return (T) assignmentNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(DeclarationAndAssignmentNode declarationAndAssignmentNode) {
        // traverse node
        declarationAndAssignmentNode.getDeclarationNode().accept(this);
        declarationAndAssignmentNode.getExpressionNode().accept(this);

        return (T) declarationAndAssignmentNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(IndexedAssignmentNode indexedAssignmentNode) {
        // traverse node
        indexedAssignmentNode.getIndexAccessNode().accept(this);
        indexedAssignmentNode.getExpressionNode().accept(this);

        return (T) indexedAssignmentNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(StatementListNode statementListNode) {
        // traverse child nodes
        for (StatementNode statementNode : statementListNode.getItems()) {
            statementNode.accept(this);
        }
        return (T) statementListNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(CallNode callNode) {
        // traverse child nodes
        callNode.getIdentifierNode().accept(this);
        callNode.getExpressionListNode().accept(this);
        return (T) callNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(OperationNode operationNode) {
        // traverse child nodes
        operationNode.getLeftHandExpression().accept(this);
        operationNode.getRightHandExpression().accept(this);

        return (T) operationNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(SimpleStatementNode simpleStatement) {
        // traverse child node
        simpleStatement.getExpressionNode().accept(this);

        return (T) simpleStatement;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(IfStatementNode ifStatementNode) {
        // traverse child nodes
        ifStatementNode.getExpressionNode().accept(this);
        ifStatementNode.getBlockNode().accept(this);

        return (T) ifStatementNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(IfElseStatementNode ifElseStatementNode) {
        // traverse child nodes
        ifElseStatementNode.getExpressionNode().accept(this);
        ifElseStatementNode.getBlockNode().accept(this);
        ifElseStatementNode.getElseBlockNode().accept(this);

        return (T) ifElseStatementNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(WhileStatementNode whileStatementNode) {
        // traverse child nodes
        whileStatementNode.getExpressionNode().accept(this);
        whileStatementNode.getBlockNode().accept(this);

        return (T) whileStatementNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(DoWhileStatementNode doWhileStatementNode) {
        // traverse child nodes
        doWhileStatementNode.getExpressionNode().accept(this);
        doWhileStatementNode.getBlockNode().accept(this);

        return (T) doWhileStatementNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(InternalFunctionNode internalFunctionNode) {
        // traverse child nodes
        internalFunctionNode.getDeclarationNode().accept(this);
        internalFunctionNode.getArgumentListNode().accept(this);
        internalFunctionNode.getBlockNode().accept(this);

        return (T) internalFunctionNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(BlockNode blockNode) {
        // traverse child nodes
        blockNode.getStatementListNode().accept(this);

        return (T) blockNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(PrecedenceNode precedenceNode) {

        // traverse child node
        precedenceNode.getExpressionNode().accept(this);

        return (T) precedenceNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(ReturnStatementNode returnStatementNode) {
        // traverse child nodes
        returnStatementNode.getExpressionNode().accept(this);

        return (T) returnStatementNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(BreakStatementNode breakStatementNode) {
        return (T) breakStatementNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(ContinueStatementNode continueStatementNode) {
        return (T) continueStatementNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(ExpressionListNode expressionListNode) {
        // traverse child nodes
        for (ExpressionNode expressionNode : expressionListNode.getItems()) {
            expressionNode.accept(this);
        }

        return (T) expressionListNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(ArgumentListNode argumentListNode) {
        // traverse child nodes
        for (DeclarationNode declarationNode : argumentListNode.getItems()) {
            declarationNode.accept(this);
        }
        return (T) argumentListNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(FunctionListNode functionListNode) {
        // traverse child nodes
        for (ExternalFunctionNode functionNode : functionListNode.getItems()) {
            functionNode.accept(this);
        }
        return (T) functionListNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(RelationExpressionNode relationExpressionNode) {
        // traverse child nodes
        relationExpressionNode.getLeftHandExpression().accept(this);
        relationExpressionNode.getRightHandExpression().accept(this);
        return (T) relationExpressionNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(ExternalFunctionNode externalFunctionNode) {
        // traverse child nodes
        externalFunctionNode.getDeclarationNode().accept(this);
        externalFunctionNode.getArgumentListNode().accept(this);
        return (T) externalFunctionNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(DeclarationNode declarationNode) {
        // traverse child node
        declarationNode.getIdentifierNode().accept(this);

        return (T) declarationNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(TypeConversionNode typeConversionNode) {
        typeConversionNode.getExpressionNode().accept(this);
        return (T) typeConversionNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(VoidExpressionNode voidExpressionNode) {
        // this is a terminal! We cannot step down any further.
        return (T) voidExpressionNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(VectorConstructorNode vectorConstructorNode) {
        vectorConstructorNode.getExpressionListNode().accept(this);
        return (T) vectorConstructorNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(ArrayConstructorNode arrayConstructorNode) {
        arrayConstructorNode.getDimExpressionsNode().accept(this);
        return (T) arrayConstructorNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(NativeArrayConstructorNode arrayConstructorNode) {
        arrayConstructorNode.getAddress().accept(this);
        return (T) arrayConstructorNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(ClassNode classNode) {
        classNode.getFunctionListNode().accept(this);
        return (T) classNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(StructNode structNode) {
        structNode.getDeclarationListNode().accept(this);
        return (T) structNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(DimNode dimNode) {
        return (T) dimNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(DimExpressionsNode dimExpressionsNode) {
        // traverse child nodes
        for (ExpressionNode expressionNode : dimExpressionsNode.getItems()) {
            expressionNode.accept(this);
        }

        return (T) dimExpressionsNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(IndexAccessNode indexAccessNode) {
        // traverse nodes
        indexAccessNode.getIdentifierNode().accept(this);
        indexAccessNode.getDimExpressionsNode().accept(this);

        return (T) indexAccessNode;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T visit(CompilationItemsNode compilationItemsNode) {
        for (AstNode astNode : compilationItemsNode.getItems()) {
            astNode.accept(this);
        }
        return (T)compilationItemsNode;
    }
}
