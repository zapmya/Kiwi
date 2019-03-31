package de.goto3d.kiwi.compiler.ast.classes;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;

/**
 * Created by da da gru on 15.07.15.
 *
 */
public class ClassNode extends AstNode {

    private final String identifier;

    private final FunctionListNode functionListNode;

    public ClassNode(SourcePosition sourcePosition, String identifier, FunctionListNode functionListNode) {
        super(sourcePosition);
        this.identifier         = identifier;
        this.functionListNode   = functionListNode;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    public String getIdentifier() {
        return identifier;
    }

    public FunctionListNode getFunctionListNode() {
        return functionListNode;
    }
}
