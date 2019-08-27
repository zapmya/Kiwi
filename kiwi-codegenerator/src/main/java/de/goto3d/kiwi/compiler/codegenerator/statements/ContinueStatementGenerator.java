package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.ast.statements.ContinueStatementNode;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorBase;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMBuilder;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.08.19
 * Time: 20:28
 */
public class ContinueStatementGenerator extends CodeGeneratorBase<ContinueStatementNode> {

    public ContinueStatementGenerator(CodeGeneratorVisitor visitor) {
        super(visitor);
    }

    @Override
    public LLVMValue generateCode(ContinueStatementNode continueStatementNode) {
        LLVMBuilder builder = this.visitor.getBuilder();
        return builder.createBranch(this.visitor.peekLoopEntry().loopBlock);
    }
}
