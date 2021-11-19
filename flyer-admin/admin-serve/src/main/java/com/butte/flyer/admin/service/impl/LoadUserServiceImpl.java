package com.butte.flyer.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.butte.base.entity.ExeRep;
import com.butte.flyer.admin.dao.SysUserDao;
import com.butte.flyer.admin.entity.PO.SysUser;
import com.butte.flyer.admin.entity.VO.AuthUserVO;
import com.butte.flyer.admin.service.LoadUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 自定义加载用户
 * @author 公众号:知了一笑
 * @since 2021-09-04 20:40
 */
@Service
public class LoadUserServiceImpl implements LoadUserService {

    @Resource
    private SysUserDao sysUserDao ;

    /**
     * 根据用户名加载用户
     * @param userName 用户名
     * @since 2021-09-12 12:51
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.getByUserName(userName) ;
        if (ObjectUtil.isNotNull(sysUser)){
            return BeanUtil.copyProperties(sysUser,AuthUserVO.class);
        }
        throw new ExeRep(HttpStatus.HTTP_NO_CONTENT,"用户不存在");
    }

    /**
     * 根据用户ID查询
     * @param userId 用户ID
     * @since 2021-09-27 20:09
     */
    @Override
    public AuthUserVO loadByUserId(Integer userId) {
        SysUser sysUser = sysUserDao.getById(userId) ;
        if (ObjectUtil.isNotNull(sysUser)){
            return BeanUtil.copyProperties(sysUser,AuthUserVO.class);
        }
        throw new ExeRep(HttpStatus.HTTP_NO_CONTENT,"用户不存在");
    }
}
