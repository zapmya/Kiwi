package de.goto3d.kiwi.compiler.ast.types;

/**
 * Created by da da gru on 30.12.14.
 *
 */
public class PrimitiveType implements Type {

    protected final RawType rawType;

    public PrimitiveType(RawType rawType) {
        this.rawType = rawType;
    }

    public RawType getRawType() {
        return rawType;
    }

    @Override
    public String getName() {
        return this.rawType.getTypeName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrimitiveType that = (PrimitiveType) o;

        return rawType == that.rawType;
    }

    @Override
    public int hashCode() {
        return rawType.hashCode();
    }
}
