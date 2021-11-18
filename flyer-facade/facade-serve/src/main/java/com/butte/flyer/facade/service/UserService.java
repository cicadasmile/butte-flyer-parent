package com.butte.flyer.facade.service;

import com.butte.base.entity.Rep;
import com.butte.flyer.account.beans.VO.UserVO;

/**
 * 登录服务层
 * @author 公众号:知了一笑
 * @since 2021-10-31 12:46
 */
public interface UserService {

    /**
     * Facade登录
     * @return java.lang.String
     * @since 2021-10-31 12:49
     */
    Rep<String> login (UserVO userVO) ;

}
