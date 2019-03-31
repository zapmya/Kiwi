package de.goto3d.kiwi.compiler.parser.types;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.statements.SimpleStatementNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementListNode;
import de.goto3d.kiwi.compiler.ast.types.StructNode;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

/**
 * Created by da da gru on 06.06.17.
 *
 */
@ProcessRule(rule={
        "<Struct> ::= struct Identifier { <DeclarationStatements> }"
})
public class StructTypeParser extends ReductionBase{

    public StructTypeParser(GOLDParser parser) {

        Reduction reduction = parser.getCurrentReduction();

        String identifier                   = (String)reduction.get(1).getData();
        ReductionBase data                  = (ReductionBase) reduction.get(3).getData();
        SourcePosition sourcePosition       = this.convertPosition(parser);

        AstNode astNode                     = data.getAstNode();
        if ( astNode instanceof SimpleStatementNode ) {
            SimpleStatementNode declarationNode = (SimpleStatementNode) astNode;
            this.astNode = new StructNode(
                    sourcePosition, identifier, new StatementListNode(sourcePosition,declarationNode)
            );
        } else {
            StatementListNode declarationNodes = (StatementListNode) astNode;
            this.astNode = new StructNode(
                    sourcePosition, identifier, declarationNodes
            );
        }
    }
}
