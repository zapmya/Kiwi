package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;

/**
 * Created by gru.
 * Created on 27.08.2019 - 20:21.
 */
public class ContinueStatementNode extends StatementNode {

    public ContinueStatementNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
