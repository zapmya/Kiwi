package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.statements.SimpleStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;
import de.goto3d.kiwi.compiler.parser.expressions.DeclarationExpressionParser;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 11:00
 */
@ProcessRule(rule={
        "<AssignmentStatement> ::= <DeclarationExpression> = <Expression> ;",
})
public class DeclarationAndAssignmentParser extends ReductionBase {

    public DeclarationAndAssignmentParser(GOLDParser parser) {
        Reduction reduction             = parser.getCurrentReduction();
        DeclarationExpressionParser declarationExpressionParser =
                (DeclarationExpressionParser)reduction.get(0).getData();
        ReductionBase expressionParser  = (ReductionBase) reduction.get(2).getData();
        DeclarationNode declarationNode = (DeclarationNode)declarationExpressionParser.getAstNode();
        AssignmentNode assignmentNode   = new DeclarationAndAssignmentNode(
                this.convertPosition(parser),
                declarationNode, (ExpressionNode)expressionParser.getAstNode()
        );

        this.astNode    = new SimpleStatementNode(this.convertPosition(parser),assignmentNode);
    }
}
