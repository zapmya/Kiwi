package de.goto3d.kiwi.compiler.codegenerator.functions;

import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.types.PointerType;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeGenerator;
import de.goto3d.kiwi.compiler.llvmbindings.*;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCallConv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.04.13
 * Time: 08:59
 */
public class PrototypeGenerator extends CodeGeneratorBase<ExternalFunctionNode> {

    public final static LLVMModule RUNTIME_MODULE = new LLVMModule("KiwiRuntime", LLVMContext.getGlobalContext());

    private final Map<String, LLVMFunction> functionNameMap =
            new HashMap<String, LLVMFunction>();

    private final TypeGenerator typeGenerator;

    public PrototypeGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);

        this.typeGenerator  = new TypeGenerator();

        // add runtime function "kiwi_lang_alloc"
        this.addRuntimeFunction(
                "kiwi_lang_alloc",
                new PointerType(new PrimitiveType(RawType.BYTE)),
                new Argument[]{new Argument(new PrimitiveType(RawType.INT), "size")}
        );
    }

    private void addRuntimeFunction(String functionName, Type returnType, Argument[] arguments) {
        // function already created ?
        LLVMFunction function= this.functionNameMap.get(functionName);
        if ( function != null ) {
            // yes -> do not create it again!
            return;
        }

        // no -> create new instance
        int numParams                       = arguments.length;
        TypeAndName[] typeAndNames          = new TypeAndName[numParams];
        for ( int i = 0; i < numParams; i++ ) {
            Argument argument   = arguments[i];
            LLVMBaseType type   = this.typeGenerator.generateCode(argument.type);
            String name         = argument.name;
            typeAndNames[i]     = new TypeAndName(type, name);
        }

        function    = new LLVMFunction(
                functionName,
                this.typeGenerator.generateCode(returnType),
                typeAndNames,
                RUNTIME_MODULE
        );
        function.setCallingConvention(LLVMCallConv.LLVMCCallConv);

        // store function in lookup map
        this.functionNameMap.put(functionName, function);
    }

    @Override
    public LLVMFunction generateCode(ExternalFunctionNode functionNode) {

        // function already created ?
        String functionName = functionNode.getName();
        LLVMFunction function= this.functionNameMap.get(functionName);
        if ( function != null ) {
            // yes -> use it!
            return function;
        }

        // no -> create new instance
        List<DeclarationNode> declarationNodes = functionNode.getArgumentListNode().getItems();
        int numParams                       = declarationNodes.size();
        TypeAndName[] typeAndNames          = new TypeAndName[numParams];
        for ( int i = 0; i < numParams; i++ ) {
            DeclarationNode node= declarationNodes.get(i);
            LLVMBaseType type   = this.typeGenerator.generateCode(node.getType());
            String name         = node.getIdentifierNode().getIdentifier();
            typeAndNames[i]     = new TypeAndName(type, name);
        }

        function    = new LLVMFunction(
                functionName,this.typeGenerator.generateCode(functionNode.getType()),typeAndNames, visitor.getModule()
        );

        if ( functionNode instanceof InternalFunctionNode ) {
            // set internal calling convention to FAST
            function.setCallingConvention(LLVMCallConv.LLVMFastCallConv);
        } else {
            // set c calling convention
            function.setCallingConvention(LLVMCallConv.LLVMCCallConv);
        }

        // store function in lookup map
        this.functionNameMap.put(functionName, function);

        return function;
    }

    public LLVMFunction getFunction(String signature) {
        return this.functionNameMap.get(signature);
    }

    private static class Argument {
        private final Type type;
        private final String name;

        private Argument(Type type, String name) {
            this.type = type;
            this.name = name;
        }
    }
}
