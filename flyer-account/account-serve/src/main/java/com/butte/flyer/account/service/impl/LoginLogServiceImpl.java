package com.butte.flyer.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.butte.flyer.account.beans.VO.LoginLogVO;
import com.butte.flyer.account.dao.LoginLogDao;
import com.butte.flyer.account.entity.PO.LoginLog;
import com.butte.flyer.account.service.LoginLogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 登录日志表Service层实现
 * @author 公众号:知了一笑
 * @since 2021-09-09 20:45
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogDao loginLogDao ;

    /**
     * 添加登录日志
     * @since 2021-09-10 20:12
     */
    @Override
    public void addLoginLog(LoginLogVO loginLogVO) {
        if (ObjectUtil.isNotNull(loginLogVO)){
            LoginLog loginLog = BeanUtil.copyProperties(loginLogVO,LoginLog.class);
            loginLogDao.save(loginLog) ;
        }
    }

}
