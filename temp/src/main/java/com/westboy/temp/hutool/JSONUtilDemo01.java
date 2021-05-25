package com.westboy.temp.hutool;

import cn.hutool.json.JSONUtil;

public class JSONUtilDemo01 {
    public static void main(String[] args) {
        String s = "{\"files\":[{\"filename\":\"/image/59922f5868.jpg\",\"fileLength\":8741949}],\"storedDataSize\":8741949}";

        StorageInfo storageInfo = JSONUtil.toBean(s, StorageInfo.class);
        System.out.println(storageInfo);
    }
}
