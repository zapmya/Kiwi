package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.AssignmentNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.statements.SimpleStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 11:00
 */
@ProcessRule(rule={
        "<AssignmentStatement> ::= Identifier = <Expression> ;"
})
public class AssignmentParser extends ReductionBase {

    public AssignmentParser(GOLDParser parser) {
        Reduction reduction     = parser.getCurrentReduction();
        Object firstDataElement = reduction.get(0).getData();
        String identifier       = firstDataElement.toString();
        ReductionBase expressionParser  = (ReductionBase) reduction.get(2).getData();
        AssignmentNode assignmentNode   = new AssignmentNode(
                this.convertPosition(parser),
                new IdentifierNode(this.convertPosition(parser),identifier),
                (ExpressionNode)expressionParser.getAstNode()
        );

        this.astNode    = new SimpleStatementNode(this.convertPosition(parser),assignmentNode);
    }
}
