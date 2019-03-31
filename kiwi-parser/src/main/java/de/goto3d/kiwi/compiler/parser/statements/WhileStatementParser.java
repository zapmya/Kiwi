package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.expressions.RelationExpressionNode;
import de.goto3d.kiwi.compiler.ast.statements.WhileStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.02.13
 * Time: 10:14
 */
@ProcessRule(rule={
        "<WhileStatement> ::= while ( <RelExpression> ) <BlockOrStatement>"
})
public class WhileStatementParser extends ReductionBase {

    public WhileStatementParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();

        ReductionBase relExpressionParser = ((ReductionBase) reduction.get(2).getData());
        ReductionBase blockParser = ((ReductionBase) reduction.get(4).getData());

        this.astNode = new WhileStatementNode(
                this.convertPosition(parser),
                (RelationExpressionNode)relExpressionParser.getAstNode(), (BlockNode)blockParser.getAstNode()
        );

    }
}
