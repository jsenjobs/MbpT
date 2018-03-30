package com.jsen.test.jobhandler.quartz.dy;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/3/30
 */
public class HelloJob implements BaseJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        LOGGER.error("Hello Job执行时间: " + new Date());

    }
}
