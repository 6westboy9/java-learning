package com.westboy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 手动模拟 Metaspace OOM
 *
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
 * <p>
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * -XX:+PrintGCDetails -Xloggc:/Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm-gc-log/week_11_demo_01.log
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm-gc-log/
 *
 * @author pengbo
 * @since 2021/1/23
 */
public class Week11Demo01 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Car.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if ("run".equalsIgnoreCase(method.getName())) {
                    System.out.println("启动汽车之前，先进行自动的安全检查...");
                }
                return methodProxy.invokeSuper(o, objects);
            }
        });


        while (true) {
            Car car = (Car) enhancer.create();
            car.run();
        }
    }

    static class Car {
        public void run() {
            System.out.println("汽车启动，开始行使...");
        }
    }
}
