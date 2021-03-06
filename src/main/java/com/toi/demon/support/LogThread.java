package com.toi.demon.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * thread 10,count 10,15分钟 1M
 * thread 50,count 100, 30分钟 20M
 */
public class LogThread implements Runnable {

    private int maxCharLen;

    private static Logger logger = LoggerFactory.getLogger(LogThread.class);

    private static Random random = new Random();

    char[] defaultValue = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '~', '!', '@', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+'};

    public LogThread(int maxCharLen) {
        this.maxCharLen = maxCharLen;
    }

    public LogThread() {
        maxCharLen = 100;
    }

    @Override
    public void run() {

        // 默认获取打印log数量
        int maxCountNum = ConfigData.getMaxCountNum();
        int threadNum = random.nextInt(ConfigData.getMaxThreadNum());

        final CountDownLatch latch = new CountDownLatch(threadNum);
        AtomicInteger messageCount = new AtomicInteger(0);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                while (messageCount.get() < maxCountNum) {
                    messageCount.incrementAndGet();
                    // 随机打印一百字节
                    char[] chars = new char[maxCharLen];
                    for (int j = 0; j < chars.length; j++) {
                        int a = random.nextInt(defaultValue.length);
                        chars[j] = defaultValue[a];
                    }
                    String str = new String(chars);
                    logger.error(str);
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
