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
import de.goto3d.kiwi.compiler.ast.types.ArrayType;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.StructNode;
import de.goto3d.kiwi.compiler.ast.types.TypeConversionNode;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.01.13
 * Time: 20:13
 */
public class SerializeVisitor implements Visitor<CharSequence> {

    private int indention;

    @Override
    public CharSequence visit(NumberNode numberNode) {
        PrimitiveType type = (PrimitiveType) numberNode.getType();
        switch(type.getRawType()) {
            case BYTE:
                return String.valueOf(numberNode.getValue().byteValue());
            case SHORT:
                return String.valueOf(numberNode.getValue().shortValue());
            case INT:
                return String.valueOf(numberNode.getValue().intValue());
            case LONG:
                return String.valueOf(numberNode.getValue().longValue());
            case FLOAT:
                return String.valueOf(numberNode.getValue().floatValue())+'F';
            case DOUBLE:
                return String.valueOf(numberNode.getValue().doubleValue());
            default:
                return String.valueOf(numberNode.getValue());
        }
    }

    @Override
    public CharSequence visit(NegateNode negateNode) {
        StringBuilder sb = new StringBuilder("-");
        sb.append(negateNode.getOperand().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(IdentifierNode identifierNode) {
        return identifierNode.getIdentifier();
    }

    @Override
    public CharSequence visit(AssignmentNode assignmentNode) {
        StringBuilder sb = new StringBuilder(assignmentNode.getIdentifierNode().accept(this));
        sb.append(" = ");
        sb.append(assignmentNode.getExpressionNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(DeclarationAndAssignmentNode declarationAndAssignmentNode) {
        StringBuilder sb = new StringBuilder(declarationAndAssignmentNode.getType().getName());
        sb.append(' ');
        sb.append(declarationAndAssignmentNode.getIdentifierNode().accept(this));
        sb.append(" = ");
        sb.append(declarationAndAssignmentNode.getExpressionNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(IndexedAssignmentNode assignmentNode) {
        StringBuilder sb = new StringBuilder(assignmentNode.getIndexAccessNode().accept(this));
        sb.append(" = ");
        sb.append(assignmentNode.getExpressionNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(StatementListNode statementListNode) {
        StringBuilder sb = new StringBuilder();
        this.indention++;
        for (StatementNode statementNode : statementListNode.getItems()) {
            this.appendIndention(sb);
            sb.append(statementNode.accept(this));
        }
        this.indention--;
        return sb;
    }

    private void appendIndention(StringBuilder sb) {
        for ( int i = 0; i < this.indention; i++ ) {
            sb.append('\t');
        }
    }

    @Override
    public CharSequence visit(CallNode callNode) {
        StringBuilder sb = new StringBuilder();
        sb.append(callNode.getIdentifierNode().accept(this));
        sb.append('(');
        sb.append(callNode.getExpressionListNode().accept(this));
        sb.append(')');

        return sb;
    }

    @Override
    public CharSequence visit(OperationNode operationNode) {
        StringBuilder sb = new StringBuilder(operationNode.getLeftHandExpression().accept(this));
        sb.append(operationNode.getOperator().character);
        sb.append(operationNode.getRightHandExpression().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(SimpleStatementNode simpleStatement) {
        StringBuilder sb = new StringBuilder(simpleStatement.getExpressionNode().accept(this));
        sb.append(";\n");
        return sb;
    }

    @Override
    public CharSequence visit(IfStatementNode ifStatementNode) {
        StringBuilder sb = new StringBuilder("if ( ");
        sb.append(ifStatementNode.getExpressionNode().accept(this));
        sb.append(" ) ");
        sb.append(ifStatementNode.getBlockNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(IfElseStatementNode ifElseStatementNode) {
        StringBuilder sb = new StringBuilder("if ( ");
        sb.append(ifElseStatementNode.getExpressionNode().accept(this));
        sb.append(" ) ");
        sb.append(ifElseStatementNode.getBlockNode().accept(this));
        if ( sb.charAt(sb.length()-1) == '\n' ) {
            sb.delete(sb.length()-1, sb.length());
        }
        sb.append(" else ");
        sb.append(ifElseStatementNode.getElseBlockNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(WhileStatementNode whileStatementNode) {
        StringBuilder sb = new StringBuilder("while ( ");
        sb.append(whileStatementNode.getExpressionNode().accept(this));
        sb.append(" ) ");
        sb.append(whileStatementNode.getBlockNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(DoWhileStatementNode doWhileStatementNode) {
        StringBuilder sb = new StringBuilder("do ");
        sb.append(doWhileStatementNode.getBlockNode().accept(this));
        if ( sb.charAt(sb.length()-1) == '\n' ) {
            sb.delete(sb.length()-1, sb.length());
        }
        sb.append(" while ( ");
        sb.append(doWhileStatementNode.getExpressionNode().accept(this));
        sb.append(" );\n");
        return sb;
    }

    @Override
    public CharSequence visit(InternalFunctionNode internalFunctionNode) {
        StringBuilder sb = new StringBuilder();
        this.appendIndention(sb);
        sb.append(internalFunctionNode.getType().getName());
        sb.append(' ');
        sb.append(internalFunctionNode.getName());
        sb.append('(');
        sb.append(internalFunctionNode.getArgumentListNode().accept(this));
        sb.append(")\n");
        sb.append(internalFunctionNode.getBlockNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(BlockNode blockNode) {
        StringBuilder sb = new StringBuilder();
        this.appendIndention(sb);
        sb.append("{\n");
        sb.append(blockNode.getStatementListNode().accept(this));
        this.appendIndention(sb);
        sb.append("}\n");
        return sb;
    }

    @Override
    public CharSequence visit(PrecedenceNode precedenceNode) {
        StringBuilder sb = new StringBuilder("(");
        sb.append(precedenceNode.getExpressionNode().accept(this));
        sb.append(')');
        return sb;
    }

    @Override
    public CharSequence visit(ReturnStatementNode returnStatementNode) {
        StringBuilder sb = new StringBuilder("return ");
        sb.append(returnStatementNode.getExpressionNode().accept(this));
        sb.append(";\n");
        return sb;
    }

    @Override
    public CharSequence visit(BreakStatementNode breakStatementNode) {
        return "break;\n";
    }

    @Override
    public CharSequence visit(ExpressionListNode expressionListNode) {
        List<ExpressionNode> items = expressionListNode.getItems();
        final int numItems          = items.size();
        if ( numItems == 0 ) {
            return "";
        }

        StringBuilder sb    = new StringBuilder(items.get(0).accept(this));
        for (int i = 1; i < numItems; i++ ) {
            sb.append(',').append(items.get(i).accept(this));
        }
        return sb;
    }

    @Override
    public CharSequence visit(ArgumentListNode argumentListNode) {
        List<DeclarationNode> items = argumentListNode.getItems();
        final int numItems          = items.size();
        if ( numItems == 0 ) {
            return "";
        }

        StringBuilder sb            = new StringBuilder(items.get(0).accept(this));
        for (int i = 1; i < numItems; i++ ) {
            sb.append(',').append(items.get(i).accept(this));
        }
        return sb;
    }

    @Override
    public CharSequence visit(FunctionListNode functionListNode) {
        StringBuilder sb = new StringBuilder();
        for (ExternalFunctionNode functionNode : functionListNode.getItems()) {
            sb.append(functionNode.accept(this)).append('\n');
        }
        return sb;
    }

    @Override
    public CharSequence visit(RelationExpressionNode relationExpressionNode) {
        StringBuilder sb = new StringBuilder(relationExpressionNode.getLeftHandExpression().accept(this));
        sb.append(relationExpressionNode.getRelation().identifier);
        sb.append(relationExpressionNode.getRightHandExpression().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(ExternalFunctionNode externalFunctionNode) {
        StringBuilder sb = new StringBuilder();
        this.appendIndention(sb);
        sb.append(externalFunctionNode.getType().getName());
        sb.append(' ');
        sb.append(externalFunctionNode.getName());
        sb.append('(');
        sb.append(externalFunctionNode.getArgumentListNode().accept(this));
        sb.append(");\n");
        return sb;
    }

    @Override
    public CharSequence visit(DeclarationNode declarationNode) {
        StringBuilder sb = new StringBuilder(declarationNode.getType().getName());
        sb.append(' ');
        sb.append(declarationNode.getIdentifierNode().accept(this));
        return sb;
    }

    @Override
    public CharSequence visit(TypeConversionNode typeConversionNode) {
        if ( typeConversionNode.isImplicitCast() ) {
            return typeConversionNode.getExpressionNode().accept(this);
        }

        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(typeConversionNode.getType().getName());
        sb.append(')');
        sb.append(typeConversionNode.getExpressionNode().accept(this));

        return sb;
    }

    @Override
    public CharSequence visit(VoidExpressionNode voidExpressionNode) {
        return "";
    }

    @Override
    public CharSequence visit(VectorConstructorNode vectorConstructorNode) {
        StringBuilder sb = new StringBuilder();
        sb.append("new (");
        sb.append(vectorConstructorNode.getExpressionListNode().accept(this));
        sb.append(')');
        return sb;
    }

    @Override
    public CharSequence visit(ArrayConstructorNode arrayConstructorNode) {
        ArrayType arrayType         = (ArrayType) arrayConstructorNode.getType();
        StringBuilder sb            = new StringBuilder("new ");
        sb.append(arrayType.getType().getName());
        int numDimExpressions       = arrayConstructorNode.getDimExpressionsNode().size();
        sb.append(arrayConstructorNode.getDimExpressionsNode().accept(this));
        for ( int i = numDimExpressions; i < arrayType.getDimensions(); i++ ) {
            sb.append("[]");
        }
        return sb;
    }

    @Override
    public CharSequence visit(NativeArrayConstructorNode arrayConstructorNode) {
        StringBuilder sb            = new StringBuilder("native ");
        sb.append(arrayConstructorNode.getAddress().getValue());
        return sb;
    }

    @Override
    public CharSequence visit(DimNode dimNode) {
        StringBuilder sb    = new StringBuilder("[]");
        for (int i = 1; i < dimNode.getDimensions(); i++) {
            sb.append("[]");
        }
        return sb.toString();
    }

    @Override
    public CharSequence visit(DimExpressionsNode dimExpressionsNode) {
        StringBuilder sb = new StringBuilder();
        for (ExpressionNode expressionNode : dimExpressionsNode.getItems()) {
            sb.append('[').append(expressionNode.accept(this)).append(']');
        }
        return sb;
    }

    @Override
    public CharSequence visit(CompilationItemsNode compilationItemsNode) {
        StringBuilder sb = new StringBuilder();
        for (AstNode astNode : compilationItemsNode.getItems()) {
            sb.append(astNode.accept(this));
            sb.append("\n");
        }

        return sb;
    }

    @Override
    public CharSequence visit(ClassNode classNode) {
        StringBuilder sb = new StringBuilder("class ");
        sb.append(classNode.getIdentifier());
        sb.append("\n{\n");
        this.indention++;
        sb.append(classNode.getFunctionListNode().accept(this));
        this.indention--;
        sb.append("}\n");
        return sb;
    }

    @Override
    public CharSequence visit(StructNode structNode) {
        StringBuilder sb = new StringBuilder("struct ");
        sb.append(structNode.getIdentifier());
        sb.append("\n{\n");
        this.indention++;
        sb.append(structNode.getDeclarationListNode().accept(this));
        this.indention--;
        sb.append("}\n");
        return sb;
    }

    @Override
    public CharSequence visit(IndexAccessNode indexAccessNode) {
        StringBuilder sb = new StringBuilder();
        sb.append(indexAccessNode.getIdentifierNode().accept(this));
        sb.append(indexAccessNode.getDimExpressionsNode().accept(this));
        return sb;
    }

}
