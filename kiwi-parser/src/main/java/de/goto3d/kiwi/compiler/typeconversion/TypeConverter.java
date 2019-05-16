package de.goto3d.kiwi.compiler.typeconversion;

import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.types.*;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 24.03.14
 * Time: 06:53
 */
public class TypeConverter {

    private final boolean implicitCast;

    public TypeConverter(boolean implicitCast) {
        this.implicitCast = implicitCast;
    }

    public Type determineTargetType(TypeAwareNode typeAwareNode0, TypeAwareNode typeAwareNode1) {
        Type type0  = typeAwareNode0.getType();
        Type type1  = typeAwareNode1.getType();
        // are both types set ?
        if ( type0 == null || type1 == null ) {
            // no -> stop processing right now
            // NB: Errors will be processed during the validation phase
            return null;
        }

        // do types match ?
        if ( type0.equals(type1)) {
            // yes -> do not generate any code at all
            return type0;
        }

        // check if implicit casting is allowed ?
        if ( !this.implicitCast ) {
            System.err.println(
                    "Cannot automatically cast from " + type0.getName() + " to " + type1.getName()
            );
            System.exit(-1);
        }

        // get target type
        if ( !(type0 instanceof PrimitiveType) || !(type1 instanceof PrimitiveType) ) {
            throw new IllegalArgumentException("currently only primitive types are supported");
        }
        PrimitiveType primitiveType0= (PrimitiveType)type0;
        PrimitiveType primitiveType1= (PrimitiveType)type1;

        RawType targetRawType   = primitiveType0.getRawType().order > primitiveType1.getRawType().order ?
                type0.getRawType() :
                type1.getRawType();
        int dimensions          = primitiveType0.getDimensions() > primitiveType1.getDimensions() ?
                primitiveType0.getDimensions() :
                primitiveType1.getDimensions();
        return new PrimitiveType(targetRawType, dimensions);
    }

   public ExpressionNode insertConverterNode(ExpressionNode sourceNode, Type targetType) {
       // is target type set ?
       if ( targetType == null ) {
           // no -> return source node instead
           // NB: Errors will be processed during the validation phase
           return sourceNode;
       }

       Type type   = sourceNode.getType();
       // do we have to convert type
       if ( targetType.equals(type) ) {
           // no -> return initial value
           return sourceNode;
       }
       // yes -> add conversion code
       TypeConversionNode targetNode =  new TypeConversionNode(
               sourceNode.getSourcePosition(), sourceNode, this.implicitCast
       );
       targetNode.setType(targetType);
       return targetNode;
   }
}
