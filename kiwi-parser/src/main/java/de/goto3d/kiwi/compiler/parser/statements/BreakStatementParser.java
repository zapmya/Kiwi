package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.statements.BreakStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.08.19
 * Time: 19:07
 */
@ProcessRule(rule={
        "<BreakStatement> ::= break ;"
})
public class BreakStatementParser extends ReductionBase {

    public BreakStatementParser(GOLDParser parser) {
        this.astNode = new BreakStatementNode(
                this.convertPosition(parser)
        );
    }
}
