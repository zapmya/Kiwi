package de.goto3d.kiwi.compiler.codegenerator;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.llvmbindings.LLVMValue;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 11.02.13
 * Time: 06:35
 */
public interface CodeGenerator<T extends AstNode> {

    LLVMValue generateCode(T astNode);

}
