package de.goto3d.kiwi.compiler.parser.visitors;

import de.goto3d.kiwi.compiler.SymbolTable;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.ast.visitors.TraversingVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.06.14
 * Time: 10:08
 */
public class InsertMissingTypesVisitor extends TraversingVisitor<AstNode> {

    private final SymbolTable symbolTable;

    public InsertMissingTypesVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public AstNode visit(IdentifierNode identifierNode) {

        // lookup type for identifier
        Type type   = this.symbolTable.getType(identifierNode);
        identifierNode.setType(type);

        // this is a terminal! We cannot step down any further.
        return identifierNode;
    }

    @Override
    public AstNode visit(CallNode callNode) {

        // traverse children
        super.visit(callNode);

        // lookup function in map
        ExternalFunctionNode externalFunctionNode   = this.symbolTable.findFirstFunctionNode(
                callNode.getIdentifierNode().getIdentifier()
        );

        callNode.setType(externalFunctionNode == null ? null : externalFunctionNode.getType());

        // this is a terminal! We cannot step down any further.
        return callNode;
    }

    @Override
    public AstNode visit(DeclarationAndAssignmentNode declarationAndAssignmentNode) {

        // override this method so we do not visit the identifier node

        // traverse node
        declarationAndAssignmentNode.getExpressionNode().accept(this);

        return declarationAndAssignmentNode;
    }

    @Override
    public AstNode visit(DeclarationNode declarationNode) {
        // override this method so we do not visit the identifier node
        return declarationNode;
    }

    @Override
    public AstNode visit(IndexAccessNode indexAccessNode) {

        // traverse children
        super.visit(indexAccessNode);

        // set type to raw type of identifier node
        Type type = indexAccessNode.getIdentifierNode().getType();
        indexAccessNode.setType(new PrimitiveType(type.getRawType()));
        return indexAccessNode;
    }
}
