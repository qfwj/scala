package javatest.objectByteSize;

import java.lang.instrument.Instrumentation;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2019/10/28 16:58
 */
public class TestObjectSize {

    public static void main(String[] args) {
        // 通过pre-agent获取Instrumentation对象

        System.out.println("哈哈");
     /*   Instrumentation instr = AgentGetter.getInstrumentation();

        System.out.println(instr.getObjectSize(new Object()));*/


    }
}
