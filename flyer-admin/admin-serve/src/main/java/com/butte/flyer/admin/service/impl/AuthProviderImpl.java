package com.butte.flyer.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.butte.base.entity.ExeRep;
import com.butte.flyer.admin.entity.VO.AuthUserVO;
import com.butte.flyer.admin.service.LoadUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 认证流程服务类
 * @author 公众号:知了一笑
 * @since 2021-09-04 21:01
 */
@Service
public class AuthProviderImpl extends AbstractUserDetailsAuthenticationProvider {

    @Resource
    private LoadUserService loadUserService ;

    @Resource
    private BCryptPasswordEncoder passwordEncoder ;

    /**
     * 身份校验机制
     * @since 2021-09-07 23:26
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authToken)
            throws AuthenticationException {
        if (ObjectUtil.isNull(userDetails)){
            throw new ExeRep(HttpStatus.HTTP_NO_CONTENT,"No Content");
        }
        AuthUserVO authUserVO = (AuthUserVO)userDetails;
        String inPwd = authToken.getCredentials().toString() ;
        if (!passwordEncoder.matches(inPwd,authUserVO.getPassword())) {
            throw new ExeRep(HttpStatus.HTTP_UNAUTHORIZED,"Auth Fail");
        }
    }

    /**
     * 加载认证用户
     * @since 2021-09-07 23:27
     */
    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken authToken)
            throws AuthenticationException {
        return loadUserService.loadUserByUsername(userName);
    }
}
