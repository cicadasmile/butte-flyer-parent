package com.butte.flyer.facade.controller;

import com.butte.base.entity.Rep;
import com.butte.base.page.PageVO;
import com.butte.flyer.quartz.bean.POJO.JobQy;
import com.butte.flyer.quartz.bean.VO.QuartzJobVO;
import com.butte.flyer.quartz.bean.VO.QuartzLogVO;
import com.butte.flyer.quartz.feign.QuartzFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 定时任务
 * @author 公众号:知了一笑
 * @since 2021-08-19 22:09
 */
@Api(value = "定时任务管理")
@RequestMapping("/quart")
@RestController
public class QuartzController {

    @Resource
    private QuartzFeign quartzFeign ;

    @ApiOperation("主键查询实体")
    @GetMapping("/job/{id}")
    public Rep<QuartzJobVO> getById(@PathVariable Integer id){
        return quartzFeign.getById(id) ;
    }

    @ApiOperation("分页查询")
    @PostMapping("/job/pageList")
    public Rep<PageVO<QuartzJobVO>> pageList (@RequestBody JobQy jobQy){
        return quartzFeign.pageList(jobQy) ;
    }

    @ApiOperation("新增任务")
    @PostMapping("/job")
    public Rep<Integer> insert(@RequestBody QuartzJobVO jobVO){
        return quartzFeign.insert(jobVO) ;
    }

    @ApiOperation("更新任务")
    @PutMapping("/job")
    public Rep<Integer> update(@RequestBody QuartzJobVO jobVO){
        return quartzFeign.update(jobVO) ;
    }

    @ApiOperation("停止任务")
    @PutMapping("/job/pause/{id}")
    public Rep<Void> pause(@PathVariable Integer id) {
        return quartzFeign.pause(id) ;
    }

    @ApiOperation("恢复任务")
    @PutMapping("/job/resume/{id}")
    public Rep<Void> resume(@PathVariable Integer id) {
        return quartzFeign.resume(id) ;
    }

    @ApiOperation("执行一次")
    @GetMapping("/job/runOnce/{id}")
    public Rep<Void> runOnce(@PathVariable Integer id) {
        return quartzFeign.runOnce(id) ;
    }

    @ApiOperation("执行日志分页查询")
    @PostMapping("/job/jobLogList")
    public Rep<PageVO<QuartzLogVO>> jobLogList (@RequestBody JobQy jobQy){
        return quartzFeign.jobLogList(jobQy) ;
    }
}
