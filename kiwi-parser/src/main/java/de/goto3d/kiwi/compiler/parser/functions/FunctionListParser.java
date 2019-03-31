package de.goto3d.kiwi.compiler.parser.functions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.parser.ListParser;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 14:21
 */
@ProcessRule(rule={
        "<Functions> ::= <Prototype> <Functions>"
})
public class FunctionListParser extends ListParser<ExternalFunctionNode> {

    public FunctionListParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        FunctionListNode functionListNode   = new FunctionListNode(this.convertPosition(parser));

        this.parseList(functionListNode, reduction);

        this.astNode = functionListNode;
    }

    @Override
    protected InternalFunctionNode createTerminalNode(Token token) {
        return null;
    }
}
