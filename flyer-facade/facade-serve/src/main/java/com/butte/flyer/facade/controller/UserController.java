package com.butte.flyer.facade.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.butte.base.entity.ExeRep;
import com.butte.base.entity.Rep;
import com.butte.flyer.account.beans.VO.UserVO;
import com.butte.flyer.account.feign.UserFeign;
import com.butte.flyer.facade.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 用户门面接口
 * @author 公众号:知了一笑
 * @since 2021-09-05 16:10
 */
@Api(value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserFeign userFeign ;
    @Resource
    private UserService userService ;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public Rep<Boolean> register (@RequestBody UserVO userVO){
        checkUser(userVO);
        return userFeign.register(userVO) ;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Rep<String> login (@RequestBody UserVO userVO){
        checkUser(userVO);
        return userService.login(userVO) ;
    }

    @ApiOperation("根据用户ID查询")
    @GetMapping("/getByUserId/{userId}")
    public Rep<UserVO> getByUserId (@PathVariable Integer userId){
        return userFeign.getByUserId(userId);
    }

    /**
     * 业务参数校验
     * @author 公众号:知了一笑
     * @since 2021-10-20 18:34
     */
    private void checkUser (UserVO userVO){
        if (ObjectUtil.isNull(userVO.getUserName()) ||
                ObjectUtil.isNull(userVO.getPassWord())){
            throw new ExeRep(HttpStatus.HTTP_BAD_REQUEST, "param error");
        }
    }
}
