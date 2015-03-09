package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.OembedStatus;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Oembed extends AbsGet<ITwitterJsonConverter>{

    // url is contain id ,so set substring url to id.
    public Oembed(AbsOauth oauth, ITwitterJsonConverter json, long id){
        super(oauth, json);
        addParam("id", id);
    }

    public Oembed maxwidth(int maxwidth){
        addParam("maxwidth", maxwidth);
        return this;
    }

    public Oembed hideMedia(boolean hideMedia){
        addParam("hide_media", hideMedia);
        return this;
    }

    public Oembed hideThread(boolean hideThread){
        addParam("hide_thread", hideThread);
        return this;
    }

    public Oembed omitScript(boolean omitScript){
        addParam("omit_script", omitScript);
        return this;
    }

    public Oembed align(String align){
        addParam("align", align);
        return this;
    }

    public Oembed related(String related){
        addParam("related", related);
        return this;
    }

    public Oembed lang(String lang){
        addParam("lang", lang);
        return this;
    }

    public Oembed widgetType(String widgetType){
        addParam("widget_type", widgetType);
        return this;
    }

    public Oembed hideTweet(boolean hideTweet){
        addParam("hide_tweet", hideTweet);
        return this;
    }

    @Override
    public String url(){
        return "https://api.twitter.com/1.1/statuses/oembed.json";
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
    public ResponseData<OembedStatus> call() throws Twitter4HoloException{
        return Json.toOembedStatusResponseData(Oauth.get(this));
    }
}
