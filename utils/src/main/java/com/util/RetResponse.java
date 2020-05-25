package com.util;

public class RetResponse {


    public static RetResult makeOKRsp() {
        return new RetResult(RetCode.SUCCESS.getCode(),"success");
    }
    public static RetResult makeOKRsp(String message) {
        return new RetResult(RetCode.SUCCESS.getCode(),message);
    }
    public static RetResult makeOKRsp(Object data) {
        return new RetResult(RetCode.SUCCESS.getCode(),"success",data);
    }
}
