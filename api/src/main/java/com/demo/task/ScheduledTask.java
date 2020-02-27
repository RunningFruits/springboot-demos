package com.demo.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    Log log = LogFactory.getLog(ScheduledTask.class);


    @Scheduled(cron = "0 */30 * * * ?")
    public void afterOrderTask() {
    }

}
