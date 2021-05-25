package com.westboy.java8.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("China");
        list.add("Japan");
        list.add("America");

        list.forEach(i -> {
            System.out.println(Thread.currentThread());
        });

    }
}
