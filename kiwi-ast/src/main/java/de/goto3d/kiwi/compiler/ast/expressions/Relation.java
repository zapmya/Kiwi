package de.goto3d.kiwi.compiler.ast.expressions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 05.02.13
 * Time: 06:41
 */
public enum Relation {

    EQUAL("=="),
    NOT_EQUAL("!="),
    LOWER_THAN("<"),
    LOWER_OR_EQUAL("<="),
    GREATER_THAN(">"),
    GREATER_OR_EQUAL(">=");

    private final static Map<String, Relation> LOOKUP_MAP   = new HashMap<String, Relation>(6);
    static {
        for (Relation relation : Relation.values()) {
            LOOKUP_MAP.put(relation.identifier,relation);
        }
    }

    public final String identifier;

    private Relation(String identifier) {
        this.identifier = identifier;
    }

    public static Relation valueOfIdentifier(String identifier) {
        return LOOKUP_MAP.get(identifier);
    }

}
