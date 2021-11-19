package com.butte.flyer.admin.config;

import com.butte.flyer.admin.constant.ApiConstant;
import com.butte.flyer.admin.filter.AdminFilter;
import com.butte.flyer.admin.service.LoadUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;

/**
 * 授权配置
 * @author 公众号:知了一笑
 * @since 2021-09-04 16:39
 */
@Configuration
@EnableWebSecurity
public class AuthorizeConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoadUserService loadUserService ;

    /**
     * 核心配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 配置拦截规则
        http.sessionManagement().disable().requestCache().disable()
            .authorizeRequests()
            .antMatchers(ApiConstant.apiArr).permitAll()
            .anyRequest().authenticated().and()
            .formLogin().disable()
            .logout().disable()
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
            .addFilterAt(facadeFilter(), CsrfFilter.class);

    }

    @Bean
    public OncePerRequestFilter facadeFilter() {
        return new AdminFilter();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthExeHandler();
    }

    /**
     * 自定义认证数据源
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(loadUserService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 加密类型
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
