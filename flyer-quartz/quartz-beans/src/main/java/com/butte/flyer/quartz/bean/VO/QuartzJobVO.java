package com.butte.flyer.quartz.bean.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 任务实体VO
 * @author 公众号:知了一笑
 * @since 2021-08-07 21:06
 */
@ApiModel(value="QuartzJobVO", description="定时任务主体VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartzJobVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "执行Bean名称")
    private String beanName;

    @ApiModelProperty(value = "执行Bean名称")
    private String params;

    @ApiModelProperty(value = "执行Bean名称")
    private String cronExpres;

    @ApiModelProperty(value = "状态：1正常，2暂停,3删除")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}