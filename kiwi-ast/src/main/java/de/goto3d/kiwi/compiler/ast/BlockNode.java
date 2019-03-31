package de.goto3d.kiwi.compiler.ast;

import de.goto3d.kiwi.compiler.ast.statements.StatementListNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 09:54
 */
public class BlockNode extends AstNode {

    private final StatementListNode statementListNode;

    public BlockNode(SourcePosition sourcePosition, StatementListNode statementListNode) {
        super(sourcePosition);
        this.statementListNode = statementListNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public StatementListNode getStatementListNode() {
        return statementListNode;
    }
}
