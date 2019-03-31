package de.goto3d.kiwi.compiler.llvmbindings;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 02.04.13
 * Time: 06:43
 */
public class TypeAndName {

    public final LLVMBaseType type;
    public final String name;

    public TypeAndName(LLVMBaseType type, String name) {
        this.type = type;
        this.name = name;
    }
}
