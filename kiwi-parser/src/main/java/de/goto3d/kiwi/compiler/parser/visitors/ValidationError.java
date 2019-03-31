package de.goto3d.kiwi.compiler.parser.visitors;

import de.goto3d.kiwi.compiler.ast.AstNode;

/**
 * Created by da da gru on 28.05.15.
 *
 */
public class ValidationError {

    private final AstNode node;
    private final String message;

    public ValidationError(AstNode node, String message) {
        this.node = node;
        this.message = message;
    }

    public AstNode getNode() {
        return node;
    }

    public String getMessage() {
        return message;
    }
}
