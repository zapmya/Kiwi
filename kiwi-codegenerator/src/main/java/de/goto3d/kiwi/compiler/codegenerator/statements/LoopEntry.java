package de.goto3d.kiwi.compiler.codegenerator.statements;

import de.goto3d.kiwi.compiler.llvmbindings.LLVMBasicBlock;

/**
 * Created by gru.
 * Created on 27.08.2019 - 19:37.
 */
public class LoopEntry {

    final LLVMBasicBlock loopBlock;
    final LLVMBasicBlock loopEndBlock;

    public LoopEntry(LLVMBasicBlock loopBlock, LLVMBasicBlock loopEndBlock) {
        this.loopBlock = loopBlock;
        this.loopEndBlock = loopEndBlock;
    }
}
