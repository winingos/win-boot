package com.win.aop.toy;

import java.lang.instrument.Instrumentation;

/**
 * Created by Administrator on 17/12/9.
 */
public class PrefMonAgent {

    private static Instrumentation inst=null;
    /**
     * this method is called before the application's main-method
     * when this agent is specified to the Java VM.
     * @param angetArgs
     * @param _inst
     */
    public static void premain(String  angetArgs, Instrumentation _inst) {
        System.out.println("PrefMonAgent.premain was called ");
        inst=_inst;
        //set up the class-file transformer
        PerfMonXformer trans = new PerfMonXformer();
        System.out.println(" Adding a PerfMonXformer instance to the JVM.");
        inst.addTransformer(trans);
    }


}
