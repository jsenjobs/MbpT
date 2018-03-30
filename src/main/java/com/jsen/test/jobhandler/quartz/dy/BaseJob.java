package com.jsen.test.jobhandler.quartz.dy;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/3/30
 */
// @DisallowConcurrentExecution // statefulJob
public interface BaseJob extends Job {
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}