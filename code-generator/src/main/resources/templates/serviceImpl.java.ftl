package ${cfg.basePath}.service.${cfg.moduleName};

import ${cfg.basePath}.domain.${cfg.moduleName}.${entity};
import ${cfg.basePath}.mapper.${cfg.moduleName}.${entity}Mapper;
import ${cfg.basePath}.query.${cfg.moduleName}.${entity}Query;
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
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author 19zfl
 */
@Transactional
@Service
@Slf4j
public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>{

    @Resource
    private ${table.mapperName} ${cfg.entityName}Mapper;

    //查询分页列表数据(使用QueryWrapper操作)
    public Page<${entity}> selectMyPage(${entity}Query query) {
        QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
        if (MyTools.hasLength(query.getKeyword())) {
            wrapper.and(i -> i.like("id", query.getKeyword()));
        }
        //排序
        wrapper.orderByDesc("id");
        Page<${entity}> page = new Page<>(query.getCurrent(), query.getSize());
        return super.page(page, wrapper);
    }

    //查询分页列表数据(自己写SQL)
    public Page<${entity}> selectMySqlPage(${entity}Query query) {
        Page<${entity}> page = new Page<>(query.getCurrent(), query.getSize());
        List<${entity}> list = ${cfg.entityName}Mapper.selectMySqlPage(page, query);
        return page.setRecords(list);
    }
}