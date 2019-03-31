package de.goto3d.kiwi.compiler.ast.compilation;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.ListNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by da da gru on 06.06.17.
 *
 */
public class CompilationItemsNode extends ListNode<AstNode> {

    public CompilationItemsNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
