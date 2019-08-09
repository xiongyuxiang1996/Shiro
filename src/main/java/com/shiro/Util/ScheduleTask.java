package com.shiro.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/8
 * @Time 17:44
 * @Description
 */
@Component
@Configuration      // 1.主要用于标记配置类，兼备Component的效果
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask implements SchedulingConfigurer {
    /**
     * 执行定时任务
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(
//                // 1.添加任务内容(Runnable)
//                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
//                // 2.设置执行周期(Trigger)
//                triggerContext -> {
//                    // 2.1 从数据库获取执行周期
//                    String cron = "0/5 * * * * ?";
//                    // 2.2 合法性校验.
//                    if (StringUtils.isEmpty(cron)) {
//                        // Omitted Code ..
//                    }
//                    // 2.3 返回执行周期(Date)
//                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
//                }
//        );
    }
}
