package com.westboy.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;

import java.util.List;

/**
 * @author pengbo
 * @since 2020/9/17
 */
public class ExcelSupport {


    public static void main(String[] args) {
        // List<Student> students = createStudents();
        // List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        // List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        // List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        // List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        // List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
        //
        // List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);
        //
        // //通过工具类创建writer
        // ExcelWriter writer = ExcelUtil.getWriter("./writeTest.xlsx");
        // //通过构造方法创建writer
        // //ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");
        //
        // //跳过当前行，既第一行，非必须，在此演示用
        // writer.passCurrentRow();
        //
        // //合并单元格后的标题行，使用默认标题样式
        // writer.merge(row1.size() - 1, "测试标题");
        // //一次性写出内容，强制输出标题
        // writer.write(rows, true);
        // //关闭writer，释放内存
        // writer.close();

        test2();
    }

    private static void test2() {
        String fileName = "./" + "write" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Student.class).sheet("模板").doWrite(createStudents());
    }


    private static List<Student> createStudents() {
        return CollUtil.newArrayList(
                new Student("小王", 18),
                new Student("小张", 20),
                new Student("小李", 16),
                new Student("小孙", 17),
                new Student("小宋", 19),
                new Student("小马", 16),
                new Student("小何", 18));
    }

}
