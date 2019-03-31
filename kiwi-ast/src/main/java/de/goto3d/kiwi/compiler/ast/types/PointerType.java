package de.goto3d.kiwi.compiler.ast.types;

/**
 * Created by da da gru on 10.04.16.
 *
 */
public class PointerType implements Type {

    private final Type type;

    private final String name;

    public PointerType(Type type) {
        this.type   = type;

        this.name   = type.getName()+"*";
    }

    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public RawType getRawType() {
        return this.type.getRawType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointerType that  = (PointerType) o;

        return this.getRawType() == that.getRawType();
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }

}
