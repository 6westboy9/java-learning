package com.westboy;

/**
 * 模拟：动态年龄判断存活对象进入老年代
 *
 * <p>
 * cd /Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm/target/classes
 * <p>
 * java -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -Xloggc:../../../shishan-jvm-gc-log/week_07_demo_02.log com.westboy.Week07Demo02
 *
 *<p>
 * 初始堆大小:20MB
 * 新生代:10MB Eden:S0:S1=8MB:1MB:1MB
 * 老年代:10MB
 *<p>
 *
 * @author westboy
 * @since 2019/11/19
 */
public class Week07Demo02 {
	public static void main(String[] args) {
		byte[] array1 = new byte[7 * 1024 * 1024];
		array1 = null;
		byte[] array2 = new byte[300 * 1024];
		byte[] array3 = new byte[7 * 1024 * 1024]; // 第一次触发 Young GC
		array3 = null;
		byte[] array4 = new byte[2 * 1024 * 1024]; // 第二次触发 Young GC


		// 关键日志
		// 0.079: [GC (Allocation Failure) 0.079: [ParNew: 8139K->601K(9216K), 0.0006843 secs] 8139K->601K(19456K), 0.0007626 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
		// 0.081: [GC (Allocation Failure) 0.081: [ParNew: 7769K->0K(9216K), 0.0024799 secs] 7769K->592K(19456K), 0.0025230 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]

		// 日志分析
		// [ParNew: 8139K->601K(9216K), 0.0006843 secs] 新生代可用内存大小 = 8MB+1MB=9MB=9216K，存活对象 601K
		// [ParNew: 7769K->0K(9216K), 0.0024799 secs]   新生代可用内存大小 = 8MB+1MB=9MB=9216K，存活对象 0K

		// 堆内存
		// Heap
		//  par new generation   total 9216K, used 2130K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
		//   eden space 8192K,  26% used [0x00000007bec00000, 0x00000007bee14930, 0x00000007bf400000)							 	Eden 总大小：8MB，已使用 2130/8192=26%
		//   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)							 	Survivor From 总大小：1MB，已使用 0%
		//   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)							 	Survivor To 总大小：1MB，已使用 0%
		//  concurrent mark-sweep generation total 10240K, used 592K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)	老年代总大小：10MB，已使用 592K <= 第一次 YGC 之后存活对象为 613K，大于 Survivor 的 50%
		//                  																										第一次躲过 YGC 的存活对象，第二次 YGC 时，超过 Survivor 50%，第一批存活对象年龄为 2，则 >= 2 的存活对象直接进入老年代
		//                                    																						592K 就是通过动态年龄判断后进入老年代的存活对象
		//  Metaspace       used 2515K, capacity 4486K, committed 4864K, reserved 1056768K
		//   class space    used 268K, capacity 386K, committed 512K, reserved 1048576K
	}
}
