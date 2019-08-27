package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by gru.
 * Created on 27.08.2019 - 19:08.
 */
public class BreakStatementNode extends StatementNode {

    public BreakStatementNode(
            SourcePosition sourcePosition
    ) {
        super(sourcePosition);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
