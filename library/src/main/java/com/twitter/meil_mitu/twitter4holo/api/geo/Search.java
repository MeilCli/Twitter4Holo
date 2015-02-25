package com.twitter.meil_mitu.twitter4holo.api.geo;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.ResponseData;
import com.twitter.meil_mitu.twitter4holo.data.PlaceQuery;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public class Search extends AbsGet {

    public Search(AbsOauth oauth, AbsJsonConverter json) {
        super(oauth, json);
    }

    public Search latitude(String latitude){
        addParam("lat",latitude);
        return this;
    }

    public Search longitude(String longitude){
        addParam("long",longitude);
        return this;
    }

    public Search query(String query){
        addParam("query",query);
        return this;
    }

    public Search ip(String ip){
        addParam("ip",ip);
        return this;
    }

    public Search granularity(String granularity){
        addParam("granularity",granularity);
        return this;
    }

    public Search accuracy(String accuracy){
        addParam("accuracy",accuracy);
        return this;
    }

    public Search maxResults(int maxResults){
        addParam("max_results",maxResults);
        return this;
    }

    public Search containedWithin(String containedWithin){
        addParam("contained_within",containedWithin);
        return this;
    }

    public Search attributeStreetAddress(String attributeStreetAddress){
        addParam("attribute:street_address",attributeStreetAddress);
        return this;
    }

    public Search callback(String callback){
        addParam("callback",callback);
        return this;
    }

    @Override
    public String url() {
        return "https://api.twitter.com/1.1/geo/search.json";
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
    public ResponseData<PlaceQuery> call() throws Twitter4HoloException {
        return Json.toPlaceQueryResponseData(Oauth.get(this));
    }
}
