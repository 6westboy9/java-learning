package com.westboy.file;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * @author pengbo
 * @since 2020/10/12
 */
public class FileTest01 {
    public static void main(String[] args) {
        File file = FileUtil.newFile("test.txt");
        if (file.delete()) {
            file = FileUtil.newFile("test.txt");
        }
        FileUtil.appendUtf8Lines(CollUtil.newArrayList("test1"), file);
        FileUtil.appendUtf8Lines(CollUtil.newArrayList("test2"), file);
        FileUtil.appendUtf8Lines(CollUtil.newArrayList("test3"), file);
    }
}
