package de.goto3d.kiwi.compiler.codegenerator;

import de.goto3d.kiwi.compiler.ast.AstNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.03.13
 * Time: 07:02
 */
public abstract class CodeGeneratorBase<T extends AstNode> implements CodeGenerator<T> {

    protected final CodeGeneratorVisitor visitor;

    protected CodeGeneratorBase(CodeGeneratorVisitor visitor) {
        this.visitor = visitor;
    }
}
