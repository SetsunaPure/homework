package com.thoughtworks.conference.util;


import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converts {


    /**
     * 日期数字提取
     * @param content
     * @return
     */
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }


    /**
     * 时间格式化
     * 将24小时制改成12小时
     * @param time
     * @return
     */
    public static String formatTime(LocalTime time) {

        String formatTime = "";
        LocalTime afterNoonTime = LocalTime.parse("12:00:00");
        if(time.isBefore(afterNoonTime)){
            formatTime = time.toString()+"AM";
        }else if(time.equals(afterNoonTime)){
            formatTime = time.toString()+"PM";
        }else{
            formatTime = time.plusHours(12).toString()+"PM";
        }
        return formatTime;
    }


}
