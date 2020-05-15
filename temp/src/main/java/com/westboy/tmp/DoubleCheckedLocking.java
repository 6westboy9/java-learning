package com.westboy.tmp;

/**
 * @author westboy
 * @since 2020/4/8
 */
public class DoubleCheckedLocking {                        // 1
	// private volatile static Instance instance;          // 2
	// public static Instance getInstance() {              // 3
	// 	if (instance == null) {                            // 4：第一次检查
	// 		synchronized (DoubleCheckedLocking.class) {    // 5：加锁
	// 			if (instance == null) {                    // 6：第二次检查
	// 				instance = new Instance();             // 7：问题根源出在这里
	// 			}                                          // 8
	// 		}                                              // 9
	// 	}                                                  // 10
	// 	return instance;                                   // 11
	// }
	//


	private static class InstanceHolder {
		public static Instance instance = new Instance();
	}

	public static Instance getInstance() {
		return InstanceHolder.instance;
	}
	static class Instance {}

}
