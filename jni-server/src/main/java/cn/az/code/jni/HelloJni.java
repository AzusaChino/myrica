package cn.az.code.jni;

import cn.az.code.jni.support.JniDeploy;

import java.io.IOException;

/**
 * JNI Demo Application
 * 
 * @author az
 * @since 2022-06-27
 */
public class HelloJni {

    static {
        try {
            JniDeploy.deploy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // load the so library
        System.loadLibrary("myrica");
    }

    public static void main(String[] args) {
        HelloJni hj = new HelloJni();
        hj.native_say();
    }

    /**
     * Native Say Hello
     */
    private native void native_say();
}
