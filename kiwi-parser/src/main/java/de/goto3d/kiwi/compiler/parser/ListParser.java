package de.goto3d.kiwi.compiler.parser;

import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.engine.Token;
import com.creativewidgetworks.goldparser.engine.enums.SymbolType;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.ListNode;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 04.02.13
 * Time: 20:56
 */
public abstract class ListParser<T> extends ReductionBase {

    @SuppressWarnings("unchecked")
    protected void parseList(ListNode<T> listNode, Reduction reduction) {
        for (Token token : reduction) {
            this.parseList(listNode, token);
        }
    }

    @SuppressWarnings("unchecked")
    protected void parseList(ListNode<T> listNode, Token token) {

        // TODO: This is crap! Find a better way to handle terminals!!!!
        // terminal ?
        if ( token.getType() == SymbolType.CONTENT ) {
            listNode.add(this.createTerminalNode(token));
            return;
        }

        AstNode astNode = ((ReductionBase)token.getData()).astNode;
        if ( astNode == null ) {
            return;
        }
        if ( astNode instanceof ListNode<?> ) {
            listNode.addAll((Collection<T>)((ListNode<?>)astNode).getItems());
        } else {
            listNode.add((T)astNode);
        }
    }

    protected abstract T createTerminalNode(Token token);
}
