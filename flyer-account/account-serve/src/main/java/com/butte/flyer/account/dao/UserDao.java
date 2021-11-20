package com.butte.flyer.account.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.butte.flyer.account.entity.PO.User;

/**
 * 用户DAO层
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:30
 */
public interface UserDao extends IService<User> {

    User getByUserName (String userName) ;

}
