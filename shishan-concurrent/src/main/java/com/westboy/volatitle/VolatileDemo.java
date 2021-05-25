package com.westboy.volatitle;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    private static volatile int flag = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localFlag = flag;
            // 死循环
            while(true) {
                if(localFlag != flag) { // 不相等时，才打印
                    System.out.println("读取到了修改后的标志位：" + flag);
                    localFlag = flag;
                }
            }
        }).start();

        new Thread(() -> {
            int localFlag = flag;
            // 死循环
            while(true) {
                System.out.println("标志位被修改为了：" + ++localFlag);
                flag = localFlag; // 进行修改操作
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

