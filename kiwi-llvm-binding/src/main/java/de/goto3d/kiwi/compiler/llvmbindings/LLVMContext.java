package de.goto3d.kiwi.compiler.llvmbindings;

import com.wapmx.nativeutils.jniloader.NativeLoader;
import de.goto3d.kiwi.compiler.llvmbindings.jni.LLVMCore;
import de.goto3d.kiwi.compiler.llvmbindings.jni.SWIGTYPE_p_LLVMContextRef;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.01.12
 * Time: 21:07
 */
public class LLVMContext {

    static {
        try {
            System.setProperty("java.library.tmpdir", System.getProperty("java.io.tmpdir"));
            NativeLoader.loadLibrary("LLVMCore_jni_bindings");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private final static LLVMContext GLOBAL_CONTEXT = new LLVMContext(LLVMCore.LLVMGetGlobalContext());

    protected SWIGTYPE_p_LLVMContextRef swigtype_p_llvmOpaqueContext;

    private LLVMContext(SWIGTYPE_p_LLVMContextRef swigtype_p_llvmOpaqueContext) {
        this.swigtype_p_llvmOpaqueContext = swigtype_p_llvmOpaqueContext;
    }

    public static LLVMContext getGlobalContext() {
        return GLOBAL_CONTEXT;
    }
}
