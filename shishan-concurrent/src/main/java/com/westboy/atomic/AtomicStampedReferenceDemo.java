package com.westboy.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {

        String str1 = "aaa";
        String str2 = "bbb";
        AtomicStampedReference<String> reference = new AtomicStampedReference<>(str1, 1);
        System.out.println("reference.getStamp() = " + reference.getStamp());
        reference.compareAndSet(str1, str2, reference.getStamp(), reference.getStamp() + 1);
        System.out.println("reference.getReference() = " + reference.getReference());

        System.out.println("----------------------");

        boolean b = reference.attemptStamp(str2, reference.getStamp() + 1);
        System.out.println("b: " + b);
        System.out.println("reference.getStamp() = " + reference.getStamp());

        System.out.println("----------------------");

        // 当 expectedStamp=3 时才会设置成功
        boolean c = reference.compareAndSet(str2, "ccc", 4, reference.getStamp() + 1);
        System.out.println("reference.getReference() = " + reference.getReference());
        System.out.println("c = " + c);
    }
}
