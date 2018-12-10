package com.toi.demon.schedule.stat;

import com.toi.demon.support.LogThread;
import com.toi.demon.support.NamedThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.toi.demon.constant.Constant.CORE_POOL_SIZE;
import static com.toi.demon.constant.Constant.INITIAL_DELAY;

//@Component
public class ScheduleExecutorService125 {
    //ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(CORE_POOL_SIZE, new NamedThreadFactory("hi-logger-writer", true));

    public void startLog() {
        //ScheduledFuture<?> scheduledFuture = scheduledExecutor.scheduleWithFixedDelay(
        //        new LogThread(125), INITIAL_DELAY, 1000, TimeUnit.MILLISECONDS);
    }
}
