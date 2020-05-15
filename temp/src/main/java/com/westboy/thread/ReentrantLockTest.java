package com.westboy.thread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author westboy
 * @since 2020/4/10
 */
public class ReentrantLockTest {

	private static int threads = 50;
	private static int a = 0;
	private static CountDownLatch countDownLatch = new CountDownLatch(threads);
	private static ReentrantLock lock = new ReentrantLock();

	private static List<Integer> list1 = new ArrayList<>();
	private static List<Integer> list2 = new ArrayList<>();


	@SneakyThrows
	public static void main(String[] args) {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("").build();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads, threadFactory);
		for (int i = 0; i < threads; i++) {
			executor.execute(ReentrantLockTest::add);
			countDownLatch.countDown();
		}

		TimeUnit.SECONDS.sleep(1);

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("活跃线程数", executor.getActiveCount());
		map.put("核心线程数", executor.getCorePoolSize());
		map.put("线程池队列大小", executor.getQueue().size());
		map.put("任务数量", executor.getTaskCount());
		System.out.println(JSONUtil.toJsonStr(map));
		System.out.println(list1);
		System.out.println(list2);
	}

	@SneakyThrows
	public static void add() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("线程名称", Thread.currentThread().getName());
		map.put("CountDownLatch", countDownLatch.getCount());
		System.out.println(JSONUtil.toJsonStr(map));
		list1.add(Integer.valueOf(Thread.currentThread().getName()));
		countDownLatch.await();
		lock.lock();
		try {

			int p = a;
			a++;


			map.clear();
			map.put("线程名称", Thread.currentThread().getName());
			map.put("值变化", p + " -> " + a);
			map.put("QueueLength", lock.getQueueLength());
			System.out.println(JSONUtil.toJsonStr(map));
			list2.add(Integer.valueOf(Thread.currentThread().getName()));
		} finally {
			lock.unlock();
		}
	}


}
