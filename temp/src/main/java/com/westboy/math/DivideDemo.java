package com.westboy.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author pengbo
 * @since 2021/1/11
 */
public class DivideDemo {
    public static void main(String[] args) {
        BigDecimal allAmount = BigDecimal.valueOf(0.05);


        BigDecimal res = allAmount.divide(BigDecimal.valueOf(3), 2, RoundingMode.DOWN );
        System.out.println(res);

    }
}
