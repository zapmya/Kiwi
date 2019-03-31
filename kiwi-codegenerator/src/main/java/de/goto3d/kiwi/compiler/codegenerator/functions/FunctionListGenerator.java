package de.goto3d.kiwi.compiler.codegenerator.functions;

import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.03.13
 * Time: 06:48
 */
public class FunctionListGenerator extends CodeGeneratorBase<FunctionListNode> {

    private final Map<FunctionKey, LLVMValue> functionMap = new HashMap<FunctionKey, LLVMValue>();

    public FunctionListGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(FunctionListNode astNode) {

        List<ExternalFunctionNode> items = astNode.getItems();

        // generate functions
        for (ExternalFunctionNode item : items) {
            FunctionKey key = new FunctionKey(item);
            LLVMValue function = item.accept(this.visitor);
            functionMap.put(key, function);
        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private static class FunctionKey {

        private final ExternalFunctionNode functionNode;

        private FunctionKey(ExternalFunctionNode functionNode) {
            this.functionNode = functionNode;
        }

        @Override
        public boolean equals(Object o) {
            return this == o || functionNode.getName().equals(((ExternalFunctionNode) o).getName());
        }

        @Override
        public int hashCode() {
            return functionNode.getName().hashCode();
        }
    }
}
