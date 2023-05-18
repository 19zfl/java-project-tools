package com.zfl19.kernel.controller.system;

import com.zfl19.kernel.domain.system.SysLog;
import com.zfl19.kernel.result.PageList;
import com.zfl19.kernel.service.system.SysLogService;
import com.zfl19.kernel.query.system.SysLogQuery;
import com.zfl19.kernel.controller.base.BaseController;
import com.zfl19.kernel.util.DateUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ej.platform.result.JSONResult;
import ej.platform.result.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;

/**
 * @desc  API接口
 * @author 19zfl
 */
@RestController
@RequestMapping("/sysLog")
@Api(value = "/sysLog", tags = {" API接口"})
public class SysLogController extends BaseController{

    @Autowired
    public SysLogService sysLogService;

    /**
     * 新增数据到【】
     * @author 19zfl
     */
    @PostMapping(value="/save")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "body", dataType = "SysLog", name = "sysLog", value = "")
    })
    @ApiOperation(value = "新增数据到【】", notes = "新增数据到【】", httpMethod = "POST")
    public JSONResult save(@RequestBody SysLog sysLog){
        sysLog.setCreateTime(DateUtils.getCurrentLocalDateTime());
        sysLog.setCreateUserName(getCurrentUserRealName());
        sysLogService.save(sysLog);
        return JSONResult.success(true);
    }

    /**
     * 修改【】表数据
     * @author 19zfl
     */
    @PostMapping(value="/update")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "body", dataType = "SysLog", name = "sysLog", value = "")
    })
    @ApiOperation(value = "修改【】表数据", notes = "修改【】表数据", httpMethod = "POST")
    public JSONResult update(@RequestBody SysLog sysLog){
        sysLog.setUpdateTime(DateUtils.getCurrentLocalDateTime());
        sysLog.setUpdateUserName(getCurrentUserRealName());
        sysLogService.updateById(sysLog);
        return JSONResult.success(true);
    }

    /**
     * 批量删除【】数据
     * @author 19zfl
     */
    @PostMapping(value="/batchDelete")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "body", dataType = "SysLogQuery", name = "query", value = "")
    })
    @ApiOperation(value = "批量删除【】数据", notes = "批量删除【】数据", httpMethod = "POST")
    public JSONResult batchDelete(@RequestBody SysLogQuery query){
        //批量删除数据库数据
        sysLogService.removeByIds(Arrays.asList(query.getIds()));
        return JSONResult.success(true);
    }

    /**
     * 单个删除【】数据
     * @author 19zfl
    */
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "")
    })
    @ApiOperation(value = "单个删除【】数据", notes = "单个删除【】数据", httpMethod = "DELETE")
    @DeleteMapping("/singleDelete/{id}")
    public JSONResult batchDelete(@PathVariable("id") Long id){
        //单个删除数据库数据
        sysLogService.removeById(id);
        return JSONResult.success(true);
    }

    /**
    * 根据ID查询【】详情数据
    * @author 19zfl
    */
    @GetMapping(value = "/{id}")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "")
    })
    @ApiOperation(value = "根据ID查询【】详情数据", notes = "根据ID查询【】详情数据", httpMethod = "GET")
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(sysLogService.getById(id));
    }

    /**
    * 查询【】所有数据（不分页）
    * @author 19zfl
    */
    @GetMapping(value = "/list")
    @ApiOperation(value = "查询【】所有数据（不分页）", notes = "查询【】所有数据（不分页）", httpMethod = "GET")
    public JSONResult list(){
        List<SysLog> list = sysLogService.list(null);
        return JSONResult.success(list);
    }

    /**
     * 查询【】数据（分页）
     * @param query 查询对象
     * @return PageList 分页对象
     * @author 19zfl
     */
    @PostMapping(value = "/pagelist")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "body", dataType = "SysLogQuery", name = "query", value = "查询对象")
    })
    @ApiOperation(value = "查询【】数据（分页）", notes = "查询【】数据（分页）", httpMethod = "POST")
    public JSONResult pagelist(@RequestBody SysLogQuery query){
        Page<SysLog> page = sysLogService.selectMySqlPage(query);
        return JSONResult.success(new PageList<>(page.getTotal(), page.getRecords()));
    }

}