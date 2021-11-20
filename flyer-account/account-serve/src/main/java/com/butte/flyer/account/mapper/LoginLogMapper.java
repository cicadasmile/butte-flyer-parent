package com.butte.flyer.account.mapper;

import com.butte.flyer.account.entity.PO.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志表 Mapper 接口
 * @author 公众号:知了一笑
 * @since 2021-09-09 20:41
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}
