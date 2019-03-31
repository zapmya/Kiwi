package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.DimExpressionsNode;
import de.goto3d.kiwi.compiler.ast.expressions.DimNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da da gru on 23.04.16.
 *
 */
@ProcessRule(rule={
        "<DimExprs> ::= [ <Expression> ]",
        "<DimExprs> ::= <DimExprs> [ <Expression> ]"
})
public class DimExpressionsParser extends ReductionBase {

    public DimExpressionsParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();

        Token token = reduction.get(0);
        String tokenName = token.getName();
        if ( "DimExprs".equals(tokenName) ) {
            DimExpressionsNode dimExpressionsNode =
                    (DimExpressionsNode) ((DimExpressionsParser)token.getData()).getAstNode();

            ReductionBase expressionParser = (ReductionBase) reduction.get(2).getData();
            ExpressionNode expressionNode = (ExpressionNode) expressionParser.getAstNode();
            dimExpressionsNode.add(expressionNode);
            this.astNode = dimExpressionsNode;
        } else {
            ReductionBase expressionParser = (ReductionBase) reduction.get(1).getData();
            ExpressionNode expressionNode = (ExpressionNode) expressionParser.getAstNode();
            this.astNode = new DimExpressionsNode(convertPosition(parser), expressionNode);
        }
    }

}
