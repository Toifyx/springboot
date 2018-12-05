package com.toi.demon.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoggerControl {

    @Resource
    private CountLogger countLogger;

    private static Logger logger = LoggerFactory.getLogger(LoggerControl.class);

    @RequestMapping("/logger")
    @ResponseBody
    public String logTimes(@RequestParam(required = false) int logTimes) {
        countLogger.initStartTime();
        for (int i = 0; i < logTimes; i++) {
            logger.error("logger,{}", i);
        }
        countLogger.initEndTime();
        countLogger.logCostTime();
        return "hi!";
    }
}
