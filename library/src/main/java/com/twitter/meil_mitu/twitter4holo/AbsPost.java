package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbsPost<T> extends AbsMethod{

    private HashMap<String, File> fmap = new HashMap<String, File>();
    protected AbsOauth Oauth;
    protected T Json;
    protected AbsJsonConverter AbsJson;

    public AbsPost(AbsOauth oauth, T json){
        this.Oauth = oauth;
        if(json instanceof AbsJsonConverter){
            this.Json = json;
            this.AbsJson = (AbsJsonConverter) json;
        }else{
            throw new IncorrectException("json not instanceof AbsJsonConverter");
        }
    }

    @Override
    public String method(){
        return "POST";
    }

    protected void addFileParam(String name, File value){
        fmap.put(name, value);
    }

    protected void removeFileValue(String name){
        fmap.remove(name);
    }

    public Set<Map.Entry<String, File>> getFileParam(){
        return fmap.entrySet();
    }

    public int fileSize(){
        return fmap.size();
    }


}
