package com.westboy.proxy.staticproxy;

public class StaticProxyTest {
    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
        userDaoProxy.save();
    }
}
