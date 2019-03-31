package de.goto3d.kiwi.compiler.ast.types;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.statements.StatementListNode;

/**
 * Created by da da gru on 06.06.17.
 *
 */
public class StructNode extends AstNode {

    private final String identifier;

    private final StatementListNode declarationListNode;

    public StructNode(SourcePosition sourcePosition, String identifier, StatementListNode declarationListNode) {
        super(sourcePosition);
        this.identifier         = identifier;
        this.declarationListNode= declarationListNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getIdentifier() {
        return identifier;
    }

    public StatementListNode getDeclarationListNode() {
        return declarationListNode;
    }
}
