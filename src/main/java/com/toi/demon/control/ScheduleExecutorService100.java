package com.toi.demon.control;

import com.toi.demon.support.LogThread;
import com.toi.demon.support.NamedThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduleExecutorService100 {
    ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1, new NamedThreadFactory("hi-logger-writer", true));
    ScheduledFuture<?> scheduledFuture = scheduledExecutor.scheduleWithFixedDelay(
            new LogThread(100), 1000, 1000, TimeUnit.MILLISECONDS);
}
