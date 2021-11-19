package com.butte.flyer.admin.service;

import com.butte.flyer.admin.entity.VO.AuthUserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户加载服务
 * @author 公众号:知了一笑
 * @since 2021-09-04 20:42
 */
public interface LoadUserService extends UserDetailsService {

    /**
     * 根据用户ID查询
     * @param userId 用户ID
     * @since 2021-09-27 20:09
     */
    AuthUserVO loadByUserId (Integer userId) ;
}
