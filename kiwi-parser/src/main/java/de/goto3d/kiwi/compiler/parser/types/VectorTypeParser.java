package de.goto3d.kiwi.compiler.parser.types;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.TypeNode;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.ast.types.VectorType;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da da gru on 10.04.16.
 *
 */
@ProcessRule(rule={
        "<FlatType> ::= VectorType"
})
public class VectorTypeParser extends ReductionBase {

    public VectorTypeParser(GOLDParser parser) {
        Reduction reduction             = parser.getCurrentReduction();

        Token token                     = reduction.get(0);
        Type type                       = VectorType.getByNameAndDim(token.getData().toString());

        SourcePosition sourcePosition   = this.convertPosition(parser);
        this.astNode                    = new TypeNode(sourcePosition, type);
    }

}
