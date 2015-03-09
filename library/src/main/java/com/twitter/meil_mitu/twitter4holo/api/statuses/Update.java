package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

public class Update extends AbsPost<ITwitterJsonConverter>{

    public Update(AbsOauth oauth, ITwitterJsonConverter json, String status){
        super(oauth, json);
        addParam("status", status);
    }

    public Update inReplyToStatusId(long inReplyToStatusId){
        addParam("in_reply_to_status_id", inReplyToStatusId);
        return this;
    }

    public Update possiblySensitive(boolean possiblySensitive){
        addParam("possibly_sensitive", possiblySensitive);
        return this;
    }

    public Update latitude(float latitude){
        addParam("lat", latitude);
        return this;
    }

    public Update longitude(float longitude){
        addParam("long", longitude);
        return this;
    }

    public Update placeId(String placeId){
        addParam("place_id", placeId);
        return this;
    }

    public Update displayCoordinates(boolean displayCoordinates){
        addParam("display_coordinates", displayCoordinates);
        return this;
    }

    /**
     * must not use in JsonConverter for User
     */
    public Update trimUser(boolean trimUser){
        addParam("trim_user", trimUser);
        return this;
    }

    public Update mediaIds(long[] mediaIds){
        addParam("media_ids", Utils.toString(mediaIds));
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/statuses/update.json";
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
    public Status call() throws Twitter4HoloException{
        return Json.toStatus(Oauth.post(this));
    }
}
