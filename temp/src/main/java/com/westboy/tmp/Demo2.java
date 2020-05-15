package com.westboy.tmp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
// import com.sun.tools.javac.util.Assert;

import java.util.*;

/**
 * @author westboy
 * @since 2019/12/24
 */
public class Demo2 {

	// List<>
	public static void main(String[] args) {

		List<Period> periods = periods();
		System.out.print("档期: \t\t\t");
		System.out.println(periods);

		System.out.print("锁定时间段: \t\t");
		List<Period> locks = locks();
		System.out.println(locks);

		periods.sort((p1, p2) -> (int) (p1.days - p2.days));
		// System.out.print("档期: ");
		// System.out.println(periods);

		List<Integer> list = CollUtil.newArrayList(7, 15, 20);
		Map<Integer, List<Period>> map1 = CollUtil.newHashMap();
		for (Integer i : list) {
			for (Period period : periods) {
				if (period.getDays() >= i) {
					List<Period> ps = map1.getOrDefault(i, CollUtil.newArrayList());
					ps.add(period);
					map1.put(i, ps);
				}
			}
		}


		TreeMap<Integer, List<Period>> treeMap1 = CollUtil.sort(map1, Comparator.comparingInt(k -> k));

		System.out.println();
		treeMap1.forEach((k, v) -> System.out.println("快捷" + k + "天时间段: \t" + v));


		Period lock = locks.get(0);
		Map<Integer, Period> map2 = CollUtil.newHashMap();
		treeMap1.forEach((days, periodList) -> {
			for (Period period : periodList) {
				Period p = check(period, lock, days);
				if (ObjectUtil.isNotNull(p)) {
					map2.put(days, p);
					break;
				}
			}
		});

		System.out.println();
		TreeMap<Integer, Period> treeMap2 = CollUtil.sort(map2, Comparator.comparingInt(k -> k));
		treeMap2.forEach((k, v) -> System.out.println("快捷" + k + "天时间段: \t" + v));

	}

	public boolean between(long begin, long end, long target) {
		return begin <= target && target <= end;
	}

	public static Period check(Period period, Period lock, int days) {
		long begin = period.getBegin();
		long end = period.getEnd();
		long lockBegin = lock.getBegin();
		long lockEnd = lock.getEnd();

		// Assert.check(begin <= end);
		// Assert.check(lockBegin <= lockEnd);

		long daysTime = (days - 1) * ONE_DAY;

		// 档期在锁定时间段之前
		if (end < lockBegin) {
			period.setEnd(begin + daysTime);
			return period;
		}

		// 档期在锁定时间段之后
		if (begin > lockEnd) {
			period.setEnd(begin + daysTime);
			return period;
		}

		// 档期包含锁定时间段
		if (begin < lockBegin && end > lockEnd) {
			// 在前面
			if (lockBegin - begin >= daysTime) {
				period.setEnd(lockBegin - ONE_DAY);
				return period;
			}
			// 横跨
			if ((lockEnd - lockBegin) <= daysTime) {
				period.setEnd(begin + daysTime);
				return period;
			}
			// 在后面
			if (end - lockEnd >= daysTime) {
				period.setBegin(lockEnd + ONE_DAY);
				return period;
			}
			return null;
		}

		// 锁定时间段包含档期
		if (begin >= lockBegin && end <= lockEnd) {
			return null;
		}

		// 档期与锁定时间段前半部分相交
		if (begin <= lockBegin && end <= lockEnd) {
			if ((lockBegin - ONE_DAY - begin) >= daysTime) {
				period.setEnd(lockBegin - ONE_DAY);
				return period;
			}
			return null;
		}

		// 档期与锁定时间段后半部分相交
		if (begin >= lockBegin && begin <= lockEnd) {
			if (end - (lockEnd + ONE_DAY) >= daysTime) {
				period.setBegin(lockEnd + ONE_DAY);
				return period;
			}
			return null;
		}
		return null;
	}

	public static List<Period> locks() {
		List<Period> periods = new ArrayList<>();
		// [2020-01-22, 2019-01-31]
		// periods.add(new Period(1579622400000L, 1580486399000L));

		// [2019-12-18, 2019-01-31]
		// periods.add(new Period(1576627200000L, 1580486399000L));

		// [2019-12-18, 2019-12-28]
		// periods.add(new Period(1576627200000L, 1577404800000L));

		// // [2019-12-10, 2019-12-17]
		// periods.add(new Period(1575936000000L, 1576540800000L));
		//
		// // [2019-12-11, 2019-12-17]
		// periods.add(new Period(1576022400000L, 1576540800000L));


		// [2019-12-11, 2019-12-16]
		periods.add(new Period(1576022400000L, 1576454400000L));
		return periods;
	}


	public static List<Period> periods() {
		List<Period> periods = new ArrayList<>();
		// [2019-12-10, 2019-12-18, 8]
		periods.add(new Period(1575936000000L, 1576627200000L));
		// [2019-12-28, 2020-01-13, 16]
		periods.add(new Period(1577462400000L, 1578873600000L));
		// [2020-01-18, 2020-01-22, 4]
		periods.add(new Period(1579305600000L, 1579651200000L));
		// [2020-02-01, 2020-03-27, 55]
		periods.add(new Period(1580515200000L, 1585267200000L));
		return periods;
	}

	static Integer ONE_DAY = 24 * 3600 * 1000;

	static class Period {
		private long begin;
		private long end;
		private long days;

		public Period(long begin, long end) {
			this.begin = begin;
			this.end = end;
			this.days = (end - begin) / ONE_DAY + 1;
		}

		public long getBegin() {
			return begin;
		}

		public void setBegin(long begin) {
			this.begin = begin;
			this.days = (end - begin) / ONE_DAY + 1;
		}

		public long getEnd() {
			return end;
		}

		public void setEnd(long end) {
			this.end = end;
			this.days = (end - begin) / ONE_DAY + 1;
		}

		public long getDays() {
			return days;
		}

		public void setDays(long days) {
			this.days = days;
		}

		public boolean include(long target) {
			return target >= begin && target <= end;
		}

		@Override
		public String toString() {
			return "[" + DateUtil.date(begin).toDateStr() + ", " + DateUtil.date(end).toDateStr() + ", " + days + "]";
		}
	}
}

