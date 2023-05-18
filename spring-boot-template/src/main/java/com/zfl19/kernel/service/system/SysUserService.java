package com.zfl19.kernel.service.system;

import com.zfl19.kernel.domain.system.SysUser;
import com.zfl19.kernel.mapper.system.SysUserMapper;
import com.zfl19.kernel.query.system.SysUserQuery;
import com.zfl19.configs.utils.MyTools;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 19zfl
 */
@Transactional
@Service
@Slf4j
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser>{

    @Resource
    private SysUserMapper sysuserMapper;

    //查询分页列表数据(使用QueryWrapper操作)
    public Page<SysUser> selectMyPage(SysUserQuery query) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (MyTools.hasLength(query.getKeyword())) {
            wrapper.and(i -> i.like("id", query.getKeyword()));
        }
        //排序
        wrapper.orderByDesc("id");
        Page<SysUser> page = new Page<>(query.getCurrent(), query.getSize());
        return super.page(page, wrapper);
    }

    //查询分页列表数据(自己写SQL)
    public Page<SysUser> selectMySqlPage(SysUserQuery query) {
        Page<SysUser> page = new Page<>(query.getCurrent(), query.getSize());
        List<SysUser> list = sysuserMapper.selectMySqlPage(page, query);
        return page.setRecords(list);
    }
}