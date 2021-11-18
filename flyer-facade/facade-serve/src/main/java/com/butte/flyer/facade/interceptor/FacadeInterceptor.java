package com.butte.flyer.facade.interceptor;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.butte.base.entity.ExeRep;
import com.butte.core.operate.JwtOperate;
import com.butte.flyer.facade.beans.constant.AuthType;
import com.butte.flyer.facade.service.AuthWhiteService;
import com.butte.redis.operate.RedisKvOperate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Facade拦截器
 * @author 公众号:知了一笑
 * @since 2021-09-28 21:00
 */
@Component
public class FacadeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(FacadeInterceptor.class);

    @Resource
    private AuthWhiteService authWhiteService ;

    @Resource
    private JwtOperate jwtOperate ;

    @Resource
    private RedisKvOperate redisKvOperate ;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)  {

        logger.info("req uri : {}",request.getRequestURI());

        // 白名单校验
        String[] uriArr = authWhiteService.getByType(AuthType.API_AUTH.getType()) ;
        String uri = request.getRequestURI();
        for (String uriWhite:uriArr){
            if (StrUtil.contains(uri,uriWhite)){
                return Boolean.TRUE ;
            }
        }
        // Token校验
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)){
            throw new ExeRep(HttpStatus.UNAUTHORIZED.value(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase()) ;
        } else {
            // 验证Token
            String userId = jwtOperate.decode(token);
            String expireId = redisKvOperate.get(token);
            if (!StrUtil.equals(expireId,userId)){
                throw new ExeRep(HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.getReasonPhrase()) ;
            }
        }
        return Boolean.TRUE ;
    }
}
