package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.OperationNode;
import de.goto3d.kiwi.compiler.ast.expressions.Operator;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 10:57
 */
@ProcessRule(rule={
        "<Mult Exp> ::= <Mult Exp> * <Negate Exp>",
        "<Mult Exp> ::= <Mult Exp> / <Negate Exp>",
        "<Mult Exp> ::= <Mult Exp> % <Negate Exp>",
        "<Add Exp> ::= <Add Exp> + <Mult Exp>",
        "<Add Exp> ::= <Add Exp> - <Mult Exp>"
})
public class ArithmeticExpressionParser extends ReductionBase {

    public ArithmeticExpressionParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();
        ReductionBase leftExprParser = (ReductionBase) reduction.get(0).getData();
        Operator operator = Operator.valueOf(reduction.get(1).getData().toString().charAt(0));
        ReductionBase rightExprParser = (ReductionBase) reduction.get(2).getData();

        ExpressionNode leftHandExpression = (ExpressionNode) leftExprParser.getAstNode();
        ExpressionNode rightHandExpression = (ExpressionNode) rightExprParser.getAstNode();

        this.astNode = new OperationNode(
                this.convertPosition(parser),
                operator, leftHandExpression, rightHandExpression
        );
    }
}
