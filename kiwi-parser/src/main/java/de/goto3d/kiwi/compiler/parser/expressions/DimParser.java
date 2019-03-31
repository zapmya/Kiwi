package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.DimNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da da gru on 20.04.16.
 *
 */
@ProcessRule(rule={
        "<Dims> ::= [ ]",
        "<Dims> ::= <Dims> [ ]"
})
public class DimParser extends ReductionBase {

    public DimParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();
        Token token = reduction.get(0);
        String tokenName = token.getName();
        int dimensions;
        if ("Dims".equals(tokenName)) {
            DimNode dimNode = (DimNode) ((DimParser)token.getData()).getAstNode();
            dimensions = dimNode.getDimensions()+1;
        } else {
            dimensions = 1;
        }

        this.astNode = new DimNode(this.convertPosition(parser), dimensions);
    }
}
