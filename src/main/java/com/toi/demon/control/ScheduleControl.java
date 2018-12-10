package com.toi.demon.control;

import com.toi.demon.schedule.ScheduleExecutorLogService;
import com.toi.demon.schedule.ScheduleExecutorTimeService;
import com.toi.demon.support.ConfigData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ScheduleControl {

    @Resource
    private ScheduleExecutorLogService scheduleExecutorService;

    @Resource
    private ScheduleExecutorTimeService scheduleExecutorTimeService;

    @RequestMapping("initSchedule/{maxCharLen}/{delay}")
    public String initSchedule(@PathVariable("maxCharLen") String maxCharLen, @PathVariable("delay") String delay) {
        scheduleExecutorService.startLog(Integer.parseInt(maxCharLen), Integer.parseInt(delay));
        return "success";
    }

    @RequestMapping("cancelSchedule")
    public String cancelSchedule() {
        scheduleExecutorService.cancel();
        return "success";
    }

    @RequestMapping("setMaxCountNum/{maxCountNum}")
    public String setMaxCountNum(@PathVariable("maxCountNum") String maxCountNum) {
        ConfigData.setMaxCountNum(Integer.parseInt(maxCountNum));
        return "success";
    }

    @RequestMapping("setMaxCountNum/{maxThreadNum}")
    public String setMaxThreadNum(@PathVariable("maxThreadNum") String maxThreadNum) {
        ConfigData.setMaxThreadNum(Integer.parseInt(maxThreadNum));
        return "success";
    }

    @RequestMapping("increase/{times}")
    public String increase(@PathVariable("times") String times) {
        int maxCountNum = ConfigData.getMaxCountNum();
        maxCountNum = Integer.parseInt(times) * maxCountNum;
        ConfigData.setMaxCountNum(maxCountNum);
        scheduleExecutorTimeService.createTimeSchedule();
        return "success";
    }

}
