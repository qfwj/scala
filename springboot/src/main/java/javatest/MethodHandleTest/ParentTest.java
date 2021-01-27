package javatest.MethodHandleTest;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/6/1 16:52
 */
public class ParentTest {
    class GrandFather {
        public void thinking() {
            System.out.println("i am grandfather.");
        }
    }

    class Father extends GrandFather {
        @Override
        public void thinking() {
            System.out.println("i am father.");
        }
    }

    class Son extends Father {
        @Override
        public void thinking() {
            MethodType methodType = MethodType.methodType(void.class);
            try {
                Constructor<MethodHandles.Lookup> constructor =
                        MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                constructor.setAccessible(true);
                MethodHandles.Lookup instance = constructor.newInstance(GrandFather.class, -1);
                MethodHandle methodHandle =
                        instance.findSpecial(
                                GrandFather.class, "thinking", methodType, GrandFather.class);
                methodHandle.invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ParentTest().new Son().thinking();
    }
}