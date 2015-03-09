package com.twitter.meil_mitu.twitter4holo.query.module;

import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.query.IQueryModule;

import java.util.HashMap;

public class StatusQueryModule implements IQueryModule<Status>{

    private String parentName;
    private Status object;
    private static HashMap<String, Class> map = new HashMap<String, Class>();

    static{
        map.put("CreatedAt", Long.class);
        map.put("FavoriteCount", Long.class);
        map.put("RetweetCount", Long.class);
        map.put("IsFavorited", Boolean.class);
        map.put("IsRetweeted", Boolean.class);
        map.put("Id", Long.class);
        map.put("InReplyToStatusId", Long.class);
        map.put("InReplyToUserId", Long.class);
        map.put("InReplyToScreenName", String.class);
        map.put("Lang", String.class);
        map.put("Source", String.class);
        map.put("Text", String.class);
    }

    public StatusQueryModule(String parentName){
        this.parentName = parentName;
    }

    @Override
    public String getParentName(){
        return parentName;
    }

    @Override
    public void init(Status object){
        this.object = object;
    }


    @Override
    public boolean isChild(String child){
        return map.containsKey(child);
    }

    @Override
    public Class getChildClass(String child){
        return map.get(child);
    }

    @Override
    public String getChildString(String child){
        if(object == null){
            return null;
        }
        if(child.equals("InReplyToScreenName")){
            return object.InReplyToScreenName;
        }
        if(child.equals("Lang")){
            return object.Lang;
        }
        if(child.equals("Source")){
            return object.Source;
        }
        if(child.equals("Text")){
            return object.EntitySupport.PlainText;
        }
        return null;
    }

    @Override
    public Long getChildLong(String child){
        if(object == null){
            return -1L;
        }
        if(child.equals("CreatedAt")){
            return object.CreatedAt.getTime();
        }
        if(child.equals("FavoriteCount")){
            return Long.valueOf(object.FavoriteCount);
        }
        if(child.equals("RetweetCount")){
            return Long.valueOf(object.RetweetCount);
        }
        if(child.equals("Id")){
            return object.Id;
        }
        if(child.equals("InReplyToStatusId")){
            return object.InReplyToStatusId;
        }
        if(child.equals("InReplyToUserId")){
            return object.InReplyToUserId;
        }
        return -1L;
    }

    @Override
    public Double getChildDouble(String child){
        return -1.0;
    }

    @Override
    public Boolean getChildBool(String child){
        if(object == null){
            return false;
        }
        if(child.equals("IsFavorited")){
            return object.IsFavorited;
        }
        if(child.equals("IsRetweeted")){
            return object.IsRetweeted;
        }
        return false;
    }
}
