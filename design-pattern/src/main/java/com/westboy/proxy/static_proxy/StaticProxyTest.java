package com.westboy.proxy.static_proxy;

public class StaticProxyTest {
    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
        userDaoProxy.save();
    }
}
