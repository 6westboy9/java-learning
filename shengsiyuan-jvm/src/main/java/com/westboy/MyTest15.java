package com.westboy;

public class MyTest15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(String.class.getClassLoader());
        System.out.println(strings.getClass().getClassLoader());
        System.out.println("--------------");

        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println(MyTest15.class.getClassLoader());
        System.out.println(myTest15s.getClass().getClassLoader());
        System.out.println("--------------");

        int[] ints = new int[2];
        System.out.println(int.class.getClassLoader());
        System.out.println(ints.getClass().getClassLoader());
    }

    @Override
    public String toString() {
        return "MyTest15";
    }
}
