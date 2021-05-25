package com.westboy.futuretask;

import cn.hutool.core.lang.Console;
import cn.hutool.log.dialect.console.ConsoleLog;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static final int SLEEP_GAP = 500;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                Console.log(getCurThreadName() + "洗好水壶");
                Console.log(getCurThreadName() + "灌上凉水");
                Console.log(getCurThreadName() + "放在火上");
                Thread.sleep(SLEEP_GAP);
                Console.log(getCurThreadName() + "水开了");
            } catch (InterruptedException e) {
                Console.error(getCurThreadName() + "烧水工作发生异常被中断");
                return false;
            }
            Console.log(getCurThreadName() + "烧水工作运行结束");
            return true;
        }
    }

    static class WashJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                Console.log(getCurThreadName() + "洗茶壶");
                Console.log(getCurThreadName() + "洗茶叶");
                Console.log(getCurThreadName() + "拿茶叶");
                Thread.sleep(SLEEP_GAP);
                Console.log(getCurThreadName() + "洗完了");
            } catch (InterruptedException e) {
                Console.error(getCurThreadName() + "清晰工作发生异常被中断");
                return false;
            }
            Console.log(getCurThreadName() + "清晰工作运行结束");
            return true;
        }
    }

    public static void main(String[] args) {
        HotWaterJob hotWaterJob = new HotWaterJob();
        FutureTask<Boolean> hTask = new FutureTask<>(hotWaterJob);
        Thread hThread = new Thread(hTask, ">> 烧水线程 ");

        WashJob washJob = new WashJob();
        FutureTask<Boolean> wTask = new FutureTask<>(washJob);
        Thread wThread = new Thread(wTask, "## 清洗线程 ");

        hThread.start();
        wThread.start();

        Thread.currentThread().setName("$$ 主线程 ");

        try {
            Boolean waterOk = hTask.get();
            Boolean washOk = wTask.get();
            drainTea(waterOk, washOk);
        } catch (InterruptedException | ExecutionException e) {
            Console.error(getCurThreadName() + "发生了异常");
        }
        Console.log(getCurThreadName() + "运行结束");
    }

    private static void drainTea(Boolean waterOk, Boolean washOk) {
        if (waterOk && washOk) {
            Console.log(getCurThreadName() + "喝茶");
        }
    }
}
