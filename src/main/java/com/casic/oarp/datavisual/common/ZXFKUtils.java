package com.casic.oarp.datavisual.common;

import com.casic.oarp.datavisual.model.zxfk.ImportanceLevelEnum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ZXFKUtils {

    // 日期的格式
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd/HH:mm:ss";


    /**
     * 判断是否在同一个月里面
     * @param month
     * @param targetMonth
     * @return
     */
    public static boolean isInSameMonth(Date month, Date targetMonth){
        Calendar monthCalendar = Calendar.getInstance();
        monthCalendar.setTime(month);
        Integer nMonth = monthCalendar.get(Calendar.MONTH);

        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(targetMonth);
        Integer nTargetMonth = targetCalendar.get(Calendar.MONTH);

        if (nTargetMonth.equals(nMonth)) {
            return true;
        } else {
            return false;
        }
    }


    /***
     * 当前日期：X年X月X日00:00:00
     * @return
     */
    public static Date nowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 本月1号0点
     * X年X月1日00:00:00
     * @return
     */
    public static Date nowMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static ImportanceLevelEnum getEnumByName(String name) {
        for (ImportanceLevelEnum item: ImportanceLevelEnum.values()) {
            if(item.getName().equals(name)) {
                return item;
            }
        }

        return ImportanceLevelEnum.DEFAULT;
    }

    /**
     * 根据当前的时间进行时间推算
     * @param year 当前年份的差值
     * @param month 当前月份的差值
     * @param day 当前日期的差值
     * @return
     */
    public static Date calcDateByNowDate(Date date, Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static String nowDateTimeStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_PATTERN);
        return simpleDateFormat.format(new Date());
    }
}
