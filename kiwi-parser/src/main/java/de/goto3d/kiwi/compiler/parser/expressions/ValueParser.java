package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.expressions.PrecedenceNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 11:02
 */
@ProcessRule(rule={
        "<Value> ::= Identifier",
        "<Value> ::= ( <Expression> )"
})
public class ValueParser extends ReductionBase {

    public ValueParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        int reductionSize = reduction.size();
        switch(reductionSize) {
            case 1:
                String identifier = reduction.get(0).getData().toString();
                this.astNode = new IdentifierNode(this.convertPosition(parser),identifier);
                break;
            case 3:
                this.astNode = new PrecedenceNode(
                        this.convertPosition(parser),
                        (ExpressionNode) ((ReductionBase) reduction.get(1).getData()).getAstNode()
                );
                break;
        }
    }
}
