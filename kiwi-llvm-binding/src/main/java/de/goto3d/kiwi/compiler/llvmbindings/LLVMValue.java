package de.goto3d.kiwi.compiler.llvmbindings;

import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMValueRefArray;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMValueRef;

import java.lang.reflect.Constructor;
import java.util.WeakHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 21:47
 */
public class LLVMValue {

    private static final WeakHashMap<SWIGTYPE_p_LLVMValueRef, LLVMValue> INSTANCE_POOL   =
            new WeakHashMap<>();
    private final static Object INSTANCE_POOL_LOCK  = new Object();

    protected SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue;

    protected LLVMValue(SWIGTYPE_p_LLVMValueRef swigtype_p_llvmOpaqueValue) {
        this.swigtype_p_llvmOpaqueValue = swigtype_p_llvmOpaqueValue;

        synchronized (INSTANCE_POOL_LOCK) {
            LLVMValue value  = INSTANCE_POOL.get(swigtype_p_llvmOpaqueValue);
            if ( value != null ) {
                throw new IllegalStateException(
                        "Trying to recreate LLVMValue wrapper instance!" +
                        "Do NOT use constructor, use getInstance() instead"
                );
            }
            INSTANCE_POOL.put(swigtype_p_llvmOpaqueValue, this);
        }
    }

    @SuppressWarnings("unchecked")
    protected static <T extends LLVMValue> T getInstance(SWIGTYPE_p_LLVMValueRef value, Class<T> valueClass) {
        synchronized (INSTANCE_POOL_LOCK) {
            T valueWrapper = (T) INSTANCE_POOL.get(value);
            if ( valueWrapper != null ) {
                return valueWrapper;
            }
            // create new instance
            try {
                Constructor<T> constructor = valueClass.getDeclaredConstructor(
                        new Class[]{SWIGTYPE_p_LLVMValueRef.class}
                );
                constructor.setAccessible(true);
                return constructor.newInstance(value);
            } catch (Throwable t) {
                throw new RuntimeException(
                        "Could not create new LLVMValue instance of type " + valueClass.getName(), t
                );
            }
        }
    }

    public static LLVMValueRefArray convertArray(LLVMValue[] values) {
        int numItems                    = values.length;
        LLVMValueRefArray valueRefArray = new LLVMValueRefArray(numItems);
        for ( int i = 0; i < numItems; i++ ) {
            LLVMValue value = values[i];
            valueRefArray.setitem(i, value.swigtype_p_llvmOpaqueValue);
        }
        return valueRefArray;
    }
    
    public void setName(String name) {
        LLVMCore.LLVMSetValueName(this.swigtype_p_llvmOpaqueValue, name);
    }

    public String getName() {
        return LLVMCore.LLVMGetValueName(this.swigtype_p_llvmOpaqueValue);
    }

    public <V extends LLVMValue> V cast(Class<V> toClass) {
        // remove this value from map
        synchronized (INSTANCE_POOL_LOCK) {
            if ( INSTANCE_POOL.remove(this.swigtype_p_llvmOpaqueValue) == null ) {
                throw new IllegalStateException(
                        String.format(
                                "There is no LLVMValue for \"%s\"", this.swigtype_p_llvmOpaqueValue
                        )
                );
            };
        }

        return LLVMValue.getInstance(this.swigtype_p_llvmOpaqueValue, toClass);
    }
}
