package com.toi.demon.control;

import com.toi.demon.log.CountLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class IoMessageCountTimeControl {

    /**
     * 100字节
     */
    private static String record_100_byte = "Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.";
    /**
     * 200字节
     */
    private static String record_200_byte = "Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.";
    /**
     * 400字节
     */
    private static String record_400_byte = "Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.Performance Testing.";

    @Resource
    private CountLogger countLogger;

    private static Logger logger = LoggerFactory.getLogger(IoMessageCountTimeControl.class);

    @RequestMapping(value = "/logger/{count}/{threadNum}/{recordByte}")
    @ResponseBody
    public String logTimes(@PathVariable String count, @PathVariable("threadNum") String threadNum, @PathVariable("recordByte") String recordByte) throws InterruptedException {


        int inCount = Integer.parseInt(count);
        int inThreadNum = Integer.parseInt(threadNum);
        int inRecordByte = Integer.parseInt(recordByte);
        String recordSize;
        switch (inRecordByte){
            case 200 : recordSize = record_200_byte; break;
            case 400 : recordSize = record_400_byte; break;
            default  : recordSize = record_100_byte;
        }
        final CountDownLatch latch = new CountDownLatch(inThreadNum);
        AtomicInteger messageCount = new AtomicInteger(0);
        countLogger.initStartTime();

        for (int i = 0; i < inThreadNum; i++) {
            new Thread(() -> {
                while (messageCount.get() < inCount) {
                    messageCount.incrementAndGet();
                    logger.info(recordSize);
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        countLogger.initEndTime();

        long costTime = countLogger.getCostTime();
        long throughput = (1000 * messageCount.get() / costTime);
        countLogger.getLogger().error(String.format("messageCount= %05d , threadNum= %05d , costTime= %08d ms, throughput= %08d", messageCount.get(), inThreadNum, costTime, throughput));
        return "success";
    }
}
