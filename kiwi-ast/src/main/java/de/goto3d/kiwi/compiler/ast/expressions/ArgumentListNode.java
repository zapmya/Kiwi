package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.ListNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 04.02.13
 * Time: 07:00
 */
public class ArgumentListNode extends ListNode<DeclarationNode> {

    public ArgumentListNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    public ArgumentListNode(SourcePosition sourcePosition, DeclarationNode declarationNode) {
        super(sourcePosition);
        this.add(declarationNode);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
