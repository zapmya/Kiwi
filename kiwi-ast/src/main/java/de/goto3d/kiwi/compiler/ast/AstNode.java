package de.goto3d.kiwi.compiler.ast;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.01.13
 * Time: 11:10
 */
public abstract class AstNode {

    private final SourcePosition sourcePosition;

    protected AstNode(SourcePosition sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public abstract <T> T accept(Visitor<T> visitor);

    public SourcePosition getSourcePosition() {
        return sourcePosition;
    }
}
