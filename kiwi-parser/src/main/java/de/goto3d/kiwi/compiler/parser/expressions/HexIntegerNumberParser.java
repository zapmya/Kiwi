package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.NumberNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 10:48
 */

@ProcessRule(rule={
        "<Number> ::= HexIntegerLiteral"
})
public class HexIntegerNumberParser extends ReductionBase {

    public HexIntegerNumberParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();
        String numberString = reduction.get(0).getData().toString().substring(2);
        this.astNode = new NumberNode(this.convertPosition(parser),new BigInteger(numberString,16));
    }
}
