package de.goto3d.kiwi.compiler.parser.functions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.expressions.ArgumentListNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.02.13
 * Time: 20:54
 */
@ProcessRule(rule={
        "<ExternFunction> ::= extern <DeclarationExpression> ( <ArgumentList> ) ;"
})
public class ExternalFunctionsParser extends ReductionBase {

    public ExternalFunctionsParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        DeclarationNode declarationNode = (DeclarationNode)((ReductionBase) reduction.get(1).getData()).getAstNode();

        AstNode astNode = ((ReductionBase) reduction.get(3).getData()).getAstNode();
        ArgumentListNode argumentListNode;
        if ( astNode instanceof ArgumentListNode ) {
            argumentListNode = (ArgumentListNode)astNode;
        } else if ( astNode instanceof DeclarationNode ) {
            argumentListNode = new ArgumentListNode(this.convertPosition(parser),(DeclarationNode)astNode);
        } else {
            throw new IllegalArgumentException("Unexpected type \""+astNode.getClass().getSimpleName() +"\"");
        }

        this.astNode = new ExternalFunctionNode(
                this.convertPosition(parser),
                declarationNode, argumentListNode
        );
    }
}
