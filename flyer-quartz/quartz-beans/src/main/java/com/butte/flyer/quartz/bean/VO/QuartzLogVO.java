package com.butte.flyer.quartz.bean.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 任务日志VO
 * @author 公众号:知了一笑
 * @since 2021-08-07 21:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartzLogVO {

    private Long id;

    private Integer jobId;

    private String beanName;

    private String params;

    private Integer state;

    private String error;

    private Integer times;

    private Date createTime;
}
