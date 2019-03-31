package de.goto3d.kiwi.compiler.parser;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionListNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 13:54
 */
@ProcessRule(rule={
        "<ExpressionList> ::= <Expression> , <ExpressionList>",
        "<ExpressionList> ::= "
})
public class ExpressionListParser extends ListParser<ExpressionNode> {

    public ExpressionListParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        ExpressionListNode expressionListNode = new ExpressionListNode(this.convertPosition(parser));

        int size = reduction.size();
        for ( int i = 0; i < size; i+=2 ) { // add 2 to skip ',' terminals
            this.parseList(expressionListNode, reduction.get(i));
        }

        this.astNode    = expressionListNode;
    }

    @Override
    protected ExpressionNode createTerminalNode(Token token) {
        return null;
    }
}
