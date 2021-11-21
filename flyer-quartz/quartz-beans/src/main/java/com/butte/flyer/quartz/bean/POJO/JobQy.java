package com.butte.flyer.quartz.bean.POJO;

import com.butte.base.page.PageQy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 任务查询
 * @author 公众号:知了一笑
 * @since 2021-08-22 15:19
 */
@ApiModel(value="JobQy", description="任务查询")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class JobQy extends PageQy {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "执行Bean名称")
    private String beanName;
}
