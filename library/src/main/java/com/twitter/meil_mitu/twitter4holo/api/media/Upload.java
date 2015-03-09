package com.twitter.meil_mitu.twitter4holo.api.media;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.data.Media;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.io.File;

public class Upload extends AbsPost<ITwitterJsonConverter>{

    public Upload(AbsOauth oauth, ITwitterJsonConverter json, File media){
        super(oauth, json);
        addFileParam("media", media);
    }

    @Override
    public String url(){
        return "https://upload.twitter.com/1.1/media/upload.json";
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
    public Media call() throws Twitter4HoloException{
        return Json.toMedia(Oauth.post(this));
    }
}
