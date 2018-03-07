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

    /*
     * 将10 or 13 位时间戳转为时间字符串
     * convert the number 1407449951 1407499055617 to date/time format timestamp
     */
    public static String timestamp2Date(String str_num,String format ) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (str_num.length() == 13) {
            String date = sdf.format(new Date(Long.parseLong(str_num)));
            System.out.printf("timestamp2Date"+ "将13位时间戳:" + str_num + "转化为字符串:", date);
            return date;
        } else {
            String date = sdf.format(new Date(Integer.parseInt(str_num) * 1000L));
            System.out.printf("timestamp2Date" + "将10位时间戳:" + str_num + "转化为字符串:", date);
            return date;
        }
    }

    /**
     * 返回错误消息的JSON字符串
     * @param msg
     * @return
     */
    public final static String error(String msg)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message",msg);
        return jsonObject.toJSONString();
    }

    public static void main(String[] args) {

//        System.out.println((int)(System.currentTimeMillis()/1000));
        String targetFanName="system/cooling6/fan16/ID";
        System.out.println(targetFanName.substring(targetFanName.indexOf("fan") + 3, targetFanName.lastIndexOf("/")));
    }


}
