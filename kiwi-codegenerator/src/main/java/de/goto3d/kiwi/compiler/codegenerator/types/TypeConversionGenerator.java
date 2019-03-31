package de.goto3d.kiwi.compiler.codegenerator.types;

import de.goto3d.kiwi.compiler.ast.types.*;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBaseType;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.03.14
 * Time: 07:06
 */
public class TypeConversionGenerator extends CodeGeneratorBase<TypeConversionNode> {

    private final TypeGenerator typeGenerator;

    public TypeConversionGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);

        this.typeGenerator  = new TypeGenerator();
    }

    @Override
    public LLVMValue generateCode(TypeConversionNode conversionNode) {

        LLVMValue exprValue = conversionNode.getExpressionNode().accept(this.visitor);
        if ( exprValue == null ) {
            // TODO: add error handling code
            return null;
        }

        Type sourceType = conversionNode.getSourceType();
        Type targetType = conversionNode.getType();

        // case 1: convert primitive types
        if ( sourceType.getClass() == PrimitiveType.class && targetType.getClass() == PrimitiveType.class ) {
            PrimitiveType primitiveSourceType = (PrimitiveType) sourceType;
            PrimitiveType primitiveTargetType = (PrimitiveType) targetType;

            if (primitiveTargetType.getRawType().isInteger()) {
                return this.generateIntCast(exprValue, primitiveSourceType, primitiveTargetType);
            }
            if (primitiveTargetType.getRawType().isFloat()) {
                return this.generateFloatCast(exprValue, primitiveSourceType, primitiveTargetType);
            }
        }

        // case 2: convert pointers to arrays
        if ( sourceType instanceof PointerType && targetType instanceof ArrayType ) {
            // do we need to cast the resulting pointer ?
            if ( targetType.getRawType() == RawType.BYTE ) {
                // no -> we are done
                return exprValue;
            }

            // yes -> add bitcast
            LLVMBaseType destType   = this.typeGenerator.generateCode(targetType);

            return this.visitor.getBuilder().createBitCast(exprValue, destType, "ptrToArray");
        }

        throw new IllegalArgumentException(
                "currently unsupported cast from \""+sourceType.getName()+" to \"" + targetType.getName()+"\""
        );
    }

    private LLVMValue generateFloatCast(LLVMValue exprValue, PrimitiveType sourceType, PrimitiveType targetType) {

        String valueName        = exprValue.getName();
        String typeName         = targetType.getName();

        RawType sourceRawType   = sourceType.getRawType();
        RawType targetRawType   = targetType.getRawType();
        // is source type also a floating point number ?
        if ( sourceRawType.isFloat() ) {
            // yes -> check if we are increasing the bits
            if ( sourceRawType.bits < targetRawType.bits ) {
                // yes -> extend bits
                return this.visitor.getBuilder().createFpExt(
                        exprValue, this.typeGenerator.generateCode(targetType), valueName+"As"+typeName
                );
            } else {
                // yes -> truncate bits
                return this.visitor.getBuilder().createFpTrunc(
                        exprValue, this.typeGenerator.generateCode(targetType), valueName+"As"+typeName
                );
            }
        }

        // no -> generate float from integer
        return this.visitor.getBuilder().createSIToFp(
                exprValue, this.typeGenerator.generateCode(targetType), valueName+"As"+typeName
        );
    }

    private LLVMValue generateIntCast(LLVMValue exprValue, PrimitiveType sourceType, PrimitiveType targetType) {
        String valueName        = exprValue.getName();
        String typeName         = targetType.getName();

        RawType sourceRawType   = sourceType.getRawType();
        RawType targetRawType   = targetType.getRawType();
        // is source type also an integer number ?
        if ( sourceRawType.isInteger() ) {
            // yes -> check if we are increasing the bits
            if ( sourceRawType.bits < targetRawType.bits ) {
                // yes -> extend bits
                return this.visitor.getBuilder().createSExt(
                        exprValue, this.typeGenerator.generateCode(targetType), valueName+"As"+typeName
                );
            } else {
                // yes -> truncate bits
                return this.visitor.getBuilder().createTruncate(
                        exprValue, this.typeGenerator.generateCode(targetType), valueName+"As"+typeName
                );
            }
        }

        return this.visitor.getBuilder().createSIToFp(
                exprValue, this.typeGenerator.generateCode(targetType), valueName+"As"+typeName
        );
    }
}
