package de.goto3d.kiwi.compiler.ast.statements;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.01.13
 * Time: 11:13
 */
public abstract class StatementNode extends AstNode {

    protected StatementNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }
}
