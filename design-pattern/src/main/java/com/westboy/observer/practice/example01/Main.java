package com.westboy.observer.practice.example01;

/**
 * 主方法
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        Rate rate = new RMBRate();
        Company importCompany = new ImportCompany();
        Company exportCompany = new ExportCompany();
        rate.add(importCompany);
        rate.add(exportCompany);
        rate.change(10);
        System.out.println();
        rate.change(-9);
    }
}
