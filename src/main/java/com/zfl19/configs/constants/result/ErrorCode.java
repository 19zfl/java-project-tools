package com.zfl19.configs.constants.result;

//系统错误码
public enum ErrorCode {

    SYSTEM_ERROR("1001", "系统内部错误"),
    UPLOAD_FILE_TYPE_ERROR("1002", "文件类型错误"),
    UPLOAD_FILE_FAIL("1003", "文件上传失败");

    //错误码
    private String code;

    //错误信息
    private String message;

    ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}