package de.goto3d.kiwi.compiler.parser.constructors;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.TypeNode;
import de.goto3d.kiwi.compiler.ast.constructors.ArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.expressions.DimExpressionsNode;
import de.goto3d.kiwi.compiler.ast.expressions.DimNode;
import de.goto3d.kiwi.compiler.ast.types.ArrayType;
import de.goto3d.kiwi.compiler.parser.ReductionBase;
import de.goto3d.kiwi.compiler.parser.expressions.DimParser;
import de.goto3d.kiwi.compiler.parser.types.PrimitiveTypeParser;

/**
 * Created by da da gru on 12.04.16.
 *
 */
@ProcessRule(rule={
        "<ArrayConstructor> ::= new <FlatType> <DimExprs> <Dims>",
        "<ArrayConstructor> ::= new <FlatType> <DimExprs>"
})
public class ArrayConstructorParser extends ReductionBase {

    public ArrayConstructorParser(GOLDParser parser) {

        Reduction reduction             = parser.getCurrentReduction();

        Token token                     = reduction.get(1);
        ArrayType arrayType;
        DimExpressionsNode dimExpressionsNode;
        PrimitiveTypeParser typeParser  = (PrimitiveTypeParser) token.getData();
        TypeNode typeNode               = (TypeNode) typeParser.getAstNode();

        dimExpressionsNode              = (DimExpressionsNode) (
                (ReductionBase) reduction.get(2).getData()
        ).getAstNode();

        // extra dimensions without expressions
        int dim;
        if ( reduction.size() == 4 ) {
            DimNode dimNode = (DimNode) ((DimParser) reduction.get(3).getData()).getAstNode();
            dim             = dimNode.getDimensions();
        } else {
            dim             = 0;
        }

        arrayType                       = new ArrayType(typeNode.getType(), dimExpressionsNode.size()+dim);

        this.astNode                    = new ArrayConstructorNode(
                this.convertPosition(parser),
                arrayType,
                dimExpressionsNode
        );

    }

}
