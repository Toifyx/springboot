package com.toi.demon.support;

public class ConfigData {

    private static int maxCountNum = 100;

    private static int maxThreadNum = 25;

    public static int getMaxCountNum() {
        return maxCountNum;
    }

    public static void setMaxCountNum(int maxCountNum) {
        ConfigData.maxCountNum = maxCountNum;
    }

    public static int getMaxThreadNum() {
        return maxThreadNum;
    }

    public static void setMaxThreadNum(int maxThreadNum) {
        ConfigData.maxThreadNum = maxThreadNum;
    }
}
