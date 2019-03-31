package de.goto3d.kiwi.compiler.parser;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.statements.StatementListNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 11:04
 */
@ProcessRule(rule={
        "<Statements> ::= <Statement> <Statements>",
        "<Statements> ::= <Statement>"
})
public class StatementListParser extends ListParser<StatementNode> {

    public StatementListParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        StatementListNode statementListNode = new StatementListNode(this.convertPosition(parser));

        this.parseList(statementListNode, reduction);

        this.astNode    = statementListNode;
    }

    @Override
    protected StatementNode createTerminalNode(Token token) {
        return null;
    }
}
