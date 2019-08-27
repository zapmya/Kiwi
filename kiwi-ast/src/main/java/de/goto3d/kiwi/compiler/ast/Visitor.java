package de.goto3d.kiwi.compiler.ast;

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
 * Date: 09.01.13
 * Time: 12:07
 */
public interface Visitor<T> {

    T visit(NumberNode numberNode);

    T visit(NegateNode negateNode);

    T visit(IdentifierNode identifierNode);

    T visit(AssignmentNode assignmentNode);

    T visit(DeclarationAndAssignmentNode declarationAndAssignmentNode);

    T visit(IndexedAssignmentNode indexedAssignmentNode);

    T visit(StatementListNode statementListNode);

    T visit(CallNode callNode);

    T visit(OperationNode operationNode);

    T visit(SimpleStatementNode simpleStatement);

    T visit(IfStatementNode ifStatementNode);

    T visit(IfElseStatementNode ifElseStatementNode);

    T visit(WhileStatementNode whileStatementNode);

    T visit(DoWhileStatementNode doWhileStatementNode);

    T visit(InternalFunctionNode internalFunctionNode);

    T visit(BlockNode blockNode);

    T visit(PrecedenceNode precedenceNode);

    T visit(ReturnStatementNode returnStatementNode);

    T visit(BreakStatementNode breakStatementNode);

    T visit(ExpressionListNode expressionListNode);

    T visit(ArgumentListNode argumentListNode);

    T visit(FunctionListNode functionListNode);

    T visit(RelationExpressionNode relationExpressionNode);

    T visit(ExternalFunctionNode externalFunctionNode);

    T visit(DeclarationNode declarationNode);

    T visit(TypeConversionNode typeConversionNode);

    T visit(VoidExpressionNode voidExpressionNode);

    T visit(VectorConstructorNode vectorConstructorNode);

    T visit(ClassNode classNode);

    T visit(StructNode classNode);

    T visit(IndexAccessNode indexAccessNode);

    T visit(ArrayConstructorNode arrayConstructorNode);

    T visit(NativeArrayConstructorNode nativeArrayConstructorNode);

    T visit(DimNode dimNode);

    T visit(DimExpressionsNode dimExpressionsNode);

    T visit(CompilationItemsNode compilationItemsNode);
}
