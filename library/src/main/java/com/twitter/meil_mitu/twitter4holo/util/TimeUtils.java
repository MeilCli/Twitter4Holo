package com.twitter.meil_mitu.twitter4holo.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils{

    private static long s = 1000;
    private static long m = 60 * s;
    private static long h = 60 * m;
    private static long d = 24 * h;
    private static long y = 365 * d;

    public static String toTime(Date date){
        return toTime(date.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String toTime(long date){
        long dtime = System.currentTimeMillis() - date;
        String format;
        if(dtime > y){
            format = "yyyy/MM/dd";
        }else if(dtime > d){
            format = "MM/dd HH:mm";
        }else{
            format = "HH:mm:ss";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(format);
        return sdf1.format(date);
    }

    public static String toDetailTime(Date date){
        return toDetailTime(date.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String toDetailTime(long date){
        long dtime = System.currentTimeMillis() - date;
        String format;
        if(dtime > y){
            format = "yyyy/MM/dd HH:mm:ss";
        }else if(dtime > d){
            format = "MM/dd HH:mm:ss";
        }else{
            format = "HH:mm:ss";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(format);
        return sdf1.format(date);
    }
}
