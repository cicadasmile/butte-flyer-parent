package com.butte.flyer.account.feign;

import com.butte.base.entity.Rep;
import com.butte.flyer.account.beans.VO.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Feign接口
 * @author 公众号:知了一笑
 * @since 2021-09-05 16:01
 */
@FeignClient("account")
public interface UserFeign {

    /**
     * 注册用户
     * @param userVO 用户实体
     * @since 2021-09-07 21:00
     */
    @PostMapping("/user/register")
    Rep<Boolean> register (@RequestBody UserVO userVO) ;

    /**
     * 用户登录
     * @param userVO 用户实体
     * @since 2021-09-07 21:00
     */
    @PostMapping("/user/login")
    Rep<String> login (@RequestBody UserVO userVO) ;

    /**
     * 根据名称查询用户
     * @param userName 用户名称
     * @since 2021-09-07 21:00
     */
    @GetMapping("/user/getByUserName")
    Rep<UserVO> getByUserName (@RequestParam("userName") String userName) ;

    /**
     * 根据用户ID查询
     * @param userId 用户ID
     * @since 2021-09-07 21:00
     */
    @GetMapping("/user/getByUserId")
    Rep<UserVO> getByUserId (@RequestParam("userId") Integer userId) ;
    
    /**
     * 更新用户
     * @param userVO 用户实体
     * @since 2021-09-17 23:23
     */
    @PutMapping("/user/updateUser")
    Rep<Boolean> updateUser (@RequestBody UserVO userVO) ;
}
