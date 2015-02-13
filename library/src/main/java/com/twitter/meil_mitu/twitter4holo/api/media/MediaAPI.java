package com.twitter.meil_mitu.twitter4holo.api.media;

import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.api.AbsAPI;

import java.io.File;

public class MediaAPI extends AbsAPI {

    public MediaAPI(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Upload upload(File media){
        return new Upload(Oauth,Json,media);
    }
}
