package de.goto3d.kiwi.compiler.parser.visitors;

import de.goto3d.kiwi.compiler.DeclarationItem;
import de.goto3d.kiwi.compiler.SymbolTable;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.expressions.CallNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.statements.*;
import de.goto3d.kiwi.compiler.ast.types.*;
import de.goto3d.kiwi.compiler.ast.visitors.TraversingVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gru on 28.05.15.
 *
 */
public class ValidationVisitor extends TraversingVisitor<AstNode> {

    private int loopCount;

    private final SymbolTable symbolTable;

    private List<ValidationError> errorList = new ArrayList<>();

    public ValidationVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public AstNode visit(IdentifierNode identifierNode) {

        // get all matching declarations
        List<DeclarationItem> declarations  = this.symbolTable.getDeclarations(identifierNode);
        if ( declarations == null || declarations.isEmpty() ) {
            this.appendError(identifierNode, "Variable \""+identifierNode.getIdentifier()+"\" is not defined");
        }

        return identifierNode;
    }

    @Override
    public AstNode visit(DeclarationNode declarationNode) {

        IdentifierNode identifierNode       = declarationNode.getIdentifierNode();
        // get all matching declarations
        List<DeclarationItem> declarations  = this.symbolTable.getDeclarations(identifierNode);
        if ( declarations == null || declarations.size() == 0 ) {
            return declarationNode;
        }
        if ( declarations.size() == 1 &&
             declarationNode == declarations.get(0).declarationAwareNode.getDeclarationNode()
                ) {
            return null;
        }

        this.appendError(
                identifierNode,
                String.format("Variable \"%s\" is defined multiple times", identifierNode.getIdentifier())
            );

        return declarationNode;
    }

    @Override
    public AstNode visit(CallNode callNode) {
        // traverse child nodes
        callNode.getExpressionListNode().accept(this);

        // check if function exists
        IdentifierNode identifierNode       = callNode.getIdentifierNode();
        String functionName                 = identifierNode.getIdentifier();
        ExternalFunctionNode functionNode   = this.symbolTable.findFirstFunctionNode(functionName);
        if ( functionNode == null ) {
            this.appendError(identifierNode, "There is no function called \"" + functionName + "\"");
        }

        return callNode;
    }

    @Override
    public AstNode visit(ExternalFunctionNode externalFunctionNode) {
        // traverse child nodes
        externalFunctionNode.getArgumentListNode().accept(this);
        // check for duplicates
        String signature                    = externalFunctionNode.getSignature();
        List<ExternalFunctionNode> nodes    = this.symbolTable.findFunctionNodes(signature);
        if ( nodes.size() > 1 ) {
            this.appendError(
                    externalFunctionNode,
                    String.format("There is already a function with the signature \"%s\"", signature)
            );
        }

        return externalFunctionNode;
    }

    @Override
    public AstNode visit(InternalFunctionNode internalFunctionNode) {
        AstNode node    = this.visit((ExternalFunctionNode) internalFunctionNode);

        // TODO: check if proper return statement is in place
        internalFunctionNode.getBlockNode().accept(this);
        return node;
    }

    @Override
    public AstNode visit(TypeConversionNode typeConversionNode) {
        // traverse children
        super.visit(typeConversionNode);

        // check types
        Type sourceType = typeConversionNode.getSourceType();
        Type targetType = typeConversionNode.getType();

        // are types equal ?
        if ( !sourceType.equals(targetType) ) {
            // no -> check if conversion is allowed
            // case 1: primitive types
            if ( sourceType instanceof PrimitiveType && targetType instanceof PrimitiveType ) {
                // are dimensions equal ?
                if ( ((PrimitiveType)sourceType).getDimensions() != ((PrimitiveType)targetType).getDimensions() ) {
                    // no -> append error message
                    this.appendError(
                            typeConversionNode,
                            String.format(
                                    "Cannot convert type \"%s\" to \"%s\"",
                                    sourceType.getName(),
                                    targetType.getName()
                            )
                    );
                    return typeConversionNode;
                }

                // is order ok `
                if (sourceType.getRawType().order <= targetType.getRawType().order ) {
                    // yes -> check dimension
                    // yes -> order ok, no problem
                    return typeConversionNode;
                }
                // no -> check type of cast
                if ( !typeConversionNode.isImplicitCast() ) {
                    // it is an explicit cast -> so do it
                    return typeConversionNode;
                }
            }
            // case 2: array and pointer types
            if ( sourceType instanceof PointerType && targetType instanceof ArrayType ) {
                // this is always allowed!
                return typeConversionNode;
            }
            this.appendError(
                    typeConversionNode,
                    String.format(
                            "Cannot convert type \"%s\" to \"%s\"",
                            sourceType.getName(),
                            targetType.getName()
                    )
            );
        }

        return typeConversionNode;
    }

    @Override
    public AstNode visit(IfStatementNode ifStatementNode) {
        // traverse child nodes
        ExpressionNode expressionNode = ifStatementNode.getExpressionNode();
        expressionNode.accept(this);
        ifStatementNode.getBlockNode().accept(this);

        Type type = expressionNode.getType();
        if ( type.getClass() != PrimitiveType.class || type.getRawType() != RawType.BOOLEAN ) {
            this.appendError(
                    ifStatementNode,
                    String.format(
                            "boolean type expected but got \"%s\" instead.",
                            type.getName()
                    )
            );
        }

        return ifStatementNode;
    }

    @Override
    public AstNode visit(DoWhileStatementNode doWhileStatementNode) {
        this.loopCount++;
        AstNode node    = super.visit(doWhileStatementNode);
        this.loopCount--;
        return node;
    }

    @Override
    public AstNode visit(WhileStatementNode whileStatementNode) {
        this.loopCount++;
        AstNode node    = super.visit(whileStatementNode);
        this.loopCount--;
        return node;
    }

    @Override
    public AstNode visit(BreakStatementNode breakStatementNode) {

        if ( this.loopCount == 0 ) {
            this.appendError(
                    breakStatementNode,
           "break statement is outside of any loop."
            );
        }

        return breakStatementNode;
    }

    @Override
    public AstNode visit(ContinueStatementNode continueStatementNode) {

        if ( this.loopCount == 0 ) {
            this.appendError(
                    continueStatementNode,
           "continue statement is outside of any loop."
            );
        }

        return continueStatementNode;
    }

    private void appendError(AstNode astNode, String message) {
        this.errorList.add(new ValidationError(astNode, message));
        SourcePosition position = astNode.getSourcePosition();
        System.err.printf("Node: %s. Line %d, Column %d: %s%n",
                astNode.getClass().getSimpleName(), position.getLine(), position.getColumn(), message
        );
    }

    public boolean isValid() {
        return this.errorList.isEmpty();
    }
}
