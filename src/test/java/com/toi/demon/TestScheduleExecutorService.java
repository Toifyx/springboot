package com.toi.demon;

import com.toi.demon.support.LogThread;
import com.toi.demon.support.NamedThreadFactory;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class TestScheduleExecutorService {
    public static void main(String[] args) {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        System.out.println(Integer.parseInt(name.substring(0, name.indexOf("@"))));
        Runnable thread = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            System.out.println();
        };

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1, new NamedThreadFactory("hi-logger-writer", true));
        ScheduledFuture<?> scheduledFuture = scheduledExecutor.scheduleWithFixedDelay(
                thread, 10000, 15000, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void test() {
        LogThread logThread = new LogThread();
        logThread.run();

        for (int i = 0; i < 1024; i++);
    }
}
