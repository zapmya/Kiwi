package de.goto3d.kiwi.compiler.parser.visitors;

import de.goto3d.kiwi.compiler.DeclarationItem;
import de.goto3d.kiwi.compiler.SymbolTable;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.expressions.CallNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.types.*;
import de.goto3d.kiwi.compiler.ast.visitors.TraversingVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by da da da gru on 28.05.15.
 *
 */
public class ValidationVisitor extends TraversingVisitor<AstNode> {

    private final SymbolTable symbolTable;

    private List<ValidationError> errorList = new ArrayList<ValidationError>();

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
                // is order ok `
                if (sourceType.getRawType().order <= targetType.getRawType().order ) {
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
                            "Types do not match. left = \"%s\", right = \"%s\"",
                            targetType.getName(),
                            sourceType.getName()
                    )
            );
        }

        return typeConversionNode;
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
