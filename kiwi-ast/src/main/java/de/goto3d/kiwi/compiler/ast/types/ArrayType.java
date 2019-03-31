package de.goto3d.kiwi.compiler.ast.types;

/**
 * Created by da da gru on 10.04.16.
 *
 */
public class ArrayType implements Type {

    private final Type type;

    private final int dimensions;

    private final String name;

    public ArrayType(Type type, int dimensions) {
        this.type       = type;
        this.dimensions = dimensions;

        StringBuilder builder   = new StringBuilder(type.getName());
        for (int i = 0; i < dimensions; i++) {
            builder.append("[]");
        }
        this.name   = builder.toString();
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

    public int getDimensions() {
        return dimensions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayType that  = (ArrayType) o;

        return this.getRawType() == that.getRawType() && this.dimensions == that.dimensions;
    }

    @Override
    public int hashCode() {
        return this.type.hashCode() + this.dimensions;
    }

}
