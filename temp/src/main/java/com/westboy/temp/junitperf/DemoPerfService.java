package com.westboy.temp.junitperf;

import cn.hutool.core.lang.UUID;

public class DemoPerfService {
    public String getServiceId(String userId){
        return UUID.randomUUID().toString();
    }
}
