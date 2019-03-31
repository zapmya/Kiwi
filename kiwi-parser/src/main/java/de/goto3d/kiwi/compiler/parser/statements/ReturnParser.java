package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.statements.ReturnStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 14:05
 */
@ProcessRule(rule={
        "<ReturnStatement> ::= return <Expression> ;"
})
public class ReturnParser extends ReductionBase {

    public ReturnParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        this.astNode = new ReturnStatementNode(
                this.convertPosition(parser),
                (ExpressionNode) ((ReductionBase) reduction.get(1).getData()).getAstNode()
        );
    }
}
