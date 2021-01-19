package com.westboy;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 注意上下两个对象的最大对象进入老年代的阈值不一样
 *
 * -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200 -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:week_8_demo1.gc.log
 *
 *
 * 出现频繁 YGC 导致 FGC，Young 100MB Eden 80MB Survivor 10MB Old 100MB
 * -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200 -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=209715200 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:week_8_demo1.gc.log
 *
 * 优化改进1，Young 200MB Eden 100MB Survivor 50MB Old 100MB
 * -XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800 -XX:SurvivorRatio=2  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=209715200 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:week_8_demo1.gc.log
 * 优化改进2，Young 400MB Eden 200MB Survivor 100MB Old 200MB
 * -XX:NewSize=419430400 -XX:MaxNewSize=419430400 -XX:InitialHeapSize=629145600 -XX:MaxHeapSize=629145600 -XX:SurvivorRatio=2  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=209715200 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:week_8_demo1.gc.log
 *
 优化改进2，Young 600MB Eden 300MB Survivor 150MB Old 400MB
 * -XX:NewSize=629145600 -XX:MaxNewSize=629145600 -XX:InitialHeapSize=1048576000 -XX:MaxHeapSize=1048576000 -XX:SurvivorRatio=2  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=209715200 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:week_8_demo1.gc.log
 *
 */
public class Week08Demo01 {

    @SneakyThrows
    public static void main(String[] args) {
        TimeUnit.SECONDS.sleep(30);
        while (true) {
            loadData();
        }
    }

    @SneakyThrows
    private static void loadData() {
        // byte[] data = null;
        // // 每秒 50 * 100KB = 5MB，20 秒就会触发一次 YGC
        // for (int i = 0; i < 50; i++) {
        //     data = new byte[100 * 1024];
        // }
        // data = null;
        // Thread.sleep(1000);
        byte[] data =  null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;


        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        TimeUnit.SECONDS.sleep(1);
    }
}
