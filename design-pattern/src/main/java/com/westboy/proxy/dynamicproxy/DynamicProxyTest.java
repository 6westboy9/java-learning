package com.westboy.proxy.dynamicproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class DynamicProxyTest {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>> 使用 JDK 的动态代理");
        IUserDao userDao = new UserDaoImpl();
        System.out.println(userDao.getClass());
        IUserDao proxyInstance1 = (IUserDao) new JdkProxyFactory(userDao).getProxyInstance();
        System.out.println(proxyInstance1.getClass());
        proxyInstance1.save();


        // 查看 JDK 生成的动态代理类源码
        // byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IUserDao.class});
        // String path = "./$Proxy0.class";
        // try (FileOutputStream fos = new FileOutputStream(path)) {
        //     fos.write(classFile);
        //     fos.flush();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        System.out.println(">>>>>>>>>>> 使用 CGLIB 的动态代理");
        UserDaoImpl2 userDaoImpl2 = new UserDaoImpl2();
        System.out.println(userDaoImpl2.getClass());
        UserDaoImpl2 proxyInstance2 = (UserDaoImpl2) new CglibProxyFactory(userDaoImpl2).getProxyInstance();
        System.out.println(proxyInstance2.getClass());
        proxyInstance2.save();
    }
}
