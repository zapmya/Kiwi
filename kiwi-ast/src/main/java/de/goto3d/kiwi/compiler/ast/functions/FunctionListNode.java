package de.goto3d.kiwi.compiler.ast.functions;

import de.goto3d.kiwi.compiler.ast.ListNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 04.02.13
 * Time: 09:00
 */
public class FunctionListNode extends ListNode<ExternalFunctionNode> {

    public FunctionListNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    public FunctionListNode(SourcePosition sourcePosition, ExternalFunctionNode functionNode) {
        super(sourcePosition);
        this.add(functionNode);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
