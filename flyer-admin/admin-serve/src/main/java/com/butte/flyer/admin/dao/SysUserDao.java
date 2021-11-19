package com.butte.flyer.admin.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.butte.flyer.admin.entity.PO.SysUser;

/**
 * 用户DAO层
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:30
 */
public interface SysUserDao extends IService<SysUser> {

    SysUser getByUserName(String userName) ;

}
