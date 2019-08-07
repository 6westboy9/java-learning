package com.westboy.linkedlist;


/**
 * @author pengbo.wang
 * @date 2019/8/7
 * @since 1.0
 */
public class Test01 {
    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(1);
        Node<Integer> node3 = new Node<>(2);
        Node<Integer> node4 = new Node<>(4);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node1);

        // print(node1);
        // node1 = reverseV1(node1);
        // print(node1);

        System.out.println(hasCycle(node1));

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
     * @param node 单链表头结点
     * @param <T>  单链表结点数据类型
     * @return 反转后的单链表头结点
     */
    private static <T> Node<T> reverseV1(Node<T> node) {
        // 用于存储新的反转后的单链表
        Node<T> tNode = null;
        // 临时结点，用于存储遍历过程中的单个结点
        Node<T> mNode;
        while (node != null) {
            mNode = new Node<>(node.getData());
            if (tNode != null) {
                mNode.setNext(tNode);
            }
            tNode = mNode;
            node = node.getNext();
        }
        return tNode;
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
    private static <T> boolean hasCycle(Node<T> node) {
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
