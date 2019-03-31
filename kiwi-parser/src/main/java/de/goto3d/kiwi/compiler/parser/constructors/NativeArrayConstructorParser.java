package de.goto3d.kiwi.compiler.parser.constructors;

/**
 * Created by da da gru on 19.06.16.
 *
 */

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.constructors.NativeArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.expressions.NumberNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

@ProcessRule(rule={
        "<ArrayConstructor> ::= native <Number>"
})
public class NativeArrayConstructorParser extends ReductionBase {

    public NativeArrayConstructorParser(GOLDParser parser) {

        Reduction reduction     = parser.getCurrentReduction();

        NumberNode numberNode   = (NumberNode)((ReductionBase) reduction.get(1).getData()).getAstNode();

        this.astNode            = new NativeArrayConstructorNode(this.convertPosition(parser),numberNode);
    }
}
