package com.butte.flyer.facade.controller;

import com.butte.base.entity.Rep;
import com.butte.flyer.facade.service.AuthWhiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 公众号:知了一笑
 * @since 2021-09-10 21:34
 */
@Api(value = "白名单授权管理")
@RequestMapping("/auth/white")
@RestController
public class AuthWhiteController {

    @Resource
    private AuthWhiteService authWhiteService ;

    @ApiOperation("授权白名单类型查询")
    @GetMapping("/getByType")
    public Rep<String[]> getByType (@RequestParam("authType") Integer authType){
        return Rep.ok(authWhiteService.getByType(authType)) ;
    }

}
