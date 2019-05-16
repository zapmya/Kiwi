package de.goto3d.kiwi.compiler.ast.types;

/**
 * Created by da da gru on 30.12.14.
 *
 */
public class PrimitiveType implements Type {

    protected final RawType rawType;

    protected final int dimensions;

    public PrimitiveType(RawType rawType) {
        this(rawType,1);
    }

    public PrimitiveType(RawType rawType, int dimensions) {
        this.rawType    = rawType;
        this.dimensions = dimensions;
    }

    public RawType getRawType() {
        return rawType;
    }

    @Override
    public boolean isVectorType() {
        return this.dimensions > 1;
    }

    public int getDimensions() {
        return dimensions;
    }

    @Override
    public String getName() {
        return this.dimensions == 1 ?
                this.rawType.getTypeName() :
                this.rawType.getTypeName() + this.dimensions;
    }

    public static PrimitiveType getByNameAndDim(String nameAndDim) {
        StringBuilder sb    = new StringBuilder();
        int idx             = nameAndDim.length();
        while( --idx >= 0 && Character.isDigit(nameAndDim.charAt(idx)) ) {
            sb.insert(0,nameAndDim.charAt(idx));
        }
        String name = nameAndDim.substring(0,idx+1);
        int dim     = Integer.parseInt(sb.toString());
        RawType type= RawType.getByName(name);

        return new PrimitiveType(type, dim);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrimitiveType that = (PrimitiveType) o;

        return rawType == that.rawType && dimensions == that.dimensions;
    }

    @Override
    public int hashCode() {
        return 31 * rawType.hashCode() + dimensions;
    }
}
