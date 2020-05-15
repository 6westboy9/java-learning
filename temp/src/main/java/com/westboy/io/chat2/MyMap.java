package com.westboy.io.chat2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author westboy
 * @since 2019/11/18
 */
public class MyMap<K, V> {

	public Map<K, V> map = new ConcurrentHashMap<>();

	public synchronized void removeByValue(V value) {
		map.forEach((k, v) -> {
			if (map.get(k) == value) {
				map.remove(k);
			}
		});
	}

	public synchronized Set<V> valueSet() {
		return new HashSet<>(map.values());
	}

	public synchronized K getKeyByValue(V value) {
		return map.entrySet().stream().filter(kvEntry -> {
			K k = kvEntry.getKey();
			return map.get(k) == value || map.get(k).equals(value);
		}).findFirst().map(Map.Entry::getKey).orElse(null);
	}

	public synchronized V put(K key, V value) {
		Set<V> values = new HashSet<>(map.values());
		boolean exists = values.stream().anyMatch(v -> v.equals(value) && v.hashCode() == value.hashCode());
		if (exists) {
			throw new RuntimeException("MyMap 实例中不允许有重复 value！");
		}
		return map.put(key, value);
	}

}
