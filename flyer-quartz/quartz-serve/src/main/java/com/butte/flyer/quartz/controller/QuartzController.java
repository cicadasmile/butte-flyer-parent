package com.butte.flyer.quartz.controller;

import com.butte.base.entity.Rep;
import com.butte.base.page.PageVO;
import com.butte.flyer.quartz.bean.POJO.JobQy;
import com.butte.flyer.quartz.bean.VO.QuartzJobVO;
import com.butte.flyer.quartz.bean.VO.QuartzLogVO;
import com.butte.flyer.quartz.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 定时任务管理
 * @author 公众号:知了一笑
 * @since 2021-08-14 13:46
 */
@Api(value = "定时任务管理")
@RestController
@RequestMapping("/quart")
public class QuartzController  {

    @Resource
    private QuartzJobService quartzJobService ;

    @ApiOperation("主键查询实体")
    @GetMapping("/job/{id}")
    public Rep<QuartzJobVO> getById(@PathVariable Integer id){
        return Rep.ok(quartzJobService.getById(id)) ;
    }

    @ApiOperation("任务分页查询")
    @PostMapping("/job/pageList")
    public Rep<PageVO<QuartzJobVO>> pageList (@RequestBody JobQy jobQy){
        return Rep.ok(quartzJobService.pageList(jobQy)) ;
    }

    @ApiOperation("新增任务")
    @PostMapping("/job")
    public Rep<Integer> insert(@RequestBody QuartzJobVO jobVO){
        return Rep.ok(quartzJobService.insert(jobVO)) ;
    }

    @ApiOperation("更新任务")
    @PutMapping("/job")
    public Rep<Integer> update(@RequestBody QuartzJobVO jobVO){
        return Rep.ok(quartzJobService.update(jobVO)) ;
    }

    @ApiOperation("停止任务")
    @PutMapping("/job/pause/{id}")
    public Rep<Void> pause(@PathVariable("id") Integer id) {
        quartzJobService.pause(id);
        return Rep.okVoid() ;
    }

    @ApiOperation("恢复任务")
    @PutMapping("/job/resume/{id}")
    public Rep<Void> resume(@PathVariable("id") Integer id) {
        quartzJobService.resume(id) ;
        return Rep.okVoid() ;
    }

    @ApiOperation("执行一次")
    @GetMapping("/job/runOnce/{id}")
    public Rep<Void> runOnce(@PathVariable("id") Integer id) {
        quartzJobService.runOnce(id) ;
        return Rep.okVoid() ;
    }

    @ApiOperation("执行日志分页查询")
    @PostMapping("/job/jobLogList")
    public Rep<PageVO<QuartzLogVO>> jobLogList (@RequestBody JobQy jobQy){
        return Rep.ok(quartzJobService.jobLogList(jobQy)) ;
    }
}
