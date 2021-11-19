package com.butte.flyer.admin.config;

import cn.hutool.json.JSONUtil;
import com.butte.base.entity.Rep;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 鉴权异常响应
 * @author 公众号:知了一笑
 * @since 2021-10-16 14:32
 */
@Component
public class AuthExeHandler implements AuthenticationEntryPoint, Ordered {


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {
        Rep<Void> rep = new Rep<>(HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonStr(rep));
        out.flush();
        out.close();
    }

    @Override
    public int getOrder() {
        return 9999;
    }
}