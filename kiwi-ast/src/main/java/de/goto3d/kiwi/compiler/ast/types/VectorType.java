package de.goto3d.kiwi.compiler.ast.types;

/**
 * Created by da da gru on 30.12.14.
 *
 */
public class VectorType extends PrimitiveType {

    private final int dimensions;

    public VectorType(RawType rawType, int dimensions) {
        super(rawType);
        this.dimensions = dimensions;
    }

    public int getDimensions() {
        return dimensions;
    }

    @Override
    public String getName() {
        return this.getRawType().getTypeName() + this.dimensions;
    }

    public static VectorType getByNameAndDim(String nameAndDim) {
        StringBuilder sb    = new StringBuilder();
        int idx             = nameAndDim.length();
        while( --idx >= 0 && Character.isDigit(nameAndDim.charAt(idx)) ) {
            sb.insert(0,nameAndDim.charAt(idx));
        }
        String name = nameAndDim.substring(0,idx+1);
        int dim     = Integer.parseInt(sb.toString());
        RawType type= RawType.getByName(name);

        return new VectorType(type, dim);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;

        VectorType that = (VectorType) o;

        return rawType == that.rawType && dimensions == that.dimensions;
    }

    @Override
    public int hashCode() {
        return 31 * rawType.hashCode() + dimensions;
    }
}
