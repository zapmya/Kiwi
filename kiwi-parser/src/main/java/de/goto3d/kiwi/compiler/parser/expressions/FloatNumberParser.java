package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.NumberNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

import java.math.BigInteger;

/**
 * Created by da da gru on 18.06.16.
 *
 */
@ProcessRule(rule={
                "<Number> ::= FloatingPointLiteral"
})
public class FloatNumberParser extends ReductionBase {

    public FloatNumberParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();
        String numberString = reduction.get(0).getData().toString();
        this.astNode = new NumberNode(this.convertPosition(parser),Double.parseDouble(numberString));
    }
}
