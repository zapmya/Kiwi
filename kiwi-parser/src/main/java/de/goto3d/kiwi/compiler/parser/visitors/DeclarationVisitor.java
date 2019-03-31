package de.goto3d.kiwi.compiler.parser.visitors;

import de.goto3d.kiwi.compiler.ScopeStack;
import de.goto3d.kiwi.compiler.SymbolTable;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.compilation.CompilationItemsNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationAndAssignmentNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.visitors.TraversingVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.06.14
 * Time: 15:31
 */
public class DeclarationVisitor extends TraversingVisitor<AstNode> {

    private final SymbolTable symbolTable           = new SymbolTable();

    private final ScopeStack currentScopeStack      = new ScopeStack();

    @Override
    public AstNode visit(BlockNode blockNode) {

        // add block to stack
        this.currentScopeStack.push(blockNode);

        super.visit(blockNode);

        // remove block from stack
        this.currentScopeStack.pop();

        return blockNode;
    }

    @Override
    public AstNode visit(IdentifierNode identifierNode) {

        this.symbolTable.insertIdentifier(identifierNode, this.currentScopeStack);

        // this is a terminal! We cannot step down any further.
        return identifierNode;
    }

    @Override
    public AstNode visit(DeclarationNode declarationNode) {
        // update declaration map
        this.symbolTable.insertDeclaration(declarationNode, this.currentScopeStack);

        return declarationNode;
    }

    @Override
    public AstNode visit(DeclarationAndAssignmentNode declarationAndAssignmentNode) {

        // update declaration map
        this.symbolTable.insertDeclaration(declarationAndAssignmentNode, this.currentScopeStack);

        // traverse node
        declarationAndAssignmentNode.getExpressionNode().accept(this);

        return declarationAndAssignmentNode;
    }

    @Override
    public AstNode visit(InternalFunctionNode internalFunctionNode) {

        // update symbol table
        this.symbolTable.add(internalFunctionNode);

        // add function to stack
        this.currentScopeStack.push(internalFunctionNode);

        // traverse child nodes
        internalFunctionNode.getArgumentListNode().accept(this);
        internalFunctionNode.getBlockNode().accept(this);

        // remove function from stack
        this.currentScopeStack.pop();

        return internalFunctionNode;
    }

    @Override
    public AstNode visit(ExternalFunctionNode externalFunctionNode) {

        // update declaration map
        this.symbolTable.add(externalFunctionNode);

        // add function to stack
        this.currentScopeStack.push(externalFunctionNode);

        // traverse child nodes
        externalFunctionNode.getArgumentListNode().accept(this);

        // remove function from stack
        this.currentScopeStack.pop();

        return externalFunctionNode;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}
