package de.goto3d.kiwi.compiler.parser.classes;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.classes.ClassNode;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da da gru on 15.07.15.
 *
 */
@ProcessRule(rule={
        "<Class> ::= class Identifier { <Functions> }"
})
public class ClassParser extends ReductionBase {

    public ClassParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();

        String identifier                   = (String)reduction.get(1).getData();
        FunctionListNode functionListNode   = (FunctionListNode)((ReductionBase)reduction.get(3).getData()).getAstNode();

        this.astNode = new ClassNode(this.convertPosition(parser),identifier, functionListNode);
    }
}
