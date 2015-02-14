package com.twitter.meil_mitu.twitter4holo.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Utils {
    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return string;
        }
    }

    public static String toString(long[] ls){
        StringBuilder sb =new StringBuilder();
        for(long l:ls){
            if(sb.length()>0){
                sb.append(',');
            }
            sb.append(l);
        }
        return sb.toString();
    }

    public static String toString(String[] ss){
        StringBuilder sb =new StringBuilder();
        for(String s:ss){
            if(sb.length()>0){
                sb.append(',');
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public static String replaceLine(String s){
        StringBuilder sb = new StringBuilder();
       for(int i=0;i<s.length();i++){
           char c = s.charAt(i);
           if(c!='\n'){
               sb.append(c);
           }
       }
        return sb.toString();
    }

    public static String base64Encode(byte[] bs){
        // https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/util/Base64.java
        // Line 674
        return replaceLine(new String(Base64.encode(bs, Base64.DEFAULT)));
    }
}
