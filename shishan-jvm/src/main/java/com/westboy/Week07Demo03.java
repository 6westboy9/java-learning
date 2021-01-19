package com.westboy;

/**
 * 模拟：存活对象无法放入 Survivor 区进入老年代
 *
 * <p>
 * cd /Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm/target/classes
 * <p>
 * java -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -Xloggc:../../../shishan-jvm-gc-log/week_07_demo_03.log com.westboy.Week07Demo03
 *
 * <p>
 * 初始堆大小:20MB
 * 新生代:10MB Eden:S0:S1=8MB:1MB:1MB
 * 老年代:10MB
 * <p>
 *
 * @author pengbo
 * @since 2021/1/19
 */
public class Week07Demo03 {
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];      // 6MB
        byte[] array2 = new byte[128 * 1024];
        array2 = null;
        byte[] array3 = new byte[2 * 1024 * 1024]; // Eden 区无法放得下，YGC 后存活对象超过 2MB > Survivor 大小，直接进入老年代

        // 0.087: [GC (Allocation Failure) 0.087: [ParNew: 6943K->301K(9216K), 0.0018637 secs] 6943K->2351K(19456K), 0.0019877 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]

        // [ParNew: 6943K->301K(9216K), 0.0018637 secs] 新生代可用大小 9MB，已使用 6943K，YGC 之后存活对象 301K
        // 在此分配 array3 时，Eden 区无法放得下，YGC 后存活对象超过 2MB > Survivor 大小，直接进入老年代

        // Heap
        //  par new generation   total 9216K, used 2432K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)                新生代可用 9MB，已使用 2432K
        //   eden space 8192K,  26% used [0x00000007bec00000, 0x00000007bee14930, 0x00000007bf400000)                                Eden 8MB，已使用 (2432-301)/8192=26%
        //   from space 1024K,  29% used [0x00000007bf500000, 0x00000007bf54b7a0, 0x00000007bf600000)                                Survivor From 1MB 301/1024=29%
        //   to   space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)                                Survivor From 1MB 0%
        //  concurrent mark-sweep generation total 10240K, used 2050K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)   老年代 10MB，已使用 2050K，存活的 array1 大约 2MB
        //  Metaspace       used 2515K, capacity 4486K, committed 4864K, reserved 1056768K
        //   class space    used 268K, capacity 386K, committed 512K, reserved 1048576K
    }
}
