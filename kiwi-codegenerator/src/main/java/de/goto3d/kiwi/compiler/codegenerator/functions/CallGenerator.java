package de.goto3d.kiwi.compiler.codegenerator.functions;

import de.goto3d.kiwi.compiler.ast.expressions.CallNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionListNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.RawType;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMCall;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMFunction;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.04.13
 * Time: 09:12
 */
public class CallGenerator extends CodeGeneratorBase<CallNode> {

    private final PrototypeGenerator prototypeGenerator;

    public CallGenerator(CodeGeneratorVisitor visitor, PrototypeGenerator prototypeGenerator) {
        super(visitor);
        this.prototypeGenerator = prototypeGenerator;
    }

    @Override
    public LLVMValue generateCode(CallNode callNode) {

        // Look up the name in the global module table.
        String functionName                     = callNode.getIdentifierNode().getIdentifier();
        LLVMFunction function                   = this.prototypeGenerator.getFunction(functionName);
        ExternalFunctionNode functionNode       = this.visitor.getFunctionNode(functionName);

        ExpressionListNode expressionListNode   = callNode.getExpressionListNode();

        // compare number of arguments
        int numExpressions  = expressionListNode.size();
        if ( numExpressions != function.getArguments().length ) {
            return null;
        }

        // generate code for parameters
        LLVMValue[] parameters = new LLVMValue[numExpressions];
        List<ExpressionNode> expressionNodes = expressionListNode.getItems();
        for ( int i = 0; i < numExpressions; i++ ) {
            parameters[i]   = expressionNodes.get(i).accept(this.visitor);
        }

        LLVMCall callValue;
        Type type = functionNode.getType();
        // is there a return value ?
        if ( type.getClass() == PrimitiveType.class && ((PrimitiveType)type).getRawType() == RawType.VOID ) {
            // no -> create call without name for return value
            callValue = this.visitor.getBuilder().createCall(function, parameters, "");
        } else {
            // yes -> create usual call
            callValue = this.visitor.getBuilder().createCall(function, parameters, functionName);
        }
        callValue.setCallingConvention(function.getCallingConvention());

        return callValue;
    }
}
