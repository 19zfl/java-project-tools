package com.zfl19.configs.result;

import com.zfl19.configs.constants.result.ErrorCode;
import lombok.Data;

/**
 * 接口统一返回结果对象
 * @author 19zfl
 * @date 2023/5/15
 */
@Data
public class JSONResult {

    private boolean success = true;

    private String message = "成功";

    //成功统一返回0000，其余编码全部是错误码
    private String code = "0000";

    //返回的数据
    private Object data;

    //创建当前实例
    public static JSONResult success(){
        return new JSONResult();
    }

    //创建当前实例
    public static JSONResult success(Object obj){
        JSONResult instance = new JSONResult();
        instance.setData(obj);
        return instance;
    }

    //成功，但是返回不同消息代码
    public static JSONResult success(Object obj, String code){
        JSONResult instance = new JSONResult();
        instance.setSuccess(true);
        instance.setCode(code);
        instance.setData(obj);
        return instance;
    }

    //创建当前实例
    public static JSONResult error(){
        JSONResult instance = new JSONResult();
        instance.setCode(ErrorCode.SYSTEM_ERROR.getCode());
        instance.setSuccess(false);
        instance.setMessage(ErrorCode.SYSTEM_ERROR.getMessage());
        return instance;
    }

    //创建当前实例
    public static JSONResult error(String message){
        JSONResult instance = new JSONResult();
        instance.setCode(ErrorCode.SYSTEM_ERROR.getCode());
        instance.setSuccess(false);
        instance.setMessage(message);
        return instance;
    }

    public static JSONResult error(String message, Object obj){
        JSONResult instance = new JSONResult();
        instance.setCode(ErrorCode.SYSTEM_ERROR.getCode());
        instance.setMessage(message);
        instance.setSuccess(false);
        instance.setData(obj);
        return instance;
    }

    //接收一个错误码的枚举
    public static JSONResult error(ErrorCode errorCode){
        JSONResult instance = new JSONResult();
        instance.setMessage(errorCode.getMessage());
        instance.setSuccess(false);
        instance.setCode(errorCode.getCode());
        return instance;
    }

}
