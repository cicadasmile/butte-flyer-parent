package com.butte.flyer.facade.feign;

import com.butte.base.entity.Rep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 白名单授权服务Feign接口
 * @author 公众号:知了一笑
 * @since 2021-09-10 21:30
 */
@FeignClient("facade")
public interface AuthWhiteFeign {

    /**
     * 授权白名单类型查询
     * @since 2021-09-10 21:36
     */
    @GetMapping("/auth/white/getByType")
    Rep<String[]> getByType (@RequestParam("authType") Integer authType) ;

}
