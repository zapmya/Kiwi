package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.expressions.CallNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionListNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 03.02.13
 * Time: 13:56
 */
@ProcessRule(rule={
        "<CallExp> ::= Identifier ( <ExpressionList> )"
})
public class CallExpressionParser extends ReductionBase {

    public CallExpressionParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        String identifier = reduction.get(0).getData().toString();
        ReductionBase expressionListParser = (ReductionBase) reduction.get(2).getData();

        AstNode astNode = expressionListParser.getAstNode();
        // list of expressions ?
        // NB: We have to add this check because the GOLDParser engine will "trim" empty nodes
        // in the parsed tree. Actually that's a good thing but in this one case
        // it makes us stumble.
        if ( !(astNode instanceof ExpressionListNode) ) {
            // no -> create new node
            astNode = new ExpressionListNode(this.convertPosition(parser),(ExpressionNode) astNode);
        }

        this.astNode    = new CallNode(
                this.convertPosition(parser),
                new IdentifierNode(this.convertPosition(parser),identifier), (ExpressionListNode)astNode
        );
    }
}
