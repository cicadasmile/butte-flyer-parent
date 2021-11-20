package com.butte.flyer.account.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.butte.base.constant.BaseState;
import com.butte.flyer.account.dao.UserDao;
import com.butte.flyer.account.entity.PO.User;
import com.butte.flyer.account.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户DAO实现层
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:31
 */
@Service
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {

    @Override
    public User getByUserName(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>() ;
        wrapper.eq(User::getUserName,userName).eq(User::getState, BaseState.ENABLE.getState());
        return this.baseMapper.selectOne(wrapper);
    }
}
