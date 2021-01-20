package com.westboy;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 模拟频繁 YGC 并监控 JVM 参数
 *
 * <p>
 * cd /Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm/target/classes
 * <p>
 * <p>
 * java -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
 * -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -Xloggc:../../../shishan-jvm-gc-log/week_08_demo_01.log com.westboy.Week08Demo01
 *
 * <p>
 * 堆内存总大小:200MB
 * 新生代:100MB
 * Eden:S0:S1=80MB:10MB:10MB
 * 老年代:10MB
 * 大对象阈值:3MB
 *
 * <p>
 * 查看 GC 情况：jstat -gc [PID] 1000 1000 基本是每秒 5 MB 的增长，基本 16、17 次左右就会导致 Eden 占满，进而导致 YGC
 * 每次 YGC 之后存活对象大概也就 500KB 左右，轻松放入 10MB 的 Survivor 中
 * <p>
 *
 * <p>
 * 这个说明系统运行良好，YGC 都会导致对象进入老年代，几乎不需要优化！
 * <p>
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
        byte[] data = null;
        // 每秒 50×100KB=5MB，80MB÷5MB=16 秒就会触发一次 YGC
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;
        Thread.sleep(1000); // 模拟上面的一切都是发生在 1 秒内
    }

    // 查看部分 YGC 日志
    // 45.185: [GC (Allocation Failure) 45.186: [ParNew: 81865K->432K(92160K), 0.0009082 secs] 81865K->432K(194560K), 0.0013505 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    // 62.243: [GC (Allocation Failure) 62.243: [ParNew: 82328K->430K(92160K), 0.0016703 secs] 82328K->430K(194560K), 0.0017532 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
    // 78.292: [GC (Allocation Failure) 78.293: [ParNew: 82350K->474K(92160K), 0.0007431 secs] 82350K->474K(194560K), 0.0008156 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    // 94.341: [GC (Allocation Failure) 94.341: [ParNew: 82394K->504K(92160K), 0.0005795 secs] 82394K->504K(194560K), 0.0006444 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    // 110.391: [GC (Allocation Failure) 110.391: [ParNew: 82424K->476K(92160K), 0.0006041 secs] 82424K->476K(194560K), 0.0006740 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
}
