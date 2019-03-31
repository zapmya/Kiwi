package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.statements.SimpleStatementNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementListNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementNode;
import de.goto3d.kiwi.compiler.parser.ListParser;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 14:21
 */
@ProcessRule(rule={
        "<DeclarationStatements> ::= <DeclarationStatements> <DeclarationStatement>"
})
public class DeclarationStatementListParser extends ListParser<StatementNode> {

    public DeclarationStatementListParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        StatementListNode statementListNode = new StatementListNode(this.convertPosition(parser));

        this.parseList(statementListNode, reduction);

        this.astNode = statementListNode;
    }

    @Override
    protected StatementNode createTerminalNode(Token token) {
        return null;
    }
}
