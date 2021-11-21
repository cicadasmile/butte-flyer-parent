package com.butte.flyer.quartz.feign;

import com.butte.base.entity.Rep;
import com.butte.base.page.PageVO;
import com.butte.flyer.quartz.bean.POJO.JobQy;
import com.butte.flyer.quartz.bean.VO.QuartzJobVO;
import com.butte.flyer.quartz.bean.VO.QuartzLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务Feign接口
 * @author 公众号:知了一笑
 * @since 2021-08-19 22:22
 */
@FeignClient("quartz")
public interface QuartzFeign {

    @GetMapping("/quart/job/{id}")
    Rep<QuartzJobVO> getById(@PathVariable("id") Integer id) ;

    @PostMapping("/quart/job/pageList")
    Rep<PageVO<QuartzJobVO>> pageList (@RequestBody JobQy jobQy) ;

    @PostMapping("/quart/job")
    Rep<Integer> insert(@RequestBody QuartzJobVO jobVO) ;

    @PutMapping("/quart/job")
    Rep<Integer> update(@RequestBody QuartzJobVO jobVO) ;

    @PutMapping("/quart/job/pause/{id}")
    Rep<Void> pause (@PathVariable("id") Integer id);

    @PutMapping("/quart/job/resume/{id}")
    Rep<Void> resume (@PathVariable("id") Integer id);

    @GetMapping("/quart/job/runOnce/{id}")
    Rep<Void> runOnce (@PathVariable("id") Integer id);

    @PostMapping("/quart/job/jobLogList")
    Rep<PageVO<QuartzLogVO>> jobLogList (@RequestBody JobQy jobQy) ;
}
