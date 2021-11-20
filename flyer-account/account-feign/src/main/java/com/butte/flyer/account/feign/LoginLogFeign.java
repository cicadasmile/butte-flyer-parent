package com.butte.flyer.account.feign;

import com.butte.base.entity.Rep;
import com.butte.flyer.account.beans.VO.LoginLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 登录日志Feign接口
 * @author 公众号:知了一笑
 * @since 2021-09-10 20:10
 */
@FeignClient("account")
public interface LoginLogFeign {

    /**
     * 添加登录日志
     * @since 2021-09-10 20:12
     */
    @PostMapping("/login/log")
    Rep<Void> addLoginLog (@RequestBody LoginLogVO loginLogVO) ;

    /**
     * 分析登录日志
     * @since 2021-10-24 20:12
     */
    @GetMapping("/log/statistic")
    Rep<Void> statisticLog () ;

}
