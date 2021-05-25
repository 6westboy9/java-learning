package com.westboy.temp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;

import java.io.IOException;

public class FtpDemo01 {

    public static void main(String[] args) throws IOException {
        //匿名登录（无需帐号密码的FTP服务器）
        // root@lan_Twapp1103
        Ftp ftp = new Ftp("192.168.199.180", 21, "root", "lan_Twapp1103");
        //进入远程目录
        ftp.cd("/opt/upload");

        String localFilepath = "/Users/westboy/WorkSpace/personal/java-learning/temp/src/main/java/com/westboy/FtpDemo01.java";
        // 上传本地文件
        ftp.upload("/opt/upload", FileUtil.file(localFilepath));
        // 下载远程文件
        // ftp.download("/opt/upload", "test.jpg", FileUtil.file("e:/test2.jpg"));
        // 关闭连接
        ftp.close();
    }

}
