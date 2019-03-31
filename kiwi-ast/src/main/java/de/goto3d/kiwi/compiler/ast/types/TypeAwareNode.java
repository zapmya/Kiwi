package de.goto3d.kiwi.compiler.ast.types;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 24.03.14
 * Time: 06:56
 */
public interface TypeAwareNode {

    Type getType();

    void setType(Type type);
}
