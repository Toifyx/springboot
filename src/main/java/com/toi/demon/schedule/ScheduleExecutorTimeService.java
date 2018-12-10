package com.toi.demon.schedule;

import com.toi.demon.support.ConfigData;
import com.toi.demon.support.NamedThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.toi.demon.constant.Constant.CORE_POOL_SIZE;

@Component
public class ScheduleExecutorTimeService {

    private final static long DELAY = 30000;

    ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(CORE_POOL_SIZE, new NamedThreadFactory("hi-logger-writer", true));

    ScheduledFuture scheduledFuture;

    public void createTimeSchedule() {
        if (this.scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        this.scheduledFuture = scheduledExecutor.scheduleWithFixedDelay(
                new TimeThread(), DELAY, DELAY, TimeUnit.MILLISECONDS);
    }

    class TimeThread implements Runnable {

        private int maxCountNum;

        private TimeThread() {
            this.maxCountNum = ConfigData.getMaxCountNum();
        }

        @Override
        public void run() {
            ConfigData.setMaxCountNum(maxCountNum);
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
        }
    }


}
