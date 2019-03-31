package de.goto3d.kiwi.compiler.parser.types;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.TypeNode;
import de.goto3d.kiwi.compiler.ast.expressions.DimNode;
import de.goto3d.kiwi.compiler.ast.types.*;
import de.goto3d.kiwi.compiler.parser.ReductionBase;
import de.goto3d.kiwi.compiler.parser.expressions.DimParser;

/**
 * Created by da da gru on 10.04.16.
 *
 */
@ProcessRule(rule={
        "<ArrayType> ::= <FlatType> <Dims>"
})
public class ArrayTypeParser extends ReductionBase {

    public ArrayTypeParser(GOLDParser parser) {
        Reduction reduction             = parser.getCurrentReduction();

        Token token                     = reduction.get(0);
        PrimitiveTypeParser typeParser  = (PrimitiveTypeParser) token.getData();
        TypeNode typeNode               = (TypeNode) typeParser.getAstNode();
        Type type                       = typeNode.getType();
        DimNode dimNode                 = (DimNode) ((DimParser) reduction.get(1).getData()).getAstNode();
        int dim                         = dimNode.getDimensions();

        SourcePosition sourcePosition   = this.convertPosition(parser);
        this.astNode                    = new TypeNode(sourcePosition, new ArrayType(type, dim));
    }

}
