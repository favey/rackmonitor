package com.greywanchuang.rackmonitor.util;

import java.text.SimpleDateFormat;
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
}
