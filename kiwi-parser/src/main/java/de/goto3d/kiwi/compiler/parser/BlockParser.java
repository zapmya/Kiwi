package de.goto3d.kiwi.compiler.parser;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementListNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 09:46
 */
@ProcessRule(rule={
        "<Block> ::= { <Statements> }"
})
public class BlockParser extends ReductionBase {

    public BlockParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        AstNode astNode = ((ReductionBase) reduction.get(1).getData()).astNode;
        // list of statements ?
        // NB: We have to add this check because the GOLDParser engine will "trim" empty nodes
        // in the parsed tree. Actually that's a good thing but in this one case
        // it makes us stumble.
        if ( !(astNode instanceof StatementListNode) ) {
            // no -> create new node
            astNode = new StatementListNode(this.convertPosition(parser),(StatementNode) astNode);
        }

        this.astNode = new BlockNode(this.convertPosition(parser),(StatementListNode) astNode);
    }
}
