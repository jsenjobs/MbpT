package com.jsen.test.service.impl;

import com.jsen.test.entity.Quartzjob;
import com.jsen.test.jobhandler.quartz.dy.sk.IJob;
import com.jsen.test.mapper.QuartzjobMapper;
import com.jsen.test.service.QuartzjobService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import com.xxl.job.core.executor.XxlJobExecutor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-03-30
 */
@Service
public class QuartzjobServiceImpl extends ServiceImpl<QuartzjobMapper, Quartzjob> implements QuartzjobService {
    private static final Logger logger = LoggerFactory.getLogger(QuartzjobServiceImpl.class);
    @Autowired
    private Scheduler scheduler;

    @Override
    public ResponseBase addJob(Quartzjob quartzjob) {
        try {
            JobDetail jobDetail = new JobDetail(quartzjob.getJobName(), quartzjob.getJobGroup(), Class.forName(quartzjob.getJobClazz()));
            // JobBuilder.newJob((Class<? extends Job>) Class.forName(quartzjob.getJobClazz())).withIdentity(quartzjob.getJobName(), quartzjob.getJobGroup()).build();

            CronTrigger trigger = new CronTrigger(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            trigger.setCronExpression(quartzjob.getCron());
            /*
            TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
            triggerTriggerBuilder.startNow();
            triggerTriggerBuilder.withIdentity(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            // 触发器时间
            triggerTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(quartzjob.getCron()));
            Trigger trigger = triggerTriggerBuilder.build();
            */

            scheduler.scheduleJob(jobDetail, trigger);


            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (ClassNotFoundException | SchedulerException | ParseException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase addJob(Quartzjob quartzjob, IJob iJob) {
        try {
            JobDetail jobDetail = new JobDetail(quartzjob.getJobName(), quartzjob.getJobGroup(), Class.forName(quartzjob.getJobClazz()));
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("ijob", iJob);
            jobDetail.setJobDataMap(jobDataMap);

            CronTrigger trigger = new CronTrigger(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            trigger.setCronExpression(quartzjob.getCron());

            scheduler.scheduleJob(jobDetail, trigger);


            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (ClassNotFoundException | SchedulerException | ParseException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase updateJob(Quartzjob quartzjob) {
        try {
            // TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            // CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());

            if (trigger == null) {
                return ResponseBase.create().code(1).msg("trigger with key:" + quartzjob.getTriggerName() + quartzjob.getTriggerGroup() + " is not exist.");
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(quartzjob.getCron())) {
                trigger = new CronTrigger(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
                trigger.setCronExpression(quartzjob.getCron());
                trigger.setJobName(quartzjob.getJobName());
                trigger.setJobGroup(quartzjob.getJobGroup());

                /*
                TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
                triggerTriggerBuilder.startNow();
                triggerTriggerBuilder.withIdentity(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
                // 触发器时间
                triggerTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(quartzjob.getCron()));
                trigger = (CronTrigger)triggerTriggerBuilder.build();

                scheduler.rescheduleJob(triggerKey, trigger);
                */

                scheduler.rescheduleJob(quartzjob.getTriggerName(), quartzjob.getTriggerGroup(), trigger);
                // way 2 delete add
                // removeJob(quartzjob);
                // addJob(quartzjob);

            }
        } catch (SchedulerException | ParseException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase removeJob(Quartzjob quartzjob) {
        try {
            /*
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());

            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(quartzjob.getJobName(), quartzjob.getJobGroup()));*/

            scheduler.pauseTrigger(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            scheduler.pauseJob(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            scheduler.unscheduleJob(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            scheduler.deleteJob(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase pauseJob(Quartzjob quartzjob) {
        try {
            /*
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());

            scheduler.pauseTrigger(triggerKey);
            scheduler.pauseJob(JobKey.jobKey(quartzjob.getJobName(), quartzjob.getJobGroup()));
            */

            scheduler.pauseTrigger(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            scheduler.pauseJob(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase resumeJob(Quartzjob quartzjob) {
        try {
            /*
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());

            scheduler.resumeTrigger(triggerKey);
            scheduler.resumeJob(JobKey.jobKey(quartzjob.getJobName(), quartzjob.getJobGroup()));
            */

            scheduler.resumeTrigger(quartzjob.getTriggerName(), quartzjob.getTriggerGroup());
            scheduler.resumeJob(quartzjob.getJobName(), quartzjob.getJobGroup());
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase resumeAllJob() {
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase pauseAllJob() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

    @Override
    public ResponseBase shutdown() {
        // shutdown后无法再使用
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseBase.create().code(1).msg(e.getMessage());
        }
        return ResponseBase.create().code(0);
    }

}
