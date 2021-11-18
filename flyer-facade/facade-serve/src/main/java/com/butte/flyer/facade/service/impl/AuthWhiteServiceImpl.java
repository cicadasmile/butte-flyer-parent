package com.butte.flyer.facade.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.butte.flyer.facade.dao.AuthWhiteDao;
import com.butte.flyer.facade.entity.PO.AuthWhite;
import com.butte.flyer.facade.service.AuthWhiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 白名单授权服务实现
 * @author 公众号:知了一笑
 * @since 2021-09-04 19:30
 */
@Service
public class AuthWhiteServiceImpl implements AuthWhiteService {

    @Resource
    private AuthWhiteDao authWhiteDao ;

    /**
     * 授权白名单类型
     * @param authType 授权类型
     * @return java.lang.String[]
     * @since 2021-09-04 19:32
     */
    @Override
    public String[] getByType(Integer authType) {
        List<AuthWhite> authWhites = authWhiteDao.listByType(authType) ;
        if (CollUtil.isNotEmpty(authWhites)){
            return authWhites.stream().map(AuthWhite::getAuthSign).toArray(String[]::new);
        }
        return new String[0];
    }
}
