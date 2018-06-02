package com.example.demo.test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/5/30 9:12
 */
public class Test {

    @org.junit.Test
    public void dateTest(){
        Instant start = Instant.now();  //2018-05-30T01:17:35.396Z  上午9点
        System.out.println(start);

        LocalDate now = LocalDate.now(); //获取当前日期 2018-05-30
        System.out.println(now);

        LocalDate tommorow = now.plus(1, ChronoUnit.DAYS);  //day+1 明天
        System.out.println(tommorow);

        LocalDate dateInit = LocalDate.of(2018,1,1);        //2018-01-01
        System.out.println(dateInit);

        LocalDate yesterday = dateInit.minus(1, ChronoUnit.DAYS);  //day-1 昨天 2017-12-31
        System.out.println(yesterday);

        //计算两个日期相隔多少天/月/年，对应年份/月份/天数 相减
        Period period=Period.between(yesterday,tommorow);
        System.out.println(period.getDays());   //0
        System.out.println(period.getMonths()); //5

        //计算两个日期相隔多少天/月/年
        long daysDiff = ChronoUnit.DAYS.between(tommorow,yesterday);
        System.out.println(daysDiff);   //-151

        //Duration 是计算时间差，针对时/分/秒/毫秒/纳秒

        LocalTime time = LocalTime.now();   //获取当前时间 09:35:50.940
        System.out.println(time);

        //2017-01-01
        LocalDate newYear = LocalDate.of(2017, 1, 1); //2017-01-01
        System.out.println(newYear);

        LocalDateTime nowTime = LocalDateTime.now();      //本地日期时间 2018-05-30T09:38:14.813
        System.out.println(nowTime);

        ZonedDateTime zoneTime = ZonedDateTime.now();     //带时区的时间 2018-05-30T09:39:37.600+08:00[Asia/Shanghai]
        System.out.println(zoneTime);

        String date = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(nowTime); //2018-5-30 9:30:34
        System.out.println(date);

        String date2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(nowTime);    //2018-05-30 09:31:47
        System.out.println(date2);


        String dateTest = DateTimeFormatter.BASIC_ISO_DATE.format(nowTime);
        System.out.println(dateTest);

        String date3 = "2018-05-30 10:06:03";
        DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time3 = LocalDateTime.parse(date3,fa);
        System.out.println(time3);

    }


}
