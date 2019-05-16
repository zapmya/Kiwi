package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.Relation;
import de.goto3d.kiwi.compiler.ast.expressions.RelationExpressionNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.02.13
 * Time: 06:37
 */
@ProcessRule(rule={
        "<RelExpression> ::= <RelExpression> < <Add Exp>",
        "<RelExpression> ::= <RelExpression> > <Add Exp>",
        "<RelExpression> ::= <RelExpression> == <Add Exp>",
        "<RelExpression> ::= <RelExpression> != <Add Exp>",
        "<RelExpression> ::= <RelExpression> <= <Add Exp>",
        "<RelExpression> ::= <RelExpression> >= <Add Exp>"
})

public class RelationalExpressionParser extends ReductionBase {

    public RelationalExpressionParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();
        ReductionBase leftExprParser = (ReductionBase) reduction.get(0).getData();
        Relation relation = Relation.valueOfIdentifier(reduction.get(1).getData().toString());
        ReductionBase rightExprParser = (ReductionBase) reduction.get(2).getData();

        this.astNode = new RelationExpressionNode(
                this.convertPosition(parser),
                relation, (ExpressionNode)leftExprParser.getAstNode(), (ExpressionNode)rightExprParser.getAstNode()
        );

    }
}
