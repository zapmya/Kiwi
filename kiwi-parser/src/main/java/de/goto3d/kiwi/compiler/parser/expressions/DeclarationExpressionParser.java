package de.goto3d.kiwi.compiler.parser.expressions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.TypeNode;
import de.goto3d.kiwi.compiler.ast.expressions.DeclarationNode;
import de.goto3d.kiwi.compiler.ast.expressions.IdentifierNode;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 28.09.13
 * Time: 12:57
 */
@ProcessRule(rule={
        "<DeclarationExpression> ::= <Type> Identifier"
})
public class DeclarationExpressionParser extends ReductionBase {

    public DeclarationExpressionParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        ReductionBase typeParser= (ReductionBase) reduction.get(0).getData();
        TypeNode typeNode       = (TypeNode) typeParser.getAstNode();
        Type type               = typeNode.getType();

        String identifier       = reduction.get(1).getData().toString();

        SourcePosition sourcePosition   = this.convertPosition(parser);
        this.astNode                    = new DeclarationNode(
                sourcePosition,type,new IdentifierNode(sourcePosition,identifier)
        );
    }
}
