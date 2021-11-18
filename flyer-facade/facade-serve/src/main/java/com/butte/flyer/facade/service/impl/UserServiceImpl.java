package com.butte.flyer.facade.service.impl;

import com.butte.base.entity.Rep;
import com.butte.core.operate.JwtOperate;
import com.butte.flyer.account.beans.VO.UserVO;
import com.butte.flyer.account.feign.UserFeign;
import com.butte.flyer.facade.service.UserService;
import com.butte.redis.operate.RedisKvOperate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录服务实现层
 * @author 公众号:知了一笑
 * @since 2021-10-31 12:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeign userFeign ;

    @Resource
    private RedisKvOperate redisKvOperate ;

    @Resource
    private JwtOperate jwtOperate ;

    /**
     * Facade登录
     * @return java.lang.String
     * @since 2021-10-31 12:49
     */
    @Override
    public Rep<String> login(UserVO userVO) {
        Rep<String> loginRep = userFeign.login(userVO);
        if (loginRep.isSus()){
            String token = loginRep.getData() ;
            // 缓存token
            String userId = jwtOperate.decode(token);
            redisKvOperate.set(token,userId,3600) ;
            return Rep.ok(token) ;
        }
        return loginRep ;
    }
}
