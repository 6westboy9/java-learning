package com.westboy.proxy.static_proxy;

public class UserDaoProxy implements IUserDao {

    private IUserDao target;

    // 封装真实的实现类
    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开启事务");
        target.save();
        System.out.println("提交事务");
    }
}
