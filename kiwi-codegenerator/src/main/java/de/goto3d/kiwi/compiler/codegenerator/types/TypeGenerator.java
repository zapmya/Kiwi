package de.goto3d.kiwi.compiler.codegenerator.types;

import de.goto3d.kiwi.compiler.ast.types.*;
import de.goto3d.kiwi.compiler.llvmbindings.*;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 25.03.14
 * Time: 07:04
 */
public class TypeGenerator {

    public LLVMBaseType generateCode(Type type) {

        if ( type instanceof VectorType ) {
            return this.generateVectorTypeCode((VectorType)type);
        }
        if ( type instanceof PrimitiveType) {
            return this.generatePrimitiveTypeCode((PrimitiveType) type);
        }
        if ( type instanceof PointerType) {
            return this.generatePointerTypeCode((PointerType) type);
        }
        if ( type instanceof ArrayType) {
            return this.generateArrayTypeCode((ArrayType) type);
        }

        throw new IllegalArgumentException("Type \""+type.getName()+"\" is currently not supported");
    }

    private LLVMBaseType generatePointerTypeCode(PointerType pointerType) {
        LLVMBaseType type   = this.generateRawTypeCode(pointerType.getRawType());
        return LLVMPointerType.createPointerType(type);
    }

    private LLVMBaseType generateVectorTypeCode(VectorType vectorType) {
        LLVMBaseType type   = this.generateRawTypeCode(vectorType.getRawType());
        return LLVMVectorType.createVectorType(type, vectorType.getDimensions());
    }

    private LLVMBaseType generateArrayTypeCode(ArrayType arrayType) {
        int dimensions      = arrayType.getDimensions();
        // TODO: implement support for multiple dimensions
        LLVMBaseType type   = this.generateRawTypeCode(arrayType.getRawType());
        return LLVMPointerType.createPointerType(type);
    }

    private LLVMBaseType generatePrimitiveTypeCode(PrimitiveType type) {
        return this.generateRawTypeCode(type.getRawType());
    }

    private LLVMBaseType generateRawTypeCode(RawType type) {
        switch(type) {
            case VOID:
                return LLVMType.voidType();
            case FLOAT:
                return LLVMRealTypes.floatType();
            case DOUBLE:
                return LLVMRealTypes.doubleType();
            case BOOLEAN:
                return LLVMIntTypes.int1Type();
            case BYTE:
                return LLVMIntTypes.int8Type();
            case SHORT:
                return LLVMIntTypes.int16Type();
            case CHAR:
                return LLVMIntTypes.int16Type();
            case INT:
                return LLVMIntTypes.int32Type();
            case LONG:
                return LLVMIntTypes.int64Type();
            default:
                return null;
        }
    }

}
