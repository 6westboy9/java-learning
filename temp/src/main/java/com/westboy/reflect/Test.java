package com.westboy.reflect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author pengbo
 * @since 2020/12/11
 */
public class Test {

    private String a = "1";
    private String b = "2";

    public static void main(String[] args) {
        // List<String> list = CollUtil.newArrayList("1");
        //
        // Object o = list;
        // if (o instanceof ArrayList) {
        //     System.out.println(CollUtil.isEmpty((ArrayList<String>)o));
        // }
        // System.out.println(o instanceof Collection);


        // Field[] fields = Test.class.getDeclaredFields();
        // Test test = new Test();
        // Object[] objects = ReflectUtil.getFieldsValue(test);
        //
        // for (Object o : objects) {
        //     System.out.println(o);
        // }


    }
}
