package ${cfg.basePath}.mapper.${cfg.moduleName};

import ${cfg.basePath}.domain.${cfg.moduleName}.${entity};
import ${cfg.basePath}.query.${cfg.moduleName}.${entity}Query;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* <p>
* ${table.comment!} Mapper接口
* </p>
*
* @author 19zfl
*/
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    //查询分页列表数据
    List<${entity}> selectMySqlPage(Page<${entity}> page, @Param("query") ${entity}Query query);
}