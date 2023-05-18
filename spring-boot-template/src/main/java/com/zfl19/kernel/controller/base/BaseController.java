package com.zfl19.kernel.controller.base;

import cn.wujiangbo.constants.ErrorCode;
import cn.wujiangbo.domain.system.SysUser;
import cn.wujiangbo.exception.MyException;
import cn.wujiangbo.util.MyTools;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: Controller的基础类
 * @author 波波老师(weixin:javabobo0513)
 */
public class BaseController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取登录人的信息
     * @return
     */
    public SysUser getCurrentUser(){
        //获取 HttpServletRequest 对象
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        //从请求头中获取 token 值
        String token = request.getHeader("token");
        if(!MyTools.hasLength(token)){
            throw new MyException(ErrorCode.ERROR_CODE_1002.getMessage());
        }
        String userInfoString = (String)redisTemplate.opsForValue().get(token);
        if(!MyTools.hasLength(userInfoString)){
            throw new MyException(ErrorCode.ERROR_CODE_1002.getMessage());
        }
        SysUser sysUser = JSONObject.parseObject(userInfoString, SysUser.class);
        return sysUser;
    }

    /**
     * 获取登录人的用户ID
     * @return
     */
    public Long getCurrentUserId(){
        return getCurrentUser().getId();
    }

    /**
     * 获取登录人的真实姓名
     * @return
     */
    public String getCurrentUserRealName(){
        return getCurrentUser().getRealName();
    }
}
