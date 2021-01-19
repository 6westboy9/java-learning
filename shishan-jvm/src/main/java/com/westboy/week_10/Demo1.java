package com.westboy.week_10;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo1 {
    @SneakyThrows
    public static void main(String[] args) {
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }
        TimeUnit.MINUTES.sleep(1);
    }

    static class Data{}
}
