package com.butte.flyer.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpStatus;
import com.butte.base.entity.ExeRep;
import com.butte.core.operate.JwtOperate;
import com.butte.flyer.account.beans.VO.UserVO;
import com.butte.flyer.account.dao.UserDao;
import com.butte.flyer.account.entity.PO.User;
import com.butte.flyer.account.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 用户服务层实现
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao ;

    @Resource
    private JwtOperate jwtOperate ;

    /**
     * 用户注册
     * @param userVO 用户主体VO
     * @return java.lang.Integer
     * @since 2021-09-05 16:20
     */
    @Override
    public Boolean register(UserVO userVO) {
        User user = BeanUtil.copyProperties(userVO, User.class) ;
        if (ObjectUtil.isNotNull(user)){
            user.setPassWord(SecureUtil.md5(user.getPassWord())) ;
            return userDao.save(user) ;
        }
        return Boolean.FALSE ;
    }

    /**
     * 用户登录
     * @param userVO 用户主体VO
     * @since 2021-09-05 16:20
     */
    @Override
    public String login(UserVO userVO) {
        User user = userDao.getByUserName(userVO.getUserName()) ;
        if (ObjectUtil.isNotNull(user)){
            String outPwd = user.getPassWord() ;
            String inPwd = SecureUtil.md5(userVO.getPassWord()) ;
            if (StrUtil.equals(outPwd,inPwd)){
                return jwtOperate.encode(user.getId()) ;
            }
        }
        throw new ExeRep(HttpStatus.HTTP_BAD_REQUEST, "login error");
    }

    /**
     * 根据名称查询用户
     * @param userName 用户名称
     * @since 2021-09-07 21:00
     */
    @Override
    public UserVO getByUserName(String userName) {
        User user = userDao.getByUserName(userName) ;
        if (ObjectUtil.isNotNull(user)){
            return BeanUtil.copyProperties(user,UserVO.class);
        }
        return null;
    }

    /**
     * 根据用户ID查询
     * @param userId 用户ID
     * @since 2021-09-07 21:00
     */
    @Override
    public UserVO getByUserId(Integer userId) {
        User user = userDao.getById(userId) ;
        if (ObjectUtil.isNotNull(user)){
            return BeanUtil.copyProperties(user,UserVO.class);
        }
        return null;
    }

    /**
     * 更新用户
     * @param userVO 用户主体VO
     * @since 2021-09-05 16:20
     */
    @Override
    public Boolean updateUser(UserVO userVO) {
        User user = BeanUtil.copyProperties(userVO, User.class) ;
        if (ObjectUtil.isNotNull(user.getId())){
            return userDao.updateById(user) ;
        }
        return Boolean.FALSE ;
    }

}
