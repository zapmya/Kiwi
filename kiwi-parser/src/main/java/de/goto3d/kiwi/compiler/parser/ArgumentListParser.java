package de.goto3d.kiwi.compiler.parser;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ArgumentListNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 08:07
 */
@ProcessRule(rule={
        "<ArgumentList> ::= ",
        "<ArgumentList> ::= <DeclarationExpression>",
        "<ArgumentList> ::= <ArgumentList> , <DeclarationExpression>"
})
public class ArgumentListParser extends ListParser<DeclarationNode> {

    public ArgumentListParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        ArgumentListNode argumentListNode = new ArgumentListNode(this.convertPosition(parser));

        int size = reduction.size();
        for ( int i = 0; i < size; i+=2 ) { // add 2 to skip ',' terminals
            this.parseList(argumentListNode, reduction.get(i));
        }

        this.astNode    = argumentListNode;
    }

    @Override
    protected DeclarationNode createTerminalNode(Token token) {
        throw new IllegalStateException("What the hell...");
    }
}
