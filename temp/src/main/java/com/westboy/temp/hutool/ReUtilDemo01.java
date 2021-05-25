package com.westboy.temp.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.pinyin.engine.pinyin4j.Pinyin4jEngine;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReUtilDemo01 {

    private static final String PREFIX = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/";
    private static final String INDEX_HTML = PREFIX + "index.html";

    private static final String SECOND_LEVEL_REGEX = "<a href='(.*?)'>(.*?)<br/>";
    private static final String THIRD_LEVEL_REGEX = "<tr class='citytr'><td><a href='(.*?)'>(.*?)</a></td><td><a href='(.*?)'>(.*?)</a></td></tr>";
    private static final String FORTH_LEVEL_REGEX = "<tr class='countytr'><td><a href='(.*?)'>(.*?)</a></td><td><a href='(.*?)'>(.*?)</a></td></tr>";
    private static final String FIFTH_LEVEL_REGEX = "<tr class='towntr'><td><a href='(.*?)'>(.*?)</a></td><td><a href='(.*?)'>(.*?)</a></td></tr>";

    private static final Pinyin4jEngine PINYIN_ENGINE = new Pinyin4jEngine();

    public static void main(String[] args) {
        // 获取二级地址
        List<Address> allAddresses = getAddress(null, INDEX_HTML, SECOND_LEVEL_REGEX, 2);
        save(allAddresses, 2);


        // 获取三级地址
        ArrayList<Address> secondAddresses = CollUtil.newArrayList(allAddresses.get(0)); // 取河北省
        for (Address secondAddress : secondAddresses) {
            String secondHtml = secondAddress.getHtml();
            String secondIdStr = secondHtml.substring(0, secondHtml.indexOf("."));

            List<Address> thirdTemp = getAddress(secondAddress.getName(), PREFIX + secondAddress.getHtml(), THIRD_LEVEL_REGEX, secondAddress.getLevel() + 1);
            save(allAddresses, 3);

            // 查询四级地址
            ArrayList<Address> thirdAddresses = CollUtil.newArrayList(thirdTemp); // 取石家庄市
            for (Address thirdAddress : thirdAddresses) {
                List<Address> fourthTemp = getAddress(null, PREFIX + thirdAddress.getHtml(), FORTH_LEVEL_REGEX, thirdAddress.getLevel() + 1);
                save(allAddresses, 4);


                // 查询五级地址
                ArrayList<Address> fourthAddresses = CollUtil.newArrayList(fourthTemp); // 取长安区

                for (Address fourthAddress : fourthAddresses) {
                    List<Address> fifthTemp = getAddress(null, PREFIX + secondIdStr + "/" + fourthAddress.getHtml(), FIFTH_LEVEL_REGEX, fourthAddress.getLevel() + 1);
                    save(allAddresses, 5);
                }
            }
        }
    }

    private static void save(List<Address> allAddresses, int level) {

    }


    private static List<Address> getAddress(String parentName, String url, String regex, int level) {
        String content = HttpUtil.get(url);

        List<String> htmlList = ReUtil.findAll(regex, content, 1);
        List<String> nameList;
        List<String> idList = new ArrayList<>();

        if (level == 2) {
            nameList = ReUtil.findAll(regex, content, 2);
        } else {
            idList = ReUtil.findAll(regex, content, 2);
            nameList = ReUtil.findAll(regex, content, 4);
        }

        List<Address> addresses = new LinkedList<>();
        for (int i = 0; i < htmlList.size(); i++) {
            Address address = new Address();

            if (level == 3 && "市辖区".equals(nameList.get(i))) {
                // 对直辖市的特殊处理
                address.setName(parentName);
            } else {
                address.setName(nameList.get(i));
            }



            if (level == 2) {
                String html = htmlList.get(i);
                address.setId(Math.toIntExact(Integer.parseInt(html.substring(0, html.indexOf("."))) * 10000000L));
            } else {
                try {
                    address.setId(Math.toIntExact(Long.parseLong(idList.get(i)) / 1000));
                } catch (Exception e) {
                    Console.error(nameList.get(i) + ":" + idList.get(i));
                    throw e;
                }

            }

            address.setHtml(htmlList.get(i));
            address.setLevel(level);
            address.setPinyin(PINYIN_ENGINE.getPinyin(address.getName(), ""));
            addresses.add(address);
        }
        return addresses;
    }

    @Data
    private static class Address {
        private String name;
        private Integer id;
        private String html;
        private int level;
        private String pinyin;

        public String toString() {
            return JSONUtil.toJsonStr(this);
        }
    }
}
