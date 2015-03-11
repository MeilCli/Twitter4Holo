package com.twitter.meil_mitu.twitter4holo.streaming;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;

public class MessageType{

    public static final int Unknown = 0;
    public static final int Status = 1;
    public static final int DirectMessage = 2;
    public static final int Block = 3;
    public static final int UnBlock = 4;
    public static final int Favorite = 5;
    public static final int UnFavorite = 6;
    public static final int Follow = 7;
    public static final int UnFollow = 8;
    public static final int ListCreated = 9;
    public static final int ListDestroyed = 10;
    public static final int ListUpdated = 11;
    public static final int ListMemberAdded = 12;
    public static final int ListMemberRemoved = 13;
    public static final int ListUserSubscribed = 14;
    public static final int ListUserUnSubscribed = 15;
    public static final int UserUpdate = 16;
    //https://blog.twitter.com/ja/2014/streaming-api-new-features
    public static final int Mute = 17;
    public static final int UnMute = 18;

    public static int type(JSONObject obj){
        if(obj.isNull("sender") == false){
            return MessageType.DirectMessage;
        }else if(obj.isNull("text") == false){
            return MessageType.Status;
        }else if(obj.isNull("direct_message") == false){
            return MessageType.DirectMessage;
        }else if(obj.isNull("event") == false){
            try{
                String event = getString(obj, "event");
                if(event.equals("block")){
                    return MessageType.Block;
                }else if(event.equals("unblock")){
                    return MessageType.UnBlock;
                }else if(event.equals("favorite")){
                    return MessageType.Favorite;
                }else if(event.equals("unfavorite")){
                    return MessageType.UnFavorite;
                }else if(event.equals("follow")){
                    return MessageType.Follow;
                }else if(event.equals("unfollow")){
                    return MessageType.UnFollow;
                }else if(event.equals("list_created")){
                    return MessageType.ListCreated;
                }else if(event.equals("list_destroyed")){
                    return MessageType.ListDestroyed;
                }else if(event.equals("list_updated")){
                    return MessageType.ListUpdated;
                }else if(event.equals("list_member_added")){
                    return MessageType.ListMemberAdded;
                }else if(event.equals("list_member_removed")){
                    return MessageType.ListMemberRemoved;
                }else if(event.equals("list_user_subscribed")){
                    return MessageType.ListUserSubscribed;
                }else if(event.equals("list_user_unsubscribed")){
                    return MessageType.ListUserUnSubscribed;
                }else if(event.equals("user_update")){
                    return MessageType.UserUpdate;
                }else if(event.equals("mute")){
                    return MessageType.Mute;
                }else if(event.equals("unmute")){
                    return MessageType.UnMute;
                }
            }catch(Twitter4HoloException e){
                e.printStackTrace();
            }
        }
        return MessageType.Unknown;
    }
}
