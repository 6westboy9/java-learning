package com.westboy.string;

import java.util.Arrays;

/**
 * @author pengbo.wang
 * @date 2019/8/12
 * @since 1.0
 */
public class Solution {

    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        System.out.println(convert(s, 5));
    }

    private static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        char[] chars = s.toCharArray();

        String[] strings = new String[numRows];
        Arrays.fill(strings, "");

        // 是否继续向下
        boolean down = false;
        // 数组下标位置
        int loc = 0;
        for (char aChar : chars) {
            strings[loc] += aChar;
            if (loc == 0 || loc == numRows - 1) {
                down = !down;
            }

            loc = down ? loc + 1 : loc - 1;
        }


        StringBuilder result = new StringBuilder();
        for (String st : strings) {
            result.append(st);
        }
        return result.toString();
    }
}
