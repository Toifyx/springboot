package com.toi.demon.schedule;

import com.toi.demon.support.LogThread;
import com.toi.demon.support.NamedThreadFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.toi.demon.constant.Constant.CORE_POOL_SIZE;
import static com.toi.demon.constant.Constant.INITIAL_DELAY;

@Component
public class ScheduleExecutorLogService {

    ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(CORE_POOL_SIZE, new NamedThreadFactory("hi-logger-writer", true));

    private List<ScheduledFuture> scheduledFutureList = Collections.synchronizedList(new ArrayList<>());

    public void startLog(int maxCharLen, long delay) {
        ScheduledFuture<?> scheduledFuture = scheduledExecutor.scheduleWithFixedDelay(
                new LogThread(maxCharLen), INITIAL_DELAY, delay, TimeUnit.MILLISECONDS);
        scheduledFutureList.add(scheduledFuture);
    }

    public void cancel() {
        for (ScheduledFuture scheduledFuture : scheduledFutureList) {
            scheduledFuture.cancel(true);
        }
    }

}
