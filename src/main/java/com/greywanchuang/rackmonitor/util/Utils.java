package com.greywanchuang.rackmonitor.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Utils {
    /**
     * 根据时间的纳秒转换日期格式
     * @param timestamp
     * @return
     */
    public final static String dateFommat(int timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(timestamp);
        return simpleDateFormat.format(date);
    }

    public final static String getPreDate(int amount)
    {
        Calendar cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   amount);
        return new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getPreDate(-2));
    }

}
