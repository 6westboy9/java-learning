package com.westboy.week_11;

public class Demo3 {
    public static void main(String[] args) {
        // long counter = 0;
        // List<Object> list = new ArrayList<>();
        // while (true) {
        //     list.add(new Object());
        //     System.out.println("当前创建了第" + (++counter) + "个对象");
        // }
        AException exception = new AException();
        System.out.println(exception instanceof  AException);
    }

    static class AException extends RuntimeException {
    }

}
