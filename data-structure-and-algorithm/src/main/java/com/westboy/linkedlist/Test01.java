package com.westboy.linkedlist;


import java.util.HashMap;
import java.util.Map;

/**
 * @author pengbo.wang
 * @date 2019/8/7
 * @since 1.0
 */
public class Test01 {

    /**
     * 保存足迹信息
     */
    private static Map<Node, Integer> nodeMap = new HashMap<>();


    public static void main(String[] args) {
        Node<Integer> node11 = new Node<>(1);
        Node<Integer> node12 = new Node<>(2);
        Node<Integer> node13 = new Node<>(5);
        Node<Integer> node14 = new Node<>(6);

        // node11.setNext(node12);
        // node12.setNext(node13);
        // node13.setNext(node14);

        Node<Integer> node21 = new Node<>(3);
        Node<Integer> node22 = new Node<>(4);
        Node<Integer> node23 = new Node<>(6);
        Node<Integer> node24 = new Node<>(8);

        // node21.setNext(node22);
        // node22.setNext(node23);
        // node23.setNext(node24);

        Node node = merge(node11, node21);
        print(node);

        // node4.setNext(node1);
        // print(node1);
        // node1 = reverseV1(node1);
        // node1 = reverseV2(node1);
        // print(node1);
        // System.out.println(hasLoopV1(node11));

    }

    private static <T> Node<T> merge(Node<T> node11, Node<T> node21) {
        // 新建哨兵结点
        Node<T> head = new Node<>();
        // 临时结点
        Node<T> temp = head;
        while (node11 != null && node21 != null) {
            if (node11.compareTo(node21) <= 0) {
                temp.setNext(node11);
                temp = temp.getNext();
                node11 = node11.getNext();
            } else {
                temp.setNext(node21);
                temp = temp.getNext();
                node21 = node21.getNext();
            }
        }

        if (node11 != null) {
            temp.setNext(node11);
        }

        if (node21 != null) {
            temp.setNext(node21);
        }

        temp = head;
        head = head.getNext();
        // 刪除哨兵结点
        temp = null;
        return head;
    }

    /**
     * 打印单链表
     *
     * @param node 单链表头结点
     * @param <T>  单链表结点数据类型
     */
    private static <T> void print(Node<T> node) {
        while (node != null) {
            System.out.print(node.getData());
            node = node.getNext();
        }
        System.out.println();
    }

    /**
     * 单链表的反转方法一
     * <p>
     * 思路：重新分配一个单链表用于存储反转后的单链表。
     *
     * @param head 单链表头结点
     * @param <T>  单链表结点数据类型
     * @return 反转后的单链表头结点
     */
    private static <T> Node<T> reverseV1(Node<T> head) {
        // 用于存储新的反转后的单链表
        Node<T> tNode = null;
        // 临时结点，用于存储遍历过程中的单个结点
        Node<T> mNode;
        while (head != null) {
            mNode = new Node<>(head.getData());
            if (tNode != null) {
                mNode.setNext(tNode);
            }
            tNode = mNode;
            head = head.getNext();
        }
        return tNode;
    }

    /**
     * 原地单链表反转
     *
     * @param head 单链表头结点
     * @param <T>  单链表结点数据类型
     * @return 反转后的单链表头结点
     */
    private static <T> Node<T> reverseV2(Node<T> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node<T> preNode = null;
        Node<T> nextNode = null;
        while (head != null) {
            nextNode = head.getNext();

            head.setNext(preNode);
            preNode = head;
            head = nextNode;
        }
        return preNode;
    }

    /**
     * 链表中环的检测
     * <p>
     * 思路：分别用两个指针指向表头，两指针分别分为快指针，每次前移两个位置，慢指针，每次前移一个位置，
     * 若两指针相遇，则有环，若快指针指向null，则无环。
     * <p>
     * 原因：若有环，快指针先行进环，在环中绕圈，慢指针后入环，也在环中绕圈，由于快指针每次比慢指针多走一步，
     * 意味着两个指针在环中的位置每次都缩短一步，所以若有环，两个指针在环中必能相遇。
     *
     * @param node 链表头结点
     * @param <T>  链表结点数据类型
     * @return 是否为循环链表
     */
    private static <T> boolean hasLoopV1(Node<T> node) {
        if (node == null) {
            return false;
        }

        if (node.getNext() == null) {
            return false;
        }

        Node<T> a = node;
        Node<T> b = node;

        boolean ret = false;

        while (true) {
            a = a.getNext();
            if (a == null) {
                break;
            }
            b = b.getNext();
            if (b == null) {
                break;
            }

            b = b.getNext();
            if (b == null) {
                break;
            }

            if (a == b) {
                ret = true;
                break;
            }
        }
        return ret;
    }

}
