package com.westboy.temp.hutool;

import cn.hutool.http.HttpUtil;

public class HttpUtilDemo01 {


    private static final String SF_REGION_URL1 = "https://www.sf-express.com/sf-service-owf-web/service/region/new/A000086000/subRegions?level=-1&lang=sc&region=cn&translate=";
    private static final String SF_REGION_URL2 = "https://www.sf-express.com/sf-service-owf-web/service/region/new/A340000000/subRegions?level=2&lang=sc&region=cn&translate";
    private static final String SF_REGION_URL3 = "https://www.sf-express.com/sf-service-owf-web/service/region/new/A340300000/subRegions?level=3&lang=sc&region=cn&translate=";



    public static void main(String[] args) {
        HttpUtilDemo01 demo01 = new HttpUtilDemo01();
        demo01.fetch();
    }

    private void fetch() {
        String result  = HttpUtil.get(SF_REGION_URL1);

        // {"id":"41-SC","code":"A340000000","rateCode":"340","level":2,"parentCode":"A000086000","parentId":"11","distId":"41","name":"安徽省","lang":"SC","countryCode":"A000086000","opening":false,"availableAsDestination":true,"availableAsOrigin":true,"workAddDays":null,"remark":null}
        // System.out.println(JSONUtil.toJsonStr(result));



    }
}
