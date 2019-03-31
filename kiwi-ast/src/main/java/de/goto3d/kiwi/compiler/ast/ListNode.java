package de.goto3d.kiwi.compiler.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 04.02.13
 * Time: 20:46
 */
public abstract class ListNode<T> extends AstNode {

    private final List<T> items    = new ArrayList<T>();

    protected ListNode(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    public void add(T item) {
        this.items.add(item);
    }

    public void addAll(Collection<T> items) {
        this.items.addAll(items);
    }

    public List<T> getItems() {
        return items;
    }

    public T getLastItem() {
        // any items ?
        final int numItems  = items.size();
        if ( numItems == 0 ) {
            // no -> nothing to return
            return null;
        }
        // yes -> returns last element
        return this.items.get(numItems-1);
    }

    public int size() {
        return this.items.size();
    }
}
