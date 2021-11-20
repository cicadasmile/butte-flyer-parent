package com.butte.flyer.account.dao.impl;

import com.butte.flyer.account.entity.PO.LoginLog;
import com.butte.flyer.account.mapper.LoginLogMapper;
import com.butte.flyer.account.dao.LoginLogDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 登录日志表Dao实现层
 * @author 公众号:知了一笑
 * @since 2021-09-09 20:45
 */
@Service
public class LoginLogDaoImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogDao {

}
