package com.butte.flyer.facade.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.butte.base.constant.BaseState;
import com.butte.flyer.facade.entity.PO.AuthWhite;
import com.butte.flyer.facade.mapper.AuthWhiteMapper;
import com.butte.flyer.facade.dao.AuthWhiteDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 授权白名单Dao层实现
 * @author 公众号:知了一笑
 * @since 2021-09-05 17:34
 */
@Service
public class AuthWhiteDaoImpl extends ServiceImpl<AuthWhiteMapper, AuthWhite> implements AuthWhiteDao {

    @Override
    public List<AuthWhite> listByType(Integer authType) {
        LambdaQueryWrapper<AuthWhite> wrapper = new LambdaQueryWrapper<>() ;
        wrapper.eq(AuthWhite::getAuthType,authType) ;
        wrapper.eq(AuthWhite::getState, BaseState.ENABLE.getState());
        return this.list(wrapper) ;
    }
}
