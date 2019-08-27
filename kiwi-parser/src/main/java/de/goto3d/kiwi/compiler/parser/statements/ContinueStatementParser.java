package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.statements.BreakStatementNode;
import de.goto3d.kiwi.compiler.ast.statements.ContinueStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 27.08.19
 * Time: 20:20
 */
@ProcessRule(rule={
        "<ContinueStatement> ::= continue ;"
})
public class ContinueStatementParser extends ReductionBase {

    public ContinueStatementParser(GOLDParser parser) {
        this.astNode = new ContinueStatementNode(this.convertPosition(parser));
    }
}
