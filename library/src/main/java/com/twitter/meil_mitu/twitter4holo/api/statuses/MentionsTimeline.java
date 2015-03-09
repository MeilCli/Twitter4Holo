package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseList;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class MentionsTimeline extends AbsGet<ITwitterJsonConverter>{

    public MentionsTimeline(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public MentionsTimeline count(int count){
        addParam("count", count);
        return this;
    }

    public MentionsTimeline sinceId(long sinceId){
        addParam("since_id", sinceId);
        return this;
    }

    public MentionsTimeline maxId(long maxId){
        addParam("max_id", maxId);
        return this;
    }

    /**
     * must not use in JsonConverter for User
     */
    public MentionsTimeline trimUser(boolean trimUser){
        addParam("trim_user", trimUser);
        return this;
    }

    public MentionsTimeline contributorDetails(boolean contributorDetails){
        addParam("contributor_details", contributorDetails);
        return this;
    }

    public MentionsTimeline includeEntities(boolean includeEntities){
        addParam("include_entities", includeEntities);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/statuses/mentions_timeline.json";
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
    public ResponseList<Status> call() throws Twitter4HoloException{
        return Json.toStatusResponseList(Oauth.get(this));
    }
}
