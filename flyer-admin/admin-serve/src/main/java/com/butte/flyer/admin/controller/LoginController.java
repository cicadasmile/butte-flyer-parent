package com.butte.flyer.admin.controller;

import com.butte.base.entity.Rep;
import com.butte.flyer.admin.entity.PO.SysUser;
import com.butte.flyer.admin.service.AuthorizeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 用户登录
 * @author 公众号:知了一笑
 * @since 2021-10-16 20:37
 */
@RestController
@RequestMapping("/sys/user")
public class LoginController {

    @Resource
    private AuthorizeService authorizeService ;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Rep<String> login (@RequestBody SysUser user){
        return Rep.ok(authorizeService.login(user)) ;
    }

}
