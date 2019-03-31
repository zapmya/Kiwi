package de.goto3d.kiwi.compiler.ast.expressions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 10.01.13
 * Time: 07:01
 */
public enum Operator {
    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/'),
    REMAINDER('%');

    private final static Map<Character, Operator> LOOKUP_MAP   = new HashMap<Character, Operator>(4);
    static {
        for (Operator operator : Operator.values()) {
            LOOKUP_MAP.put(operator.character,operator);
        }
    }

    public final char character;

    private Operator(char character) {
        this.character = character;
    }

    public static Operator valueOf(char ch) {
        return LOOKUP_MAP.get(ch);
    }
}
