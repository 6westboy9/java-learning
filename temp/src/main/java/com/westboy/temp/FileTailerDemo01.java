package com.westboy.temp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.Tailer;

public class FileTailerDemo01 {
    public static void main(String[] args) {
        Tailer tailer = new Tailer(FileUtil.file("/Users/westboy/WorkSpace/personal/java-learning/temp_log/test.log"), Tailer.CONSOLE_HANDLER);
        tailer.start();

    }
}
