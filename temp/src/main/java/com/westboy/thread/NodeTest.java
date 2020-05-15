package com.westboy.thread;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author westboy
 * @since 2020/4/10
 */
public class NodeTest {

	public static void main(String[] args) {

		Node node5 = new Node(5);
		Node node4 = new Node(4);
		Node node3 = new Node(3);
		Node node2 = new Node(2);
		Node node1 = new Node(1);


		node1.setPredAndNext(null, node2);
		node2.setPredAndNext(node1, node3);
		node3.setPredAndNext(node2, node4);
		node4.setPredAndNext(node3, node5);
		node5.setPredAndNext(node4, null);

		Node.print(node1);


		// 当前节点
		Node node = node5;
		// 当前节点的前驱节点
		Node pred = node.prev;

		while (pred.num > 1) {
			// pred = pred.prev;
			// node.prev = pred;
			node.prev = pred = pred.prev;
		}


		Node.print(node1);


	}


	@Data
	@RequiredArgsConstructor
	static class Node {
		@NonNull Integer num;
		Node prev;
		Node next;

		void setPredAndNext(Node pred, Node next) {
			this.prev = pred;
			this.next = next;
		}

		static void print(Node node) {
			List<Integer> list = new ArrayList<>();
			while (ObjectUtil.isNotNull(node)) {
				list.add(node.getNum());
				node = node.next;
			}
			System.out.println(list);
		}
	}
}
