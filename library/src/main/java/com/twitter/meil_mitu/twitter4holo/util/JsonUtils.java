package com.twitter.meil_mitu.twitter4holo.util;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class JsonUtils {

    public static JSONObject getJSONObject(JSONObject obj,String name) throws Twitter4HoloException{
        try {
            return obj.getJSONObject(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static JSONArray getJSONArray(JSONObject obj,String name) throws Twitter4HoloException{
        try {
            return obj.getJSONArray(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static Date getDate(JSONObject obj,String name) throws Twitter4HoloException {
        return new Date(getString(obj,name));
    }

    public static String getString(JSONObject obj,String name) throws Twitter4HoloException {
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name+" is null");
        }
        try {
            return obj.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static String getString(JSONObject obj,String name,String def) throws Twitter4HoloException {
        if(obj.isNull(name)){
            return def;
        }
        try {
            return obj.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static boolean getBoolean(JSONObject obj,String name)throws Twitter4HoloException{
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name+" is null");
        }
        try {
            return obj.getBoolean(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static boolean getBoolean(JSONObject obj,String name,boolean def)throws Twitter4HoloException{
        if(obj.isNull(name)){
            return def;
        }
        try {
            return obj.getBoolean(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static int getInt(JSONObject obj,String name) throws Twitter4HoloException {
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name+" is null");
        }
        try {
            return obj.getInt(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static int getInt(JSONObject obj,String name,int def) throws Twitter4HoloException {
        if(obj.isNull(name)){
            return def;
        }
        try {
            return obj.getInt(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static long getLong(JSONObject obj,String name) throws Twitter4HoloException {
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name+" is null");
        }
        try {
            return obj.getLong(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static long getLong(JSONObject obj,String name,long def) throws Twitter4HoloException {
        if(obj.isNull(name)){
            return def;
        }
        try {
            return obj.getLong(name);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static JSONObject getJSONObject(JSONArray ar,int index) throws Twitter4HoloException{
        try {
            return ar.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static String getString(JSONArray ar,int index) throws Twitter4HoloException {
        try {
            return ar.getString(index);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static int getInt(JSONArray ar,int index) throws Twitter4HoloException {
        try {
            return ar.getInt(index);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static long getLong(JSONArray ar,int index) throws Twitter4HoloException {
        try {
            return ar.getLong(index);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }
}
