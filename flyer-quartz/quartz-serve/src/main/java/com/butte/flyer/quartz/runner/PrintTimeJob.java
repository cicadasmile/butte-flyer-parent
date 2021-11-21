package com.butte.flyer.quartz.runner;

import cn.hutool.core.date.DateUtil;
import com.butte.quartz.service.JobRunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 时间打印任务
 * @author 公众号:知了一笑
 * @since 2021-09-30 18:11
 */
@Component("printTimeJob")
public class PrintTimeJob implements JobRunnerService {

    private static final Logger logger = LoggerFactory.getLogger(PrintTimeJob.class);

    @Override
    public void run(String param) {
        logger.info("now time :{}", DateUtil.now());
    }
}
