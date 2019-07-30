package com.westboy.observer.practice.example01;

/**
 * 人民币汇率
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class RMBRate extends Rate {
    @Override
    protected void change(int number) {
        companies.forEach(company -> company.response(number));
    }
}
