package com.butte.flyer.admin.filter;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.butte.core.operate.JwtOperate;
import com.butte.flyer.admin.config.AuthExeHandler;
import com.butte.flyer.admin.constant.ApiConstant;
import com.butte.flyer.admin.entity.VO.AuthUserVO;
import com.butte.flyer.admin.service.LoadUserService;
import com.butte.redis.operate.RedisKvOperate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 门面服务过滤器
 * @author 公众号:知了一笑
 * @since 2021-09-16 20:58
 */
@Slf4j
@Component
public class AdminFilter extends OncePerRequestFilter implements Ordered {

    @Resource
    private JwtOperate jwtOperate ;

    @Resource
    private LoadUserService loadUserService ;

    @Resource
    private RedisKvOperate redisKvOperate ;

    @Resource
    private AuthExeHandler authExeHandler ;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws ServletException, IOException {

        // 获取Token
        String token = request.getHeader("token");

        if (StrUtil.isNotEmpty(token)){
            // 验证Token
            String userId = jwtOperate.decode(token);
            String expireId = redisKvOperate.get(token);
            if (StrUtil.equals(expireId,userId)){
                // 设置用户
                AuthUserVO authUser = loadUserService.loadByUserId(Integer.parseInt(userId));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(authUser, null,new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                authExeHandler.commence(request,response,null);
            }
        } else {
            String uri = request.getRequestURI();
            if (!ArrayUtil.contains(ApiConstant.apiArr,uri)){
                authExeHandler.commence(request,response,null);
            }
        }

        // 请求向下转发
        chain.doFilter(request, response);
    }

    @Override
    public int getOrder() {
        return -1 ;
    }
}
