package com.twitter.meil_mitu.twitter4holo.api.media;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;

import java.io.File;

public class MediaAPI extends AbsAPI<ITwitterJsonConverter> {

    public MediaAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public Upload upload(File media){
        return new Upload(Oauth,Json,media);
    }
}
