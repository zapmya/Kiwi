package de.goto3d.kiwi.compiler.codegenerator.constructors;

import de.goto3d.kiwi.compiler.ast.constructors.NativeArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.types.PointerType;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeGenerator;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBaseType;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by da da gru on 13.04.16.
 *
 */
public class NativeArrayConstructorGenerator extends CodeGeneratorBase<NativeArrayConstructorNode> {

    private static final PointerType BYTE_POINTER_TYPE = new PointerType(new PrimitiveType(RawType.BYTE));

    private final TypeGenerator typeGenerator;

    public NativeArrayConstructorGenerator(CodeGeneratorVisitor visitor, TypeGenerator typeGenerator) {
        super(visitor);
        this.typeGenerator  = typeGenerator;
    }


    @Override
    public LLVMValue generateCode(NativeArrayConstructorNode astNode) {

        // get address
        LLVMValue addressValue  = astNode.getAddress().accept(this.visitor);
        // get destination type
        LLVMBaseType destType   = this.typeGenerator.generateCode(BYTE_POINTER_TYPE);
        // convert to pointer
        return this.visitor.getBuilder().createIntToPtr(addressValue, destType, "intToPtr");
    }
}
