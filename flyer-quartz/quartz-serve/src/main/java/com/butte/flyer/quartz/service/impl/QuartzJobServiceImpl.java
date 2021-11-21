package com.butte.flyer.quartz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.butte.base.entity.ExeRep;
import com.butte.base.page.PageVO;
import com.butte.flyer.quartz.bean.POJO.JobQy;
import com.butte.flyer.quartz.bean.VO.QuartzJobVO;
import com.butte.flyer.quartz.bean.VO.QuartzLogVO;
import com.butte.flyer.quartz.mapper.QuartzJobMapper;
import com.butte.flyer.quartz.mapper.QuartzLogMapper;
import com.butte.flyer.quartz.service.QuartzJobService;
import com.butte.quartz.entity.JobState;
import com.butte.quartz.entity.QuartzJob;
import com.butte.quartz.entity.QuartzLog;
import com.butte.quartz.operate.QuartzOperate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper,QuartzJob>
                                  implements QuartzJobService {

    @Resource
    private QuartzJobMapper quartzJobMapper ;

    @Resource
    private QuartzLogMapper quartzLogMapper ;

    @Resource
    private QuartzOperate quartzOperate ;

    /**
     * 初始化加载定时任务
     */
    @PostConstruct
    public void init (){
        LambdaQueryWrapper<QuartzJob> queryWrapper = new LambdaQueryWrapper<>() ;
        List<QuartzJob> jobList = quartzJobMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(jobList)){
            jobList.forEach(quartzJob -> {
                if (quartzJob.getState() == JobState.JOB_RUN.getStatus()){
                    quartzOperate.createJob(quartzJob);
                }
                if (quartzJob.getState() == JobState.JOB_STOP.getStatus()){
                    quartzOperate.checkStop(quartzJob);
                }
            });
        }
    }

    /**
     * 任务主键查询
     * @param id 主键
     * @return com.butte.flyer.quartz.bean.VO.QuartzJobVO
     * @since 2021-08-22 14:07
     */
    @Override
    public QuartzJobVO getById(Integer id) {
        QuartzJob quartzJob = this.baseMapper.selectById(id) ;
        if (ObjectUtil.isNull(quartzJob) || quartzJob.getState()== JobState.JOB_DEL.getStatus()){
            throw new ExeRep(HttpStatus.HTTP_NO_CONTENT,"任务不存在");
        }
        return BeanUtil.copyProperties(quartzJob, QuartzJobVO.class);
    }

    /**
     * 分页查询
     * @param jobQy 查询条件
     * @return com.butte.base.page.PageVO
     * @since 2021-08-22 16:40
     */
    public PageVO<QuartzJobVO> pageList (JobQy jobQy){
        // 参数条件
        LambdaQueryWrapper<QuartzJob> wrapper = new LambdaQueryWrapper<>() ;
        wrapper.eq(ObjectUtil.isNotNull(jobQy.getId()),QuartzJob::getId,jobQy.getId());
        wrapper.eq(ObjectUtil.isNotNull(jobQy.getBeanName()),QuartzJob::getBeanName,jobQy.getBeanName());
        wrapper.orderByDesc(QuartzJob::getId) ;
        // 分页查询
        Page<QuartzJob> iPage = new Page<>(jobQy.getPage(),jobQy.getPageSize()) ;
        Page<QuartzJob> pageData = this.baseMapper.selectPage(iPage,wrapper);
        // 响应处理
        return new PageVO<>(pageData,QuartzJobVO.class) ;
    }

    /**
     * 新增任务
     * @param jobVO 任务主体VO
     * @return int
     * @since 2021-08-22 14:31
     */
    @Override
    public int insert(QuartzJobVO jobVO) {
        QuartzJob quartzJob = BeanUtil.copyProperties(jobVO,QuartzJob.class);
        if (ObjectUtil.isNotNull(quartzJob)){
            int flag = quartzJobMapper.insert(quartzJob);
            if (flag > 0){
                quartzOperate.createJob(quartzJob);
            }
            return flag ;
        }
        return 0 ;
    }

    /**
     * 更新任务
     * @param jobVO 任务主体VO
     * @return int
     * @since 2021-08-22 14:31
     */
    @Override
    public int update(QuartzJobVO jobVO) {
        if (ObjectUtil.isNull(jobVO.getId())){
            throw new ExeRep(HttpStatus.HTTP_NO_CONTENT,"任务ID缺失");
        }
        QuartzJob quartzJob = BeanUtil.copyProperties(jobVO,QuartzJob.class);
        int flag = quartzJobMapper.updateById(quartzJob);
        if (flag > 0){
            quartzOperate.updateJob(quartzJob);
        }
        return flag ;
    }

    /**
     * 暂停任务
     * @param id 任务ID
     * @since 2021-08-22 14:35
     */
    @Override
    public void pause(Integer id) {
        QuartzJob quartzJob = this.baseMapper.selectById(id) ;
        if (ObjectUtil.isNull(quartzJob)){
            throw new ExeRep(HttpStatus.HTTP_NO_CONTENT,"任务不存在");
        }
        quartzJob.setState(JobState.JOB_STOP.getStatus());
        if (quartzJobMapper.updateById(quartzJob)>0){
            quartzOperate.checkStop(quartzJob);
        }
    }

    /**
     * 恢复任务
     * @param id 任务ID
     * @since 2021-08-22 14:42
     */
    @Override
    public void resume(Integer id) {
        QuartzJob quartzJob = this.baseMapper.selectById(id) ;
        if (quartzJob != null){
            quartzJob.setState(JobState.JOB_RUN.getStatus());
            if (quartzJobMapper.updateById(quartzJob)>0){
                quartzOperate.resumeJob(id);
            }
        }
    }

    /**
     * 执行任务一次
     * @param id 任务ID
     * @since 2021-08-22 14:42
     */
    @Override
    public void runOnce(Integer id) {
        QuartzJob quartzJob = this.baseMapper.selectById(id) ;
        if (ObjectUtil.isNull(quartzJob) || quartzJob.getState()== JobState.JOB_DEL.getStatus()){
            throw new ExeRep(HttpStatus.HTTP_NO_CONTENT,"任务不存在");
        }
        quartzOperate.run(quartzJob);
    }

    /**
     * 执行日志分页查询
     * @param jobQy 查询条件
     * @return com.butte.base.page.PageVO
     * @since 2021-08-22 18:17
     */
    @Override
    public PageVO<QuartzLogVO> jobLogList(JobQy jobQy) {
        // 参数条件
        LambdaQueryWrapper<QuartzLog> wrapper = new LambdaQueryWrapper<>() ;
        wrapper.eq(ObjectUtil.isNotNull(jobQy.getId()),QuartzLog::getJobId,jobQy.getId());
        wrapper.eq(ObjectUtil.isNotNull(jobQy.getBeanName()),QuartzLog::getBeanName,jobQy.getBeanName());
        wrapper.orderByDesc(QuartzLog::getId) ;
        // 分页查询
        Page<QuartzLog> iPage = new Page<>(jobQy.getPage(),jobQy.getPageSize()) ;
        Page<QuartzLog> pageData = quartzLogMapper.selectPage(iPage,wrapper);
        // 响应处理
        return new PageVO<>(pageData,QuartzLogVO.class) ;
    }
}
