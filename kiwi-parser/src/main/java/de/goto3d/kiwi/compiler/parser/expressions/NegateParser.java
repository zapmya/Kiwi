package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.NegateNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 11:03
 */
@ProcessRule(rule={
        "<Negate Exp> ::= - <Value>"
})
public class NegateParser extends ReductionBase {

    public NegateParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();
        ReductionBase valueParser = (ReductionBase) reduction.get(1).getData();
        this.astNode = new NegateNode(this.convertPosition(parser),(ExpressionNode) valueParser.getAstNode());
    }
}
