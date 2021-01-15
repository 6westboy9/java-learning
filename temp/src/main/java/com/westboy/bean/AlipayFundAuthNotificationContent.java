package com.westboy.bean;

import lombok.Data;

/**
 * @author pengbo
 * @since 2021/1/9
 */
@Data
public class AlipayFundAuthNotificationContent {
    private String gmtCreate;
    private String charset;
    private String operationType;
    private String sign;
    private String authNo;
    private String notifyId;
    private String notifyType;
    private String gmtTrans;
    private String operationId;
    private String outRequestNo;
    private String payerUserId;
    private String appId;
    private String signType;
    private String amount;
    private String restAmount;
    private String notifyTime;
    private String payeeUserId;
    private String outOrderNo;
    private String payeeLogonId;
    private String version;
    private String totalPayAmount;
    private String totalFreezeAmount;
    private String authAppId;
    private String totalUnfreezeAmount;
    private String status;
    private String payerLogonId;

}
