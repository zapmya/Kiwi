package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.expressions.RelationExpressionNode;
import de.goto3d.kiwi.compiler.ast.statements.IfElseStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.02.13
 * Time: 20:43
 */
@ProcessRule(rule={
        "<IfElseStatement> ::= if ( <RelExpression> ) <BlockOrStatement> else <BlockOrStatement>"
})
public class IfElseStatementParser extends ReductionBase {

    public IfElseStatementParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();

        ReductionBase relExpressionParser = ((ReductionBase) reduction.get(2).getData());
        ReductionBase ifBlockParser = ((ReductionBase) reduction.get(4).getData());
        ReductionBase elseBlockParser = ((ReductionBase) reduction.get(6).getData());

        this.astNode = new IfElseStatementNode(
                this.convertPosition(parser),
                (RelationExpressionNode)relExpressionParser.getAstNode(),
                (BlockNode)ifBlockParser.getAstNode(),
                (BlockNode)elseBlockParser.getAstNode()
        );
    }
}
