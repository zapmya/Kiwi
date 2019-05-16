package de.goto3d.kiwi.compiler.ast.types;

/**
 * Created by da da gru on 30.12.14.
 */
public interface Type {

    String getName();

    RawType getRawType();

    boolean isVectorType();
    //TODO: add method to compare order
}
