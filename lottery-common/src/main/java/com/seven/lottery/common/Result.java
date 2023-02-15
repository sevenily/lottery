package com.seven.lottery.common;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @Description: 统一返回对象中，Code码、Info描述
 * @Author: seven
 * @CreateTime: 2023-02-08 09:55
 * @Version: 1.0
 **/

public class Result implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;

    private String code;

    private String info;

    public static Result buildResult(Constants.ResponseCode code){
        return new Result(code.getCode(), code.getInfo());

    }

    public static Result buildResult(Constants.ResponseCode code, String info){
        return new Result(code.getCode(), info);
    }

    public static Result buildResult(String code, String info){
        return new Result(code, info);
    }

    public static Result build(Constants.ResponseCode code, Constants.ResponseCode info){
        return new Result(code.getCode(), code.getInfo());
    }

    public static Result buildSuccessResult(){
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult(){
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String info){
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), info);
    }

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
