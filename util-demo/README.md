1.java 8 时间类
    DateTimeFormatter 时间格式转换类
    DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime time3 = LocalDateTime.parse(时间字符串,fa);
    String date = fa.format(nowTime);
    1.1 自定义转换类型
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    1.2 api自带实例
         ISO_LOCAL_DATE             2012-12-02
         ISO_OFFSET_DATE            2011-12-03+01:00
         ISO_DATE          	        2011-12-03+01:00; 2011-12-03
         ISO_LOCAL_TIME             10:15:30
         ISO_OFFSET_TIME            10:15:30+01:00
         ISO_TIME                   10:15:30+01:00; 10:15:30
         ISO_LOCAL_DATE_TIME    	2011-12-03T10:15:30
         ISO_OFFSET_DATE_TIME       2011-12-03T10:15:30+01:00
         ISO_ZONED_DATE_TIME        2011-12-03T10:15:30+01:00[Europe/Paris]
         ISO_DATE_TIME              2011-12-03T10:15:30+01:00[Europe/Paris]
         ISO_ORDINAL_DATE           2012-337
         ISO_WEEK_DATE              2012-W48-6
         ISO_INSTANT                2011-12-03T10:15:30Z
         BASIC_ISO_DATE             20111203
         RFC_1123_DATE_TIME         Tue, 3 Jun 2008 11:05:30 GMT