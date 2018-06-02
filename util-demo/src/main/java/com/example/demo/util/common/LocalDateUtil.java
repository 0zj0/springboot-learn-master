package com.example.demo.util.common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Description: Java 8 新特性中时间的使用
 * @Auther: zhangjie
 * @Date: 2018/5/30 11:13
 */
public class LocalDateUtil {

    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取自定义格式的当前时间
     * @param format
     * @return
     */
    public static String now(String format){
        return DateTimeFormatter.ofPattern(format).format(LocalDateTime.now());
    }

    /**
     *  获取默认格式的当前时间
     * @return
     */
    public static String now(){
        return DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT).format(LocalDateTime.now());
    }


    /**
     * 将string类型的时间转换为时间格式
     * @param source
     * @param format
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String source , String format){
        //将string类型的时间（基本符合format格式）的字符串转换为对应format格式的字符串，减少转换失败的情况
        int dateLength=source.length();
        int formatLength=format.length();
        if(dateLength>formatLength){
            source=source.substring(0,formatLength);
        }else if(dateLength<formatLength){
            String appendStr=format.substring(dateLength,formatLength);
            source=source+appendStr.replaceAll("[a-zA-Z]", "0");
        }
        return LocalDateTime.parse(source,DateTimeFormatter.ofPattern(format));
    }

    /**
     * 将时间格式转换为对应的字符串格式
     * @param time
     * @param format
     * @return
     */
    public static String localDateTimeToString(LocalDateTime time , String format){
        return DateTimeFormatter.ofPattern(format).format(time);
    }

    /**
     * 将LocalDateTime 转换为long类型的时间戳
     * @param time
     * @return
     */
    public static Long localDateTimeToTimestamp(LocalDateTime time){
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 获取当前时间并转换为long类型的时间戳格式
     * @return
     */
    public static Long getNowTimestamp(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将string类型的时间转换为long类型的时间戳
     * @param source 需要基本符合yyyy-MM-dd HH:mm:ss格式，只能长点或者短点，格式不能变
     * @return
     */
    public static Long stringToTimestamp(String source){
        LocalDateTime time = stringToLocalDateTime(source, DEFAULT_TIME_FORMAT);
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取time隔days天后的时间
     *      time：2018-01-01 days：3 return 2018-01-04
     * @param time 原时间
     * @param days  增加时间
     * @return
     */
    public static LocalDateTime getLocalDateTimeByPlusDay(LocalDateTime time , long days){
        return time.plusDays(days);
    }

    /**
     * 获取time隔days天后的时间
     *      time：2018-01-01 days：3 return 2017-12-29
     * @param time 原时间
     * @param days  减少时间
     * @return
     */
    public static LocalDateTime getLocalDateTimeByMinusDay(LocalDateTime time , long days){
        return time.minusDays(days);
    }

}
