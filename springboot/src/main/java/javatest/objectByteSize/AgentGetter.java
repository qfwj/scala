package javatest.objectByteSize;

import java.lang.instrument.Instrumentation;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/28 17:28
 */
public class AgentGetter {

    public static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("asdadasd");
        instrumentation = inst;
    }

    public static Instrumentation getInstrumentation() {
        return instrumentation;
    }

}