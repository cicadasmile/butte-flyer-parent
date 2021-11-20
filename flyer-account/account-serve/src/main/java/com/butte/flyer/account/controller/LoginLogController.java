package com.butte.flyer.account.controller;

import com.butte.base.entity.Rep;
import com.butte.flyer.account.beans.VO.LoginLogVO;
import com.butte.flyer.account.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 登录日志接口
 * @author 公众号:知了一笑
 * @since 2021-09-10 20:10
 */
@Api(value = "登录日志管理")
@RestController
public class LoginLogController {

    @Resource
    private LoginLogService loginLogService ;

    @ApiOperation("添加登录日志")
    @PostMapping("/login/log")
    public Rep<Void> addLoginLog (@RequestBody LoginLogVO loginLogVO) {
        loginLogService.addLoginLog(loginLogVO);
        return Rep.okVoid();
    }

}
