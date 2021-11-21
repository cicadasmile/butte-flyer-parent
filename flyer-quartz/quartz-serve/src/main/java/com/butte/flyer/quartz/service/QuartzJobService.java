package com.butte.flyer.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.butte.base.page.PageVO;
import com.butte.flyer.quartz.bean.POJO.JobQy;
import com.butte.flyer.quartz.bean.VO.QuartzJobVO;
import com.butte.flyer.quartz.bean.VO.QuartzLogVO;
import com.butte.quartz.entity.QuartzJob;

public interface QuartzJobService extends IService<QuartzJob> {
    /**
     * 主键查询
     */
    QuartzJobVO getById(Integer id);

    /**
     * 分页查询
     */
    PageVO<QuartzJobVO> pageList (JobQy jobQy) ;

    /**
     * 保存
     */
    int insert(QuartzJobVO jobVO);
    /**
     * 更新
     */
    int update(QuartzJobVO jobVO);
    /**
     * 停止
     */
    void pause(Integer id) ;
    /**
     * 恢复
     */
    void resume(Integer id) ;
    /**
     * 执行一次
     */
    void runOnce(Integer id) ;

    /**
     * 执行日志分页查询
     */
    PageVO<QuartzLogVO> jobLogList (JobQy jobQy) ;
}
