package de.goto3d.kiwi.compiler.ast;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 09.06.14
 * Time: 16:49
 */
public class SourcePosition {

    private final int line;
    private final int column;

    public SourcePosition(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {
        return line + "," + column;
    }
}
