package de.goto3d.kiwi.compiler.ast.types;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 21.09.13
 * Time: 12:00
 */
public enum RawType {
    VOID("void"),

    BOOLEAN("boolean",0,1),

    BYTE("byte",1, 8),
    SHORT("short",2, 16),
    CHAR("char",3, 16),
    INT("int",4, 32),
    LONG("long",5, 64),

    FLOAT("float",6, 32),
    DOUBLE("double",7, 64);

    private final static Map<String, RawType> TYPE_NAME_MAP    = new HashMap<String, RawType>(RawType.values().length);
    static {
        for (RawType type : RawType.values()) {
            TYPE_NAME_MAP.put(type.typeName, type);
        }
    }

    private final String typeName;
    public final int order;
    public final int bits;

    private RawType(String typeName) {
        this(typeName,0,0);
    }
    private RawType(String typeName, int order, int bits) {
        this.typeName   = typeName;
        this.order      = order;
        this.bits       = bits;
    }

    public String getTypeName() {
        return typeName;
    }

    public static RawType getByName(String name) {
        return TYPE_NAME_MAP.get(name);
    }

    public boolean isInteger() {
        return this == BYTE || this == SHORT || this==CHAR || this == INT || this == LONG;
    }

    public boolean isFloat() {
        return this == FLOAT || this == DOUBLE;
    }
}
