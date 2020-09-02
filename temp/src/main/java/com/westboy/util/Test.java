package com.westboy.util;

import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengbo
 * @since 2020/8/26
 */
public class Test {

    public static void main(String[] args) {
        // test1(1);
        // test1(2);
        // test1(3);

        int num = 10000;
        // test21(1, num);
        test21(2, num);
        // test21(3, num);

        // test22(1, num);
        // test22(2, num);
        // test22(3, num);
    }

    private static void test1(int type) {
        long start = System.currentTimeMillis();
        UserDO userDO = createUser();
        UserDTO userDTO1 = copyByBeanCopier(userDO, type);
        System.out.println("复制前: " + userDTO1);
        System.out.println("复制后: " + userDTO1);
        long end = System.currentTimeMillis();
        System.out.println("无缓存耗时: " + (end - start) + "ms");

        UserDTO userDT2 = copyByBeanCopier(userDO, type);
        System.out.println("有缓存耗时: " + (System.currentTimeMillis() - end) + "ms");
    }

    private static void test21(int type, int num) {
        long start = System.currentTimeMillis();
        List<UserDO> userDOs = createUsers(num);
        userDOs.forEach(userDO -> copyByBeanCopier(userDO, type));
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }


    private static void test22(int type, int num) {
        long start = System.currentTimeMillis();
        List<UserDO> userDOs = createUsers(num);

        if (type == 1 || type == 2) {
            userDOs.forEach(userDO -> copyByBeanCopier(userDO, type));
        } else {
            BeanMapper.mapList(userDOs, UserDO.class, UserDTO.class);
        }

        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }


    private static UserDTO copyByBeanCopier(UserDO userDO, int type) {
        if (type == 1) {
            UserDTO userDTO = new UserDTO();
            BeanCopierUtils.copyProperties(userDO, userDTO);
            return userDTO;
        } else if (type == 2){
            return BeanCopierUtils.copyProperties2(userDO, UserDTO.class);
        } else if (type == 3) {
            return BeanMapper.map(userDO, UserDTO.class);
        }
        return null;
    }


    public static UserDO createUser() {
        return new UserDO(1, "mike", 18, DateUtil.date());
    }


    public static List<UserDO> createUsers(int num) {
        List<UserDO> userDOS = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            userDOS.add(new UserDO(i + 1, "mike", 18, DateUtil.date()));
        }
        return userDOS;
    }
}
