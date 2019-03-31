package de.goto3d.kiwi.compiler.parser.functions;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import com.creativewidgetworks.goldparser.parser.ProcessRule;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.statements.ReturnStatementNode;
import de.goto3d.kiwi.compiler.ast.statements.StatementNode;
import de.goto3d.kiwi.compiler.ast.types.PrimitiveType;
import de.goto3d.kiwi.compiler.ast.types.Type;
import de.goto3d.kiwi.compiler.parser.ReductionBase;

import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 10:24
 */

@ProcessRule(rule={
        "<Function> ::= <DeclarationExpression> ( <ArgumentList> ) <Block>"
})
public class InternalFunctionParser extends ReductionBase {

    public InternalFunctionParser(GOLDParser parser) {
        Reduction reduction = parser.getCurrentReduction();

        DeclarationNode declarationNode = (DeclarationNode)((ReductionBase) reduction.get(0).getData()).getAstNode();

        AstNode argumentNode            = ((ReductionBase) reduction.get(2).getData()).getAstNode();
        ArgumentListNode argumentListNode;
        if ( argumentNode instanceof DeclarationNode ) {
            argumentListNode    = new ArgumentListNode(this.convertPosition(parser), (DeclarationNode) argumentNode);
        } else {
            argumentListNode    = (ArgumentListNode) argumentNode;
        }

        InternalFunctionNode functionNode   = new InternalFunctionNode(
                this.convertPosition(parser),
                declarationNode, argumentListNode,
                (BlockNode) ((ReductionBase) reduction.get(4).getData()).getAstNode()
        );

        // does function end with a return statement ?
        // TODO: we have to check every leaf node
        StatementNode statementNode = functionNode.getBlockNode().getStatementListNode().getLastItem();
        if ( !(statementNode instanceof ReturnStatementNode) ) {
            // no -> insert return (void) statement
            SourcePosition sourcePosition = this.convertPosition(parser);
            ReturnStatementNode returnStatementNode = new ReturnStatementNode(
                    sourcePosition,
                    this.createExpression(declarationNode.getType(), sourcePosition)
            );
            functionNode.getBlockNode().getStatementListNode().add(returnStatementNode);
        }
        this.astNode    = functionNode;
    }

    private ExpressionNode createExpression(Type type, SourcePosition sourcePosition) {

        if ( type.getClass() != PrimitiveType.class ) {
            throw new IllegalArgumentException("currently only primitive types are supported");
        }
        PrimitiveType primitiveType = (PrimitiveType)type;

        switch(primitiveType.getRawType()) {
            case VOID:
                return new VoidExpressionNode(sourcePosition);
            case FLOAT:
                return new NumberNode(sourcePosition,0.0F);
            case DOUBLE:
                return new NumberNode(sourcePosition,0.0);
            default:
                return new NumberNode(sourcePosition,new BigInteger("0"));
        }
    }
}
