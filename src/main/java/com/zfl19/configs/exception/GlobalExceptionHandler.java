package com.zfl19.configs.exception;

import com.zfl19.configs.result.JSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 19zfl
 * @date 2023/5/15
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public JSONResult myException(MyException e) {
        return JSONResult.error(e.getMessage());
    }

    /**
     * 兜底异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JSONResult exceptionHandler(Exception e) {
        return JSONResult.error("系统开小差了, 请稍后再试!");
    }

}
