package com.butte.flyer.account.controller;

import com.butte.base.entity.Rep;
import com.butte.flyer.account.beans.VO.UserVO;
import com.butte.flyer.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口层
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:38
 */
@Api(value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService ;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public Rep<Boolean> register (@RequestBody UserVO userVO){
        return Rep.ok(userService.register(userVO)) ;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Rep<String> login (HttpServletRequest request, @RequestBody UserVO userVO){
        logger.info("req uri : {}",request.getRequestURI());
        return Rep.ok(userService.login(userVO)) ;
    }

    @ApiOperation("根据名称查询用户")
    @GetMapping("/getByUserName")
    public Rep<UserVO> getByUserName (@RequestParam("userName") String userName){
        return Rep.ok(userService.getByUserName(userName)) ;
    }

    @ApiOperation("根据用户ID查询")
    @GetMapping("/getByUserId")
    public Rep<UserVO> getByUserId (@RequestParam("userId") Integer userId){
        return Rep.ok(userService.getByUserId(userId)) ;
    }

    @ApiOperation("更新用户")
    @GetMapping("/updateUser")
    public Rep<Boolean> updateUser (@RequestBody UserVO userVO){
        return Rep.ok(userService.updateUser(userVO)) ;
    }
}
