package com.butte.flyer.account.service;

import com.butte.flyer.account.beans.VO.UserVO;

/**
 * 用户服务层
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:34
 */
public interface UserService {

    /**
     * 用户注册
     * @param userVO 用户主体VO
     * @since 2021-09-05 16:20
     */
    Boolean register (UserVO userVO) ;

    /**
     * 用户登录
     * @param userVO 用户主体VO
     * @since 2021-09-05 16:20
     */
    String login (UserVO userVO) ;

    /**
     * 根据名称查询用户
     * @param userName 用户名称
     * @since 2021-09-07 21:00
     */
    UserVO getByUserName (String userName) ;

    /**
     * 根据用户ID查询
     * @param userId 用户ID
     * @since 2021-09-07 21:00
     */
    UserVO getByUserId (Integer userId) ;

    /**
     * 更新用户
     * @param userVO 用户主体VO
     * @since 2021-09-05 16:20
     */
    Boolean updateUser(UserVO userVO);
}
