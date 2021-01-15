package com.westboy.json;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author pengbo
 * @since 2021/1/12
 */
public class JSONDemo {
    public static void main(String[] args) {
        String res = "{\"head\":{\"code\":\"201\",\"message\":\"支付请求失败:[subMsg:交易已经支付]\"},\"details\":null}";
        JSONObject resObj = JSONUtil.parseObj(res);
        int code = resObj.getByPath("head.code", Integer.class);
        String msg = resObj.getByPath("head.message", String.class);
        System.out.println(code);
        System.out.println(msg);
    }
}
