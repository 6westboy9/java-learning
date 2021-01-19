package com.westboy;

/**
 * 模拟：年轻代存活的对象太多并且老年代没有足够空间时，触发 Full GC 的场景
 *
 * <p>
 * cd /Users/westboy/IdeaProjects/personal/java-learning/shishan-jvm/target/classes
 * <p>
 * java -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 * -Xloggc:../../../shishan-jvm-gc-log/week_07_demo_05.log com.westboy.Week07Demo05
 *
 * <p>
 * 初始堆大小:20MB
 * 新生代:10MB Eden:S0:S1=8MB:1MB:1MB
 * 老年代:10MB
 * 大对象阈值:3MB
 * <p>
 *
 *
 */
public class Week07Demo05{
	public static void main(String[] args) {
		byte[] array1 = new byte[6 * 1024 * 1024]; // ① 大对象直接进入老年代
		array1 = null;

		byte[] array2 = new byte[2 * 1024 * 1024];
		byte[] array3 = new byte[2 * 1024 * 1024];
		byte[] array4 = new byte[2 * 1024 * 1024];
		byte[] array5 = new byte[128 * 1024];

		byte[] array6 = new byte[2 * 1024 * 1024]; // ② 此时触发 YGC，但是存活对象太多，且老年代没有足够空间

		// 0.077: [GC (Allocation Failure) 0.077: [ParNew (promotion failed): 6943K->7377K(9216K), 0.0020572 secs]0.079: [CMS: 8194K->6544K(10240K), 0.0023151 secs] 13087K->6544K(19456K), [Metaspace: 2508K->2508K(1056768K)], 0.0044813 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]

		// [ParNew (promotion failed): 6943K->7377K(9216K), 0.0020572 secs]  新生代可用大小：9216K，已使用：6943K，YGC 之后已使用大小为：7377K，说明 YGC 一个都回收不掉
		// 此时一定会将这些对象放入老年代，但是此时老年代已经有了一个 6MB 大小的数组，无法放下 YGC 后存活对象，进而触发 Full GC

		// [CMS: 8194K->6544K(10240K), 0.0023151 secs] 13087K->6544K(19456K), [Metaspace: 2508K->2508K(1056768K)], 0.0044813 secs]
		// 老年代总大小：10MB，FGC 前占用 8194K，FGC 后占用 6544K
		// 为啥 FGC 前是 8MB 左右呢？因为在 YGC 后，有 2MB 的存活对象大于 Survivor，直接进入老年代，此时老年代已使用 8194K
		// 但是还有 2MB + 2MB + 128KB = 4MB 左右的存活对象，此时老年代内存不足以存放，就触发了 FGC，将之前的 array1=6MB 回收，此时老年代中占用的有 array2 + array3 + array4 + array5 大概 6MB 多存活对象
		// 最后将 array6 的 2MB 对象放入 Eden

		// Heap
		//  par new generation   total 9216K, used 2130K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)              	新生代可用总代小：9MB，已使用 array6 2130K
		//   eden space 8192K,  26% used [0x00000007bec00000, 0x00000007bee14930, 0x00000007bf400000)								Eden 总大小 8MB，已使用 2130/8192=26%
		//   from space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
		//   to   space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
		//  concurrent mark-sweep generation total 10240K, used 6544K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)	新生代可用总代小：10MB，老年代 FGC 之后，占用 6544K
		//  Metaspace       used 2515K, capacity 4486K, committed 4864K, reserved 1056768K
		//   class space    used 268K, capacity 386K, committed 512K, reserved 1048576K
	}
}
