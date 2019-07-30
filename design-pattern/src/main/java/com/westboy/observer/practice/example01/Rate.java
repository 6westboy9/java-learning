package com.westboy.observer.practice.example01;

import java.util.ArrayList;
import java.util.List;

/**
 * 汇率
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public abstract class Rate {

    /**
     * 公司集合
     */
    protected List<Company> companies = new ArrayList<>();

    /**
     * 添加公司
     *
     * @param company 待添加公司
     */
    public void add(Company company) {
        companies.add(company);
    }

    /**
     * 删除公司
     *
     * @param company 待删除公司
     */
    public void remove(Company company) {
        companies.remove(company);
    }

    /**
     * 汇率变化
     *
     * @param number 汇率变化值
     */
    protected abstract void change(int number);
}
