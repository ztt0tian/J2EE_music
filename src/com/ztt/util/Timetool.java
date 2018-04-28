package com.ztt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaotian
 * @date 2018/4/19 10:16
 */
public class Timetool {
    public static Date getTime() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String returnStr  = f.format(date);
        try {
            return f.parse(returnStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
