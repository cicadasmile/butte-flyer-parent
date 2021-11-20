package com.butte.flyer.account.beans.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 登录日志VO
 * @author 公众号:知了一笑
 * @since 2021-09-10 20:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginLogVO {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "获取的Token")
    private String token;

}
