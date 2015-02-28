package com.twitter.meil_mitu.twitter4holo.api.geo;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.Place;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Id extends AbsGet<ITwitterJsonConverter> {

    private String placeId;

    public Id(AbsOauth oauth, ITwitterJsonConverter json,String placeId) {
        super(oauth, json);
        this.placeId=placeId;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/geo/id/"+placeId+".json";
    }

    @Override
    public int allowOauthType() {
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization() {
        return true;
    }

    @Override
    public ResponseData<Place> call() throws Twitter4HoloException {
        return Json.toPlaceResponseData(Oauth.get(this));
    }
}
