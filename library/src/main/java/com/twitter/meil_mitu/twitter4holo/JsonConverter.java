package com.twitter.meil_mitu.twitter4holo;

import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.data.CursorIDs;
import com.twitter.meil_mitu.twitter4holo.data.CursorUsers;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.data.Friendship;
import com.twitter.meil_mitu.twitter4holo.data.IDs;
import com.twitter.meil_mitu.twitter4holo.data.Media;
import com.twitter.meil_mitu.twitter4holo.data.OembedStatus;
import com.twitter.meil_mitu.twitter4holo.data.Relationship;
import com.twitter.meil_mitu.twitter4holo.data.SearchResult;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public class JsonConverter extends AbsJsonConverter {

    private static JsonConverter defaultConverter;

    public static JsonConverter getDefaultConverter(){
        if(defaultConverter==null){
            defaultConverter=new JsonConverter();
        }
        return defaultConverter;
    }

    @Override
    public Status toStatus(Response res) throws Twitter4HoloException {
        return new Status(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<Status> toStatusResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<Status>(toStatus(res),toRateLimit(res));
    }

    @Override
    public  ResponseList<Status> toStatusResponseList(Response res)throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size=ar.length();
        ResponseList<Status> list = new ResponseList<Status>(toRateLimit(res));
        for(int i=0;i<size;i++){
            try {
                list.add(new Status(getJSONObject(ar,i)));
            }catch (Twitter4HoloException e){
                e.printStackTrace();
                if(Config.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<OembedStatus> toOembedStatusResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<OembedStatus>(new OembedStatus(toJSONObject(toString(res.body()))),toRateLimit(res));
    }

    @Override
    public ResponseData<CursorIDs> toCursorIDsResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<CursorIDs>(new CursorIDs(toJSONObject(toString(res.body()))),toRateLimit(res));
    }

    @Override
    public Media toMedia(Response res) throws Twitter4HoloException {
        return new Media(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<SearchResult> toSearchResultResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<SearchResult>(new SearchResult(toJSONObject(toString(res.body()))),toRateLimit(res));
    }

    @Override
    public DirectMessage toDirectMessage(Response res) throws Twitter4HoloException {
        return new DirectMessage(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<DirectMessage> toDirectMessageResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<DirectMessage>(new DirectMessage(toJSONObject(toString(res.body()))),toRateLimit(res));
    }

    @Override
    public ResponseList<DirectMessage> toDirectMessageResponseList(Response res) throws Twitter4HoloException {
        JSONArray ar = toJSONArray(toString(res.body()));
        int size=ar.length();
        ResponseList<DirectMessage> list = new ResponseList<DirectMessage>(toRateLimit(res));
        for(int i=0;i<size;i++){
            try {
                list.add(new DirectMessage(getJSONObject(ar,i)));
            }catch (Twitter4HoloException e){
                e.printStackTrace();
                if(Config.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<IDs> toIDsResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<IDs>(new IDs(toJSONArray(toString(res.body()))),toRateLimit(res));
    }

    @Override
    public User toUser(Response res) throws Twitter4HoloException {
        return new User(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<User> toUserResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<User>(toUser(res),toRateLimit(res));
    }

    @Override
    public ResponseList<User> toUserResponseList(Response res) throws Twitter4HoloException {
        JSONArray ar = toJSONArray(toString(res.body()));
        int size=ar.length();
        ResponseList<User> list = new ResponseList<User>(toRateLimit(res));
        for(int i=0;i<size;i++){
            try {
                list.add(new User(getJSONObject(ar,i)));
            }catch (Twitter4HoloException e){
                e.printStackTrace();
                if(Config.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public Relationship toRelationship(Response res) throws Twitter4HoloException {
        return new Relationship(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<Relationship> toRelationshipResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<Relationship>(toRelationship(res),toRateLimit(res));
    }

    @Override
    public ResponseList<Friendship> toFriendshipResponseList(Response res) throws Twitter4HoloException {
        JSONArray ar = toJSONArray(toString(res.body()));
        int size=ar.length();
        ResponseList<Friendship> list = new ResponseList<Friendship>(toRateLimit(res));
        for(int i=0;i<size;i++){
            try {
                list.add(new Friendship(getJSONObject(ar,i)));
            }catch (Twitter4HoloException e){
                e.printStackTrace();
                if(Config.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<CursorUsers> toCursorUsersResponseData(Response res) throws Twitter4HoloException {
        return new ResponseData<CursorUsers>(new CursorUsers(toJSONObject(toString(res.body()))),toRateLimit(res));
    }
}
