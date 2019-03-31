package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.expressions.DimExpressionsNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.expressions.IndexAccessNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da da gru on 07.01.16.
 *
 */
@ProcessRule(rule={
        "<IndexAccess> ::= Identifier <DimExprs>"
})
public class IndexAccessParser extends ReductionBase {

    public IndexAccessParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        String identifier               = reduction.get(0).getData().toString();
        ReductionBase expressionParser  = (ReductionBase) reduction.get(1).getData();

        SourcePosition sourcePosition   = this.convertPosition(parser);
        this.astNode                    = new IndexAccessNode(
                sourcePosition,
                new IdentifierNode(sourcePosition,identifier),
                (DimExpressionsNode) expressionParser.getAstNode()
        );
    }

}
