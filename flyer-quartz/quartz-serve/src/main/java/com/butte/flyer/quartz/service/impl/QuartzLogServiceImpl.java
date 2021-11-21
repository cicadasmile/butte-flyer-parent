package com.butte.flyer.quartz.service.impl;

import com.butte.flyer.quartz.mapper.QuartzLogMapper;
import com.butte.quartz.entity.QuartzLog;
import com.butte.quartz.service.QuartzLogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("quartzLogService")
public class QuartzLogServiceImpl implements QuartzLogService {

    @Resource
    private QuartzLogMapper quartzLogMapper ;

    @Override
    public Integer insert(QuartzLog quartzLog) {
        return quartzLogMapper.insert(quartzLog);
    }
}
