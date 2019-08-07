package com.westboy.linkedlist;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pengbo.wang
 * @date 2019/8/7
 * @since 1.0
 */
@Data
@NoArgsConstructor
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}
