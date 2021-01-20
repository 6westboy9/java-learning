package com.westboy;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 模拟频繁 YGC 并监控 JVM 参数
 * <p>
 * cd /Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm/target/classes
 * <p>
 * <p>
 * java -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200
 * -XX:SurvivorRatio=8  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=209715200 -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -Xloggc:../../../shishan-jvm-gc-log/week_08_demo_02_before.log com.westboy.Week08Demo02
 *
 *
 * <p>
 * 堆内存总大小:200MB
 * 新生代:100MB
 * Eden:S0:S1=80MB:10MB:10MB
 * 老年代:100MB
 * 大对象阈值:20MB
 * <p>
 *
 * <p>
 * 查看 GC 情况：jstat -gc [PID] 1000 1000
 * 案例每秒新增 80MB 左右，触发每秒 1 次 YGC，每次 YGC 后存活下来 20~30对象
 *
 * <p>
 * 调优后：
 * <p>
 * java -XX:NewSize=209715200 -XX:MaxNewSize=209715200 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800
 * -XX:SurvivorRatio=2  -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=209715200 -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -Xloggc:../../../shishan-jvm-gc-log/week_08_demo_02_after.log com.westboy.Week08Demo02
 *
 * <p>
 * 调优（调大 Survivor 区大小）后：
 * 堆内存总大小:300MB
 * 新生代:200MB
 * Eden:S0:S1=100MB:50MB:50MB
 * 老年代:100MB
 * 大对象阈值:20MB
 * <p>
 */
public class Week08Demo02 {

    @SneakyThrows
    public static void main(String[] args) {
        TimeUnit.SECONDS.sleep(30);
        while (true) {
            loadData();
        }
    }

    @SneakyThrows
    private static void loadData() {
        // 对象进入老年代时机
        // 1. [Eden 不足] & [新生代所有对象大小 > 老年代可用内存大小] & [历次 YGC 存活对象平均大小 > 老年代可用内存大小] 提前触发一次 FGC   （YGC之前触发一次FGC）
        //
        //    [Eden 不足] & [新生代所有对象大小 < 老年代可用内存大小] | [历次 YGC 存活对象平均大小 < 老年代可用内存大小] 就会触发一次 YGC
        // 2. 接着 YGC 中 [1. 存活超过15次 | 2. 动态年龄判断] ===> 进入老年代
        // 3. 在 YGC 后，存活对象太多 > Survivor ===> 进入老年代 ===> 如果老年代放不下 ===> 触发 FGC                                   （YGC之后触发一次FGC）
        // 4. 大对象直接进入老年代

        // 所以具体分析 FGC 的原因一般从两个角度去思考
        // 1. 老年代分配太小 ------------------------------------- 优化思路:扩大老年代大小
        // 2. 老年代对象增长太快
        //    2.1. YGC 后存活对象太多，Survivor 太小 -------------- 优化思路:调整新生代及其内部 Eden 与 Survivor 的大小，防止对象频繁进入老年代
        //    2.2. 不停有大对象直接进入老年代 ---------------------- 优化思路：定位问题对象代码（一般都是代码引起）

        // 优化前的内存分配情况
        // 堆内存总大小:200MB
        // 新生代:100MB
        // Eden:S0:S1=80MB:10MB:10MB
        // 老年代:10MB
        // 大对象阈值:20MB

        byte[] data =  null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null; // 上述 for 循环结束之后，会在 Eden 占用 40MB

        // 第二次进来时，Eden 已经有 80MB，上述第 2 次循环结束后 Eden 占满，第 3 次循环就会导致 YGC（1s 后进行 1 次 YGC），存活对象 10MB，Survivor 放不下，进入老年代

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];
        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];
        TimeUnit.SECONDS.sleep(1); // 1 秒钟 Eden 中含有 40MB + 10MB + 10MB + 10MB + 10MB = 80MB
    }
}
