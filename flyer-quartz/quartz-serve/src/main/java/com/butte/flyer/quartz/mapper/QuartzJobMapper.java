package com.butte.flyer.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.butte.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

}
