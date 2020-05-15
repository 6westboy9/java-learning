package com.westboy.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();

		List<Item> list = new ArrayList<>();

		list.add(new Item(1, 3));
		list.add(new Item(2, 2));
		list.add(new Item(1, 4));
		list.add(new Item(4, 5));

		for (Item item : list) {
			int i = item.getI();
			int j = item.getJ();
			map.compute(i, (k, v) -> {
				System.out.println(k + "\t" + map.getOrDefault(k, 0) + "\t" + v);
				v = map.getOrDefault(k, 0);
				return v + j;
			});
		}

		System.out.println(map);


	}

	static class Item {
		private int i;
		private int j;
		public Item(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public int getI() {
			return i;
		}

		public int getJ() {
			return j;
		}
	}

}
