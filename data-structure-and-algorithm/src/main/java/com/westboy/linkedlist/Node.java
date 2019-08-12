package com.westboy.linkedlist;

/**
 * @author pengbo.wang
 * @date 2019/8/7
 * @since 1.0
 */
// @Data
// @NoArgsConstructor
public class Node<T> implements Comparable<Node<T>> {
    private T data;
    private Node<T> next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public int compareTo(Node<T> o) {
        return Integer.compare((Integer) this.getData(), (Integer) o.getData());
    }
}
