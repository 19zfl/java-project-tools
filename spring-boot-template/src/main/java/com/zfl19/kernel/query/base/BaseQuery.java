package com.zfl19.kernel.query.base;

import lombok.Data;

/**
 * @author 19zfl
 * @date 2023/5/18
 */
@Data
public class BaseQuery {

    private Long[] ids; //批量删除时，前端传来的主键ID集合

    private String keyword;//关键字

    private Integer current = 1; //当前页

    private Integer size = 10; //每页显示多少条

}
