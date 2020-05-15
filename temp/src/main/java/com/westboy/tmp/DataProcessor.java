package com.westboy.tmp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author westboy
 * @since 2019/11/7
 */
public class DataProcessor {

	public static void main(String[] args) throws Exception {
		// processConvert("prod-data-from.txt", "prod-data-to.txt");
		compile("original-data.txt", "test-data-to.txt", "prod-data-to.txt", "target-data.txt");

	}

	public static void compile(String original, String test, String prod, String target) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./data/" + target)));

		List<String> originalAllOrderIds = readFile2List(original);
		List<String> testAllOrderIds = readFile2List(test);
		List<String> prodAllOrderIds = readFile2List(prod);

		originalAllOrderIds.forEach(orderId -> {
			if (testAllOrderIds.contains(orderId)) {
				orderId = orderId + ":test";
			}

			if (prodAllOrderIds.contains(orderId)) {
				orderId = orderId + ":prod";
			}

			// System.out.println(orderId);

			try {
				bw.write("order" + orderId);
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		bw.close();

	}

	public static List<String> readFile2List(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("./data/" + fileName)));
		String lineStr;
		int count = 0;
		List<String> orderIds = new ArrayList<>();
		while ((lineStr = br.readLine()) != null) {
			orderIds.add(lineStr);
			count++;
		}

		System.out.println(count);
		return orderIds;
	}


	/**
	 * 处理复制出来的数据，去掉行号
	 */
	public static void processConvert(String from, String to) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("./data/" + from)));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./data/" + to)));

		String lineStr;
		int count = 0;
		int result = 0;
		while ((lineStr = br.readLine()) != null) {
			count++;
			// 为奇数行跳过
			if (count % 2 != 0) {
				continue;
			}
			System.out.println(lineStr);
			bw.write(lineStr);
			bw.newLine();
			result++;
		}

		System.out.println(result);

		br.close();
		bw.close();
	}
}
