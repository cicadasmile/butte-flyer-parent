package com.butte.flyer.admin.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.butte.base.constant.BaseState;
import com.butte.flyer.admin.dao.SysUserDao;
import com.butte.flyer.admin.entity.PO.SysUser;
import com.butte.flyer.admin.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * 系统用户表
 * @author 公众号:知了一笑
 * @since 2021-10-16 15:51
 */
@Service
public class SysUserDaoImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserDao {

    @Override
    public SysUser getByUserName(String userName) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>() ;
        wrapper.eq(SysUser::getUserName,userName).eq(SysUser::getState, BaseState.ENABLE.getState());
        return this.baseMapper.selectOne(wrapper);
    }

}
