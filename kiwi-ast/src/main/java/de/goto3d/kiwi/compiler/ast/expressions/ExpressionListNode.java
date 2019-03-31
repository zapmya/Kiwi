package de.goto3d.kiwi.compiler.ast.expressions;

import de.goto3d.kiwi.compiler.ast.ListNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 18:41
 */
public class ExpressionListNode extends ListNode<ExpressionNode> {

    public ExpressionListNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    public ExpressionListNode(SourcePosition sourcePosition, ExpressionNode expressionNode) {
        super(sourcePosition);
        this.add(expressionNode);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
