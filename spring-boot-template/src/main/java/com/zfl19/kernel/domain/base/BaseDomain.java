package com.zfl19.kernel.domain.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @desc 基础Domain类
 * @author 19zfl
 */
@Data
public class BaseDomain implements Serializable {

    @ApiModelProperty(value = "创建人名称")
    @TableField(exist = false)
    private String createUserName;

    @ApiModelProperty(value = "更新人名称")
    @TableField(exist = false)
    private String updateUserName;

}
