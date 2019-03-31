package de.goto3d.kiwi.compiler.parser.compilation;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.compilation.CompilationItemsNode;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;
import de.goto3d.kiwi.compiler.parser.ListParser;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da da gru on 06.06.17.
 *
 */
@ProcessRule(rule={
        "<Compilation Items> ::= <Compilation Items> <Compilation Item>",
        "<Compilation Items> ::= "
})
public class CompilationItemsParser extends ListParser<AstNode> {

    public CompilationItemsParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();
        if ( reduction.size() == 0 ) {
            return;
        }

        CompilationItemsNode compilationItemsNode   = new CompilationItemsNode(this.convertPosition(parser));

        this.parseList(compilationItemsNode, reduction);

        this.astNode = compilationItemsNode;
    }

    @Override
    protected AstNode createTerminalNode(Token token) {
        return null;
    }
}
