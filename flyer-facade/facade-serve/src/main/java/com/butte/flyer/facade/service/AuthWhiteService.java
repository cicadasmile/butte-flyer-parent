package com.butte.flyer.facade.service;

/**
 * 白名单授权服务
 * @author 公众号:知了一笑
 * @since 2021-09-04 19:29
 */
public interface AuthWhiteService {

    /**
     * 授权白名单类型
     * @param authType 授权类型
     * @return java.lang.String[]
     * @since 2021-09-04 19:32
     */
    String[] getByType (Integer authType) ;

}
