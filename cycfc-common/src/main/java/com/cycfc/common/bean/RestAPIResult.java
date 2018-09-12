package com.cycfc.common.bean;

import com.cycfc.common.constant.SystemConstants;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：REST API接口统一响应接口实体类<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/11 10:52<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@Data
public class RestAPIResult<T> implements Serializable {

    // 返回代码，1表示成功，其它的都有对应问题
    private int resCode = 1;
    // 如果code 不等于 1，提示错误信息
    private String resMsg = "SUCCESS";

    // respCode为1时返回结果
    private T resData = (T) new Object();

    // 附加信息
    private Map<String, Object> resMap = new HashMap();

    // 接口返回数据的签名信息
    private String signature;

    // 请求接口时的设备类型
    private String deviceType;

    // base64加密，用于客户端验证签名
    private String dataCode;

    public RestAPIResult(String errorMsg) {
        this.resMsg = errorMsg;
        this.resCode = SystemConstants.Code.error;
        this.resData = (T) new Object();
        this.resMap = new HashMap<>();
    }

    public RestAPIResult() {
        this.resData = (T) new Object();
        this.resMap = new HashMap<>();
    }

    public void success(T object) {
        this.resCode = SystemConstants.Code.success;
        this.resMsg = SystemConstants.Msg.SUCCESS;
        this.resData = object;
        this.resMap = new HashMap<>();
    }

    public void error() {
        this.resCode = SystemConstants.Code.error;
        this.resMsg = SystemConstants.Msg.ERROR;
        this.resData = (T) new Object();
        this.resMap = new HashMap<>();
    }

    public void error(String message) {
        this.resCode = SystemConstants.Code.error;
        this.resMsg = message;
        this.resData = (T) new Object();
        this.resMap = new HashMap<>();
    }

}
