package com.butte.flyer.admin.service.impl;

import com.butte.core.operate.JwtOperate;
import com.butte.flyer.admin.entity.PO.SysUser;
import com.butte.flyer.admin.entity.VO.AuthUserVO;
import com.butte.flyer.admin.service.AuthorizeService;
import com.butte.redis.operate.RedisKvOperate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 授权服务实现
 * @author 公众号:知了一笑
 * @since 2021-09-04 17:43
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Resource
    private AuthProviderImpl authProvider ;

    @Resource
    private JwtOperate jwtOperate ;

    @Resource
    private RedisKvOperate redisKvOperate ;

    @Override
    public String login(SysUser user) {

        // 构建认证Token
        UsernamePasswordAuthenticationToken authToken = buildAuthToken(user) ;

        // 调用认证流程
        Authentication authentication = authProvider.authenticate(authToken);

        // 返回用户信息
        AuthUserVO authUserVO = (AuthUserVO) authentication.getPrincipal();

        // 生成Token令牌，缓存1小时
        String token = jwtOperate.encode(authUserVO.getId());
        redisKvOperate.set(token,String.valueOf(authUserVO.getId()),3600);

        // 返回Token令牌
        return token;
    }

    /**
     * 构建账户密码认证Token
     * @since 2021-09-07 23:22
     */
    private UsernamePasswordAuthenticationToken buildAuthToken (SysUser user){
        return new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassWord()) ;
    }

}
