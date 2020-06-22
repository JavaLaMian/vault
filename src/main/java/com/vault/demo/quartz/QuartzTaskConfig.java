package com.vault.demo.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling//激活自动调度
public class QuartzTaskConfig {
    @Bean
    public JobDetail myJobDetail(){
        JobDetail jobDetail = (JobDetail) JobBuilder.newJob(QuartzTask.class)
                .withIdentity("myjob1")//设置组名
                //jobDateMap可以给任务execute传递参数
//                .usingJobData("job_parm","job_parm1")
                .storeDurably()
                .build();
        return jobDetail;
    }
//    @Bean
//    public Trigger myTrigger(){
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(myJobDetail())//把任务详情设置给触发器
//                .withIdentity("myTrigger","myTiggerGroup1")
//                .startNow()//马上开始
////                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()) //可以直接换成ConTrigger
////                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))//直接写表单式 每5分钟执行一次
////                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))//直接写表单式 每5分钟执行一次
//                .build();
//        return trigger;
//    }
}
