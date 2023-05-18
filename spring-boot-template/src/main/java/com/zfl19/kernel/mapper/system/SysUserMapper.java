package com.zfl19.kernel.mapper.system;

import com.zfl19.kernel.domain.system.SysUser;
import com.zfl19.kernel.query.system.SysUserQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* <p>
* 用户信息表 Mapper接口
* </p>
*
* @author 19zfl
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    //查询分页列表数据
    List<SysUser> selectMySqlPage(Page<SysUser> page, @Param("query") SysUserQuery query);
}