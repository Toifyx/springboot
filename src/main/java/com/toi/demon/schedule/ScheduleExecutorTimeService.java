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

    /**
     * 1时(h)=3600000毫秒(ms)
     */
    private final static long DELAY = 3600000;

    private int maxCountNum;

    ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(CORE_POOL_SIZE, new NamedThreadFactory("hi-logger-writer", true));

    ScheduledFuture scheduledFuture;

    public boolean createTimeSchedule() {
        if (this.scheduledFuture != null) {
            return false;
        }
        maxCountNum = ConfigData.getMaxCountNum();;
        this.scheduledFuture = scheduledExecutor.scheduleWithFixedDelay(
                new TimeThread(), DELAY, DELAY, TimeUnit.MILLISECONDS);
        return true;
    }

    public boolean cancelTimeSchedule(){
        if (this.scheduledFuture != null) {
            scheduledFuture.cancel(true);
            ConfigData.setMaxCountNum(maxCountNum);
            scheduledFuture = null;
            return true;
        }
        return false;
    }

    class TimeThread implements Runnable {

        @Override
        public void run() {
            if (scheduledFuture != null) {
                ConfigData.setMaxCountNum(maxCountNum);
                scheduledFuture.cancel(true);
                scheduledFuture = null;
            }
        }
    }


}
