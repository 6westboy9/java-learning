package com.westboy.temp.performance;

import com.github.jobop.performance.spi.PerformanceBizSpi;
import com.github.jobop.performance.task.PerformanceTask;

import java.util.Random;

public class PerformanceDemo01 implements PerformanceBizSpi {

    public static void main(String[] args) {
        new PerformanceTask().t(1).c(1L).l("/temp_log/performance_demo01_test.log").addTest(new PerformanceDemo01()).start();
    }

    @Override
    public boolean execute() {
        // System.out.println("模拟50%成功率");
        return (new Random().nextInt(10) % 2) == 0;

    }
}
