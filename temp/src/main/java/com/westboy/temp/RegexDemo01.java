package com.westboy.temp;

public class RegexDemo01 {
    public static void main(String[] args) {
        String s1 = "\\xE6\\x9E\\x9C\\xE7\\xBB\\xQ7.  .";
        String s2 = "....\\xE6\\x9E\\x9C       \\xE7\\xBB\\xQ7";
        String s3 = "\\xE6\\x9E\\x9C....\\xE7\\xBB\\xQ7";

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        String s4 = s1.replaceAll("\\\\[x|X][\\da-zA-Z]{2}|[.]*", "1");
        String s5 = s2.replaceAll("\\\\[x|X][\\da-zA-Z]{2}|[.]*", "1");
        String s6 = s3.replaceAll("\\\\[x|X][\\da-zA-Z]{2}|[.]*", "1");
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
    }
}
