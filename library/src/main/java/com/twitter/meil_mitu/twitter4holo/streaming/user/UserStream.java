package com.twitter.meil_mitu.twitter4holo.streaming.user;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.streaming.IStreamListener;
import com.twitter.meil_mitu.twitter4holo.streaming.IStreamParam;
import com.twitter.meil_mitu.twitter4holo.streaming.MessageType;
import com.twitter.meil_mitu.twitter4holo.streaming.Stream;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONObject;

public class UserStream extends AbsGet<ITwitterJsonConverter> implements IStreamParam{

    private IStreamListener streamListener;
    private IUserStreamListener userStreamListener;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static final String WithUser = "user";
    public static final String WithFollowings = "followings";
    public static final String RepliesAll = "all";
    private static final String source = "source";
    private static final String target = "target";
    private static final String targetObject = "target_object";

    public UserStream(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    /**
     * should not use this
     */
    public UserStream delimited(boolean delimited){
        addParam("delimited", delimited);
        return this;
    }

    public UserStream stallWarnings(boolean stallWarnings){
        addParam("stall_warnings", stallWarnings);
        return this;
    }

    public UserStream with(String with){
        addParam("with", with);
        return this;
    }

    public UserStream replies(String replies){
        addParam("replies", replies);
        return this;
    }

    public UserStream track(String[] track){
        addParam("track", Utils.toString(track));
        return this;
    }

    public UserStream locations(String[] locations){
        addParam("locations", Utils.toString(locations));
        return this;
    }

    public UserStream stringifyFriendIds(boolean stringifyFriendIds){
        addParam("stringify_friend_ids", stringifyFriendIds);
        return this;
    }

    public UserStream setStreamListener(IStreamListener streamListener){
        if(streamListener == null){
            return this;
        }
        this.streamListener = streamListener;
        return this;
    }

    public UserStream setUserStreamListener(IUserStreamListener userStreamListener){
        if(userStreamListener == null){
            return this;
        }
        this.userStreamListener = userStreamListener;
        return this;
    }

    @Override
    public String url(){
        return "https://userstream.twitter.com/1.1/user.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public Stream call(){
        return new Stream(this);
    }

    @Override
    public InputStream getInputStream() throws Twitter4HoloException{
        return Oauth.get(this).body().byteStream();
    }

    @Override
    public IStreamListener getStreamListener(){
        return streamListener;
    }

    @Override
    public void onLine(final String line){
        if(userStreamListener == null){
            return;
        }
        executorService.submit(new Runnable(){
            @Override
            public void run(){
                handleLine(line);
            }
        });
    }

    private void handleLine(String line){
        try{
            JSONObject obj = toJSONObject(line);
            int type = MessageType.type(obj);
            switch(type){
                case MessageType.Status:
                    userStreamListener.onStatus(Json.toStatus(obj));
                    break;
                case MessageType.DirectMessage:
                    userStreamListener.onDirectMessage(Json.toDirectMessage(obj.isNull("direct_message") ? obj : getJSONObject(obj, "direct_message")));
                    break;
                case MessageType.Block:
                    userStreamListener.onBlock(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)));
                    break;
                case MessageType.UnBlock:
                    userStreamListener.onUnBlock(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)));
                    break;
                case MessageType.Favorite:
                    userStreamListener.onFavorite(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)), Json.toStatus(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.UnFavorite:
                    userStreamListener.onUnFavorite(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)), Json.toStatus(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.Follow:
                    userStreamListener.onFollow(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)));
                    break;
                case MessageType.UnFollow:
                    userStreamListener.onUnFollow(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)));
                    break;
                case MessageType.ListCreated:
                    userStreamListener.onListCreated(Json.toUser(getJSONObject(obj, source)), Json.toUserList(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.ListDestroyed:
                    userStreamListener.onListDestroyed(Json.toUser(getJSONObject(obj, source)), Json.toUserList(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.ListUpdated:
                    userStreamListener.onListUpdated(Json.toUser(getJSONObject(obj, source)), Json.toUserList(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.ListMemberAdded:
                    userStreamListener.onListMemberAdded(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)), Json.toUserList(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.ListMemberRemoved:
                    userStreamListener.onListMemberRemoved(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)), Json.toUserList(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.ListUserSubscribed:
                    userStreamListener.onListUserSubscribed(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)), Json.toUserList(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.ListUserUnSubscribed:
                    userStreamListener.onListUserUnSubscribed(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)), Json.toUserList(getJSONObject(obj, targetObject)));
                    break;
                case MessageType.UserUpdate:
                    userStreamListener.onUserUpdate(Json.toUser(getJSONObject(obj, source)));
                    break;
                case MessageType.Mute:
                    userStreamListener.onMute(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)));
                    break;
                case MessageType.UnMute:
                    userStreamListener.onUnMute(Json.toUser(getJSONObject(obj, source)), Json.toUser(getJSONObject(obj, target)));
                    break;
                default:
                    userStreamListener.onUnknown(line);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
