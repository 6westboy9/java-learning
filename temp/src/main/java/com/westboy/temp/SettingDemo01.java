package com.westboy.temp;

import cn.hutool.setting.Setting;

public class SettingDemo01 {
    public static void main(String[] args) {
        //读取classpath下的XXX.setting，不使用变量
        // Setting setting = new Setting("XXX.setting");

        //读取classpath下的config目录下的XXX.setting，不使用变量
        Setting setting = new Setting("config/example.properties");


        //读取绝对路径文件/home/looly/XXX.setting（没有就创建，关于touc请查阅FileUtil）
        //第二个参数为自定义的编码，请保持与Setting文件的编码一致
        //第三个参数为是否使用变量，如果为true，则配置文件中的每个key都以被之后的条目中的value引用形式为 ${key}
        // setting = new Setting(FileUtil.touc("/home/looly/XXX.setting"), CharsetUtil.CHARSET_UTF_8, true);

        //读取与SettingDemo.class文件同包下的XXX.setting
        // setting = new Setting("XXX.setting", SettingDemo.class,CharsetUtil.CHARSET_UTF_8, true);


        //获取key为name的值
        // setting.getStr("name");
        // //获取分组为group下key为name的值
        // setting.getByGroup("name", "group1");
        // //当获取的值为空（null或者空白字符时，包括多个空格），返回默认值
        // setting.getStr("name", "默认值");
        // //完整的带有key、分组和默认值的获得值得方法
        // setting.getStr("name", "group1", "默认值");
        //
        // //如果想获得其它类型的值，可以调用相应的getXXX方法，参数相似
        //
        // //有时候需要在key对应value不存在的时候（没有这项设置的时候）告知户，故有此方法打印一个debug日志
        // setting.getWithLog("name");
        // setting.getByGroupWithLog("name", "group1");

        //获取分组下所有配置键值对，组成新的Setting
        Setting demo = setting.getSetting("demo");
        System.out.println(demo.getStr("ds.setting.path"));
        System.out.println(demo.getStr("driver"));
        
    }
}
