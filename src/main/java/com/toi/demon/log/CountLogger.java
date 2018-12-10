package com.toi.demon.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CountLogger
{
    private static Logger logger = LoggerFactory.getLogger(CountLogger.class);

    long start;

    long end;

    public void initStartTime(){
        start = System.currentTimeMillis();
    }

    public void initEndTime(){
        end = System.currentTimeMillis();
    }

    public long getCostTime() {
        return end - start;
    }

    public Logger getLogger(){
        return logger;
    }

    public void logCostTime(){
        logger.error("cost time is {} , from {} to {}", end - start, start, end);
    }
}
