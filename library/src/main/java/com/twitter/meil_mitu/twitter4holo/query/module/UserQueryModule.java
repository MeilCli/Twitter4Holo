package com.twitter.meil_mitu.twitter4holo.query.module;

import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.query.IQueryModule;

import java.util.HashMap;

public class UserQueryModule implements IQueryModule<User>{

    private String parentName;
    private User object;
    private static HashMap<String, Class> map = new HashMap<String, Class>();

    static{
        map.put("CreatedAt", Long.class);
        map.put("Description", String.class);
        map.put("FavouritesCount", Long.class);
        map.put("FollowersCount", Long.class);
        map.put("FriendsCount", Long.class);
        map.put("ListedCount", Long.class);
        map.put("StatusesCount", Long.class);
        map.put("IsFollowRequestSent", Boolean.class);
        map.put("IsFollowing", Boolean.class);
        map.put("IsProtected", Boolean.class);
        map.put("IsMuting", Boolean.class);
        map.put("IsVerified", Boolean.class);
        map.put("Id", Long.class);
        map.put("Lang", String.class);
        map.put("Location", String.class);
        map.put("Name", String.class);
        map.put("ScreenName", String.class);
        map.put("Url", String.class);
    }

    public UserQueryModule(String parentName){
        this.parentName = parentName;
    }

    @Override
    public String getParentName(){
        return parentName;
    }

    @Override
    public void init(User object){
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
        if(child.equals("Description")){
            return object.UserEntitySupport.Description.PlainText;
        }
        if(child.equals("Lang")){
            return object.Lang;
        }
        if(child.equals("Location")){
            return object.Location;
        }
        if(child.equals("Name")){
            return object.Name;
        }
        if(child.equals("ScreenName")){
            return object.ScreenName;
        }
        if(child.equals("Url")){
            return object.UserEntitySupport.Url.PlainText;
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
        if(child.equals("FavouritesCount")){
            return Long.valueOf(object.FavouritesCount);
        }
        if(child.equals("FollowersCount")){
            return Long.valueOf(object.FollowersCount);
        }
        if(child.equals("FriendsCount")){
            return Long.valueOf(object.FriendsCount);
        }
        if(child.equals("ListedCount")){
            return Long.valueOf(object.ListedCount);
        }
        if(child.equals("StatusesCount")){
            return Long.valueOf(object.StatusesCount);
        }
        if(child.equals("Id")){
            return object.Id;
        }
        return null;
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
        if(child.equals("IsFollowRequestSent")){
            return object.IsFollowRequestSent;
        }
        if(child.equals("IsFollowing")){
            return object.IsFollowing;
        }
        if(child.equals("IsProtected")){
            return object.IsProtected;
        }
        if(child.equals("IsMuting")){
            return object.IsMuting;
        }
        if(child.equals("IsVerified")){
            return object.IsVerified;
        }
        return false;
    }
}
