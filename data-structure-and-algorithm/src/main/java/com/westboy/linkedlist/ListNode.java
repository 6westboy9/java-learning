package com.westboy.linkedlist;

/**
 * @author pengbo.wang
 * @date 2019/8/11
 * @since 1.0
 */
public class ListNode {
    int val;
    ListNode next;

    private ListNode(int x) {
        val = x;
    }


    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(2);
        ListNode nodeA3 = new ListNode(5);
        ListNode nodeA4 = new ListNode(6);
        ListNode nodeA5 = new ListNode(8);
        ListNode nodeA6 = new ListNode(9);


        // nodeA1.next = nodeA2;
        // nodeA2.next = nodeA3;
        // nodeA3.next = nodeA4;
        // nodeA4.next = nodeA5;
        // nodeA5.next = nodeA6;

        ListNode nodeB1 = new ListNode(3);
        ListNode nodeB2 = new ListNode(4);
        ListNode nodeB3 = new ListNode(7);

        // nodeB1.next = nodeB2;
        // nodeB2.next = nodeB3;

        //
        // ListNode node = mergeTwoLists(nodeA1, nodeB1);
        //
        // while (node != null) {
        //     System.out.print(node.val + " ");
        //     node = node.next;
        // }

        ListNode node = removeNthFromEnd(nodeA1, 1);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }


    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        // 移动 first 指针 n 次
        ListNode first = head;
        while (n-- != 0) {
            first = first.next;
        }

        // 移动完毕，进行判断
        if (first == null) {
            // 删除的是头结点
            return head.next;
        }

        ListNode sec = head;
        while (first.next != null) {
            first = first.next;
            sec = sec.next;
        }

        sec.next = sec.next.next;
        return head;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode temp = null;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                if (temp == null) {
                    head = l1;
                    temp = head;
                } else {
                    temp.next = l1;
                    temp = temp.next;
                }
                l1 = l1.next;
            } else {
                if (temp == null) {
                    head = l2;
                    temp = head;
                } else {
                    temp.next = l2;
                    temp = temp.next;
                }
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            if (temp == null) {
                head = l2;
            } else {
                temp.next = l2;
            }
        }

        if (l2 == null) {
            if (temp == null) {
                head = l1;
            } else {
                temp.next = l1;
            }
        }

        return head;
    }
}
