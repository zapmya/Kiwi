package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.statements.SimpleStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 14:02
 */
@ProcessRule(rule={
        "<CallStatement> ::= <CallExp> ;",
        "<DeclarationStatement> ::= <DeclarationExpression> ;"

})
public class SimpleStatementParser extends ReductionBase {

    public SimpleStatementParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();
        ReductionBase expressionParser = (ReductionBase) reduction.get(0).getData();

        this.astNode = new SimpleStatementNode(
                this.convertPosition(parser),(ExpressionNode) expressionParser.getAstNode()
        );
    }
}
