package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.ast.statements.BreakStatementNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBuilder;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.08.19
 * Time: 19:15
 */
public class BreakStatementGenerator extends CodeGeneratorBase<BreakStatementNode> {

    public BreakStatementGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(BreakStatementNode breakStatementNode) {
        LLVMBuilder builder = this.visitor.getBuilder();
        return builder.createBranch(this.visitor.peekLoopEntry().loopEndBlock);
    }
}
