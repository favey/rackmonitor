package com.greywanchuang.rackmonitor.util;

import com.alibaba.fastjson.JSONObject;

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


    /**
     * 用于返回成功标识的json数据
     * @return
     */
    public final static String success()
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message","success");
        return jsonObject.toJSONString();
    }

    public final static String error(String msg)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message",msg);
        return jsonObject.toJSONString();
    }


}
