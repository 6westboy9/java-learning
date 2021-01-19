package com.westboy;

/**
 * 模拟：大对象直接进入老年代
 *
 * <p>
 * cd /Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm/target/classes
 * <p>
 * java -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=5242880 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -Xloggc:../../../shishan-jvm-gc-log/week_07_demo_04.log com.westboy.Week07Demo04
 *
 * <p>
 * 初始堆大小:20MB
 * 新生代:10MB Eden:S0:S1=8MB:1MB:1MB
 * 老年代:10MB
 * 大对象阈值:5MB
 * <p>
 *
 * @author westboy
 * @since 2019/11/24
 */
public class Week07Demo04 {

	public static void main(String[] args) {
		byte[] array1 = new byte[6 * 1024 * 1024];

		// 没有发生 GC，直接进入老年代
		// Heap
		//  par new generation   total 9216K, used 835K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
		//   eden space 8192K,  10% used [0x00000007bec00000, 0x00000007becd0f08, 0x00000007bf400000)								835/8192=10% JVM 自身附带对象
		//   from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
		//   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
		//  concurrent mark-sweep generation total 10240K, used 6144K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)  直接进入老年代
		//  Metaspace       used 2515K, capacity 4486K, committed 4864K, reserved 1056768K
		//   class space    used 268K, capacity 386K, committed 512K, reserved 1048576K
	}
}
