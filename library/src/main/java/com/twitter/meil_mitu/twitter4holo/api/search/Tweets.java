package com.twitter.meil_mitu.twitter4holo.api.search;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.SearchResult;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Tweets extends AbsGet<ITwitterJsonConverter>{

    public Tweets(AbsOauth oauth, ITwitterJsonConverter json, String q){
        super(oauth, json);
        addParam("q", q);
    }

    public Tweets geocode(String geocode){
        addParam("geocode", geocode);
        return this;
    }

    public Tweets lang(String lang){
        addParam("lang", lang);
        return this;
    }

    public Tweets locale(String locale){
        addParam("locale", locale);
        return this;
    }

    public Tweets resultType(String resultType){
        addParam("result_type", resultType);
        return this;
    }

    public Tweets count(int count){
        addParam("count", count);
        return this;
    }

    public Tweets until(String until){
        addParam("until", until);
        return this;
    }

    public Tweets sinceId(long sinceId){
        addParam("since_id", sinceId);
        return this;
    }

    public Tweets maxId(long maxId){
        addParam("max_id", maxId);
        return this;
    }

    public Tweets includeEntities(boolean includeEntities){
        addParam("include_entities", includeEntities);
        return this;
    }

    public Tweets callback(String callback){
        addParam("callback", callback);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/search/tweets.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1 | OauthType.Oauth2;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public ResponseData<SearchResult> call() throws Twitter4HoloException{
        return Json.toSearchResultResponseData(Oauth.get(this));
    }
}
