package com.westboy;

/**
 * 模拟：Young GC
 *
 *<p>
 * 初始堆大小:10MB
 * 新生代:5MB Eden:S0:S1=4MB:0.5MB:0.5MB
 * 老年代:5MB
 *<p>
 *
 * <p>
 * 建议不要再 IDEA 中直接运行，可能因为 IDEA 的原因（IDEA 自身也是个 Java 程序）导致结果不是那么准确
 * 推荐在 Terminal 直接手动执行命令：
 * <p>
 *
 * cd /Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm/target/classes
 * <p>
 * java -XX:NewSize=5242880 -XX:MaxNewSize=5242880 -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760
 * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:../../../shishan-jvm-gc-log/week_07_demo_01.log com.westboy.Week07Demo01
 */
public class Week07Demo01 {
    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        // 此时，已经占据 Eden 3MB 了，将 array1=null，在下次 Young GC 时会对 array1 进行回收

        // 1. Eden 空间是不足的
        // 2. 老年代空间大小 > 新的对象 2MB
        // 顺利触发 Young GC，查看 gc 关键日志
        // 0.073: [GC (Allocation Failure) 0.073: [ParNew: 3579K->305K(4608K), 0.0005590 secs] 3579K->305K(9728K), 0.0006504 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

        // [ParNew: 3579K->305K(4608K), 0.0005590 secs] 在 Young GC 之前新生代使用了 3579K，Young GC 之后存活对象为 305K，耗时：0.0005590 secs
        // 3579K->305K(9728K), 0.0006504 secs 总的可用堆内存大小为 9728K=4608K+5120K=4096K+512K+5120K，GC 前总的已使用堆内存大小为 3579K，GC 后总的已使用堆内存大小为 305K


        byte[] array2 = new byte[2 * 1024 * 1024];

        // Heap
        //  par new generation   total 4608K, used 2395K [0x00000007bf600000, 0x00000007bfb00000, 0x00000007bfb00000)               新生代总共：4608KB（总大小=4096+512），已使用：2395K
        //   eden space 4096K,  51% used [0x00000007bf600000, 0x00000007bf80a558, 0x00000007bfa00000)                               Eden：4096KB（总大小） × 51%          <=== 怎么计算的呢？(2395-305)/4096=51%
        //   from space 512K,  59% used [0x00000007bfa80000, 0x00000007bfacc790, 0x00000007bfb00000)                                Survivor From：305KB（总大小） × 59%  <=== 怎么计算的呢？存活对象 305/512=59%
        //   to   space 512K,   0% used [0x00000007bfa00000, 0x00000007bfa00000, 0x00000007bfa80000)                                Survivor From：512KB（总大小） × 0%
        //  concurrent mark-sweep generation total 5120K, used 0K [0x00000007bfb00000, 0x00000007c0000000, 0x00000007c0000000)      老年代：5120KB，已使用 0KB
        //  Metaspace       used 2515K, capacity 4486K, committed 4864K, reserved 1056768K                                          Metaspace 使用情况
        //   class space    used 268K, capacity 386K, committed 512K, reserved 1048576K
    }
}
