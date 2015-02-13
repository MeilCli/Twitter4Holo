package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbsMethod {
    private HashMap<String,String> map = new HashMap<String,String>();

    protected void addParam(String name,String value){
        map.put(name,value);
    }

    protected void addParam(String name,boolean value){
        map.put(name,String.valueOf(value));
    }

    protected void addParam(String name,int value){
        map.put(name,String.valueOf(value));
    }

    protected void addParam(String name,long value){
        map.put(name,String.valueOf(value));
    }

    protected void addParam(String name,float value){
        map.put(name,String.valueOf(value));
    }

    protected void removeValue(String name){
        map.remove(name);
    }

    public Set<Map.Entry<String,String>> getParam(){
        return map.entrySet();
    }

    public HashMap<String,String> getParamMap(){return map;}

    public int size(){
        return map.size();
    }

    public abstract String method();

    public abstract String url();

    public abstract int allowOauthType();

    public abstract  boolean isAuthorization();

    public abstract Object call() throws Twitter4HoloException;
}
