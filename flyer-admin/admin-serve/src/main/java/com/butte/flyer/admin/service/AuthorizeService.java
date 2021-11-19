package com.butte.flyer.admin.service;

import com.butte.flyer.admin.entity.PO.SysUser;

/**
 * 授权服务
 * @author 公众号:知了一笑
 * @since 2021-09-04 16:50
 */
public interface AuthorizeService {

    /**
     * 用户登录授权
     * @param user 用户实体
     * @since 2021-09-07 23:18
     */
    String login (SysUser user) ;

}
