package com.butte.flyer.facade.config;

import com.butte.flyer.facade.interceptor.FacadeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;

/**
 * 配置文件
 * @author 公众号:知了一笑
 * @since 2021-09-28 23:00
 */
@Configuration
public class FacadeConfig implements WebMvcConfigurer {

    @Resource
    private FacadeInterceptor facadeInterceptor ;

    /**
     * 注册拦截器
     * @since 2021-09-28 23:03
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(facadeInterceptor).addPathPatterns("/**") ;
    }

}
