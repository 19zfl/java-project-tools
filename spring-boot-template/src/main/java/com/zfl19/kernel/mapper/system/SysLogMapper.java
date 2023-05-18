package com.zfl19.kernel.mapper.system;

import com.zfl19.kernel.domain.system.SysLog;
import com.zfl19.kernel.query.system.SysLogQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* <p>
*  Mapper接口
* </p>
*
* @author 19zfl
*/
public interface SysLogMapper extends BaseMapper<SysLog> {

    //查询分页列表数据
    List<SysLog> selectMySqlPage(Page<SysLog> page, @Param("query") SysLogQuery query);
}