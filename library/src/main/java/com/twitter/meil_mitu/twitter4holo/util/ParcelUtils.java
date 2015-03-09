package com.twitter.meil_mitu.twitter4holo.util;

import android.os.Parcel;

public class ParcelUtils{

    public static void writeNullableString(Parcel dest, String value){
        dest.writeByte(value != null ? (byte) 1 : (byte) 0);
        if(value != null){
            dest.writeString(value);
        }
    }

    public static String readNullableString(Parcel in){
        if(in.readByte() == 1){
            return in.readString();
        }else{
            return null;
        }
    }
}
