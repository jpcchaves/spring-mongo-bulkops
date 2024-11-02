package com.mongobulk.bulkops.utils;

public class TimeUtils {

    public static long calcDuration(long startTime, long endTime) {

        return endTime - startTime;
    }

    public static long calcDurationInSeconds(long startTime, long endTime) {

        long durationInMillis = calcDuration(startTime, endTime);

        return (long) (durationInMillis / 1000.0);

    }

}
