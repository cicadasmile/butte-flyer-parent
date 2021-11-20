package com.butte.flyer.account.service;

import com.butte.flyer.account.beans.VO.LoginLogVO;

/**
 * 登录日志表Service层
 * @author 公众号:知了一笑
 * @since 2021-09-09 20:45
 */
public interface LoginLogService {

    /**
     * 添加登录日志
     * @since 2021-09-10 20:12
     */
    void addLoginLog (LoginLogVO loginLogVO) ;

}
