package de.goto3d.kiwi.compiler.parser.constructors;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.expressions.ExpressionListNode;
import de.goto3d.kiwi.compiler.ast.constructors.VectorConstructorNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da gru on 20.12.14.
 *
 */
@ProcessRule(rule={
        "<Constructor> ::= new VectorType ( <ExpressionList> )"
})
public class VectorConstructorParser extends ReductionBase {

    public VectorConstructorParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();

        AstNode astNode     = ((ReductionBase) reduction.get(3).getData()).getAstNode();

        this.astNode        = new VectorConstructorNode(this.convertPosition(parser), (ExpressionListNode)astNode);
    }
}
