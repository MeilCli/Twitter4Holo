package com.twitter.meil_mitu.twitter4holo.util;

import com.twitter.meil_mitu.twitter4holo.IObjectConverter;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Iterator;

public class JsonUtils{

    public static Object get(JSONObject obj, String name) throws Twitter4HoloException{
        try{
            return obj.get(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static JSONObject getJSONObject(JSONObject obj, String name) throws Twitter4HoloException{
        try{
            return obj.getJSONObject(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static JSONArray getJSONArray(JSONObject obj, String name) throws Twitter4HoloException{
        try{
            return obj.getJSONArray(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static Date getDate(JSONObject obj, String name) throws Twitter4HoloException{
        return new Date(getString(obj, name));
    }

    public static String getString(JSONObject obj, String name) throws Twitter4HoloException{
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name + " is null");
        }
        try{
            return obj.getString(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static String getString(JSONObject obj, String name, String def) throws Twitter4HoloException{
        if(obj.isNull(name)){
            return def;
        }
        try{
            return obj.getString(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static boolean getBoolean(JSONObject obj, String name) throws Twitter4HoloException{
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name + " is null");
        }
        try{
            return obj.getBoolean(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static boolean getBoolean(JSONObject obj, String name, boolean def) throws Twitter4HoloException{
        if(obj.isNull(name)){
            return def;
        }
        try{
            return obj.getBoolean(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static int getInt(JSONObject obj, String name) throws Twitter4HoloException{
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name + " is null");
        }
        try{
            return obj.getInt(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static int getInt(JSONObject obj, String name, int def) throws Twitter4HoloException{
        if(obj.isNull(name)){
            return def;
        }
        try{
            return obj.getInt(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static long getLong(JSONObject obj, String name) throws Twitter4HoloException{
        if(obj.isNull(name)){
            throw new Twitter4HoloException(name + " is null");
        }
        try{
            return obj.getLong(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static long getLong(JSONObject obj, String name, long def) throws Twitter4HoloException{
        if(obj.isNull(name)){
            return def;
        }
        try{
            return obj.getLong(name);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putInt(JSONObject obj, String name, int value) throws Twitter4HoloException{
        try{
            obj.put(name, value);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putLong(JSONObject obj, String name, long value) throws Twitter4HoloException{
        try{
            obj.put(name, value);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putString(JSONObject obj, String name, String value) throws Twitter4HoloException{
        try{
            obj.put(name, value);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putJSONObject(JSONObject obj, String name, JSONObject value) throws Twitter4HoloException{
        try{
            obj.put(name, value);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putJSONArray(JSONObject obj, String name, JSONArray value) throws Twitter4HoloException{
        try{
            obj.put(name, value);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putNull(JSONObject obj, String name) throws Twitter4HoloException{
        try{
            obj.put(name, null);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static Iterator<String> keys(JSONObject obj){
        return obj.keys();
    }

    public static JSONObject toJSONObject(String obj) throws Twitter4HoloException{
        try{
            return new JSONObject(obj);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static JSONObject getJSONObject(JSONArray ar, int index) throws Twitter4HoloException{
        try{
            return ar.getJSONObject(index);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static String getString(JSONArray ar, int index) throws Twitter4HoloException{
        try{
            return ar.getString(index);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static int getInt(JSONArray ar, int index) throws Twitter4HoloException{
        try{
            return ar.getInt(index);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static long getLong(JSONArray ar, int index) throws Twitter4HoloException{
        try{
            return ar.getLong(index);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putInt(JSONArray ar, int index, int value) throws Twitter4HoloException{
        try{
            ar.put(index, value);
        }catch(JSONException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public static void putJSONObject(JSONArray ar, JSONObject value) throws Twitter4HoloException{
        ar.put(value);
    }

    public static JSONArray toJSONArray(IObjectConverter[] objcts) throws Twitter4HoloException{
        JSONArray ar = new JSONArray();
        for(IObjectConverter obj : objcts){
            putJSONObject(ar, obj.toJSONObject());
        }
        return ar;
    }
}
