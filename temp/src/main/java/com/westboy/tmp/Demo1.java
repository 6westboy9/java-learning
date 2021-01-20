package com.westboy.tmp;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author westboy
 * @since 2019/11/19
 */
public class Demo1 {

	public static void main(String[] args) {
		// Map<Integer, String> map = new HashMap<>();
		// map.put(1, "中国1");
		// map.put(2, "中国2");
		// map.put(3, "中国3");
		// map.put(4, "中国4");
		// map.put(5, "中国5");
		//
		//
		// System.out.println(map.toString());
		//
		// map.remove(1);
		// System.out.println(map.toString());

		// List<Integer> list = new ArrayList<>();
		// list.add(1);
		// list.add(1);
		// list.add(1);
		// list.add(1);
		//
		// String res = CollUtil.join(list, ",");
		// System.out.println(res);

		long start = System.currentTimeMillis();
		test();
		System.out.println(System.currentTimeMillis() - start);


	}

	private static void test() {
		long threshold = 10;
		while (threshold > 0) {
			// 等待预授权以不同通知对于长租买断订单的处理成功
			// if (buyoutOrder.getAuthStatus() == AlipayFundAuthStatus.FREEZE_MANUAL_ASYNC_SUCCESS) {
			// 	break;
			// }

			try {
				Thread.sleep(1000);
				threshold--;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
