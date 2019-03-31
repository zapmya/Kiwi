package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.ListNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.01.13
 * Time: 06:46
 */
public class StatementListNode extends ListNode<StatementNode> {

    public StatementListNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    public StatementListNode(SourcePosition sourcePosition, StatementNode statementNode) {
        super(sourcePosition);
        this.add(statementNode);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
