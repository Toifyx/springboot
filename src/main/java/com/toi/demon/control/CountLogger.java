package com.toi.demon.control;

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

    public void logCostTime(){
        logger.error("cost time is {} , from {} to {}", end - start, start, end);
    }
}
