package com.westboy.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @author westboy
 * @since 2020/4/7
 */
public class TreeMapAndLinkedHashMapTest {
	public static void main(String[] args) {
		//HashMap
		System.out.println("------HashMap无序输出------");
		HashMap<String, String> hsMap = new HashMap<>();
		hsMap.put("3", "Value3");
		hsMap.put("1", "Value1");
		hsMap.put("2", "Value2");
		hsMap.put("b", "ValueB");
		hsMap.put("a", "ValueA");
		hsMap.forEach((k, v) -> System.out.println("Key: " + k + "--Value: " + v));

		//TreeMap
		System.out.println("------TreeMap按Key排序输出------");
		TreeMap<String, String> teMap = new TreeMap<>();
		teMap.put("3", "Value3");
		teMap.put("1", "Value1");
		teMap.put("2", "Value2");
		teMap.put("b", "ValueB");
		teMap.put("a", "ValueA");
		teMap.forEach((k, v) -> System.out.println("Key: " + k + "--Value: " + v));

		//LinkedHashMap
		System.out.println("--LinkedHashMap根据输入的顺序输出--");
		LinkedHashMap<String, String> lhsMap = new LinkedHashMap<>();
		lhsMap.put("3", "Value3");
		lhsMap.put("1", "Value1");
		lhsMap.put("2", "Value2");
		lhsMap.put("b", "ValueB");
		lhsMap.put("a", "ValueA");
		lhsMap.get("b");

		lhsMap.forEach((k, v) -> System.out.println("Key: " + k + "--Value: " + v));
	}
}
