package de.goto3d.kiwi.compiler.parser.statements;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.statements.SimpleStatementNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;
import de.goto3d.kiwi.compiler.parser.expressions.IndexAccessParser;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 11:00
 */
@ProcessRule(rule={
        "<AssignmentStatement> ::= <IndexAccess> = <Expression> ;",
})
public class IndexedAssignmentParser extends ReductionBase {

    public IndexedAssignmentParser(GOLDParser parser) {
        Reduction reduction             = parser.getCurrentReduction();
        IndexAccessParser indexAccessParser =
                (IndexAccessParser)reduction.get(0).getData();
        ReductionBase expressionParser  = (ReductionBase) reduction.get(2).getData();
        IndexAccessNode accessNode      = (IndexAccessNode)indexAccessParser.getAstNode();
        AssignmentNode assignmentNode   = new IndexedAssignmentNode(
                this.convertPosition(parser),
                accessNode, (ExpressionNode)expressionParser.getAstNode()
        );

        this.astNode    = new SimpleStatementNode(this.convertPosition(parser),assignmentNode);
    }
}
