package com.twitter.meil_mitu.twitter4holo;

import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.twitter.meil_mitu.twitter4holo.data.Banner;
import com.twitter.meil_mitu.twitter4holo.data.ConfigurationResult;
import com.twitter.meil_mitu.twitter4holo.data.CursorIDs;
import com.twitter.meil_mitu.twitter4holo.data.CursorLists;
import com.twitter.meil_mitu.twitter4holo.data.CursorUsers;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.data.Friendship;
import com.twitter.meil_mitu.twitter4holo.data.IDs;
import com.twitter.meil_mitu.twitter4holo.data.Language;
import com.twitter.meil_mitu.twitter4holo.data.Media;
import com.twitter.meil_mitu.twitter4holo.data.Oauth2Token;
import com.twitter.meil_mitu.twitter4holo.data.OauthRequestToken;
import com.twitter.meil_mitu.twitter4holo.data.OauthToken;
import com.twitter.meil_mitu.twitter4holo.data.OembedStatus;
import com.twitter.meil_mitu.twitter4holo.data.Place;
import com.twitter.meil_mitu.twitter4holo.data.PlaceQuery;
import com.twitter.meil_mitu.twitter4holo.data.PrivacyResult;
import com.twitter.meil_mitu.twitter4holo.data.RateLimit;
import com.twitter.meil_mitu.twitter4holo.data.RateLimitResult;
import com.twitter.meil_mitu.twitter4holo.data.Relationship;
import com.twitter.meil_mitu.twitter4holo.data.SavedSearch;
import com.twitter.meil_mitu.twitter4holo.data.SearchResult;
import com.twitter.meil_mitu.twitter4holo.data.Setting;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.data.Suggestion;
import com.twitter.meil_mitu.twitter4holo.data.SuggestionUser;
import com.twitter.meil_mitu.twitter4holo.data.TosResult;
import com.twitter.meil_mitu.twitter4holo.data.TrendPlace;
import com.twitter.meil_mitu.twitter4holo.data.TrendResult;
import com.twitter.meil_mitu.twitter4holo.data.User;
import com.twitter.meil_mitu.twitter4holo.data.UserList;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public abstract class AbsJsonConverter {

    public Oauth2Token toOauth2Token(Response res)throws Twitter4HoloException{
        return  new Oauth2Token(toJSONObject(toString(res.body())));
    }

    public Oauth2Token toOauth2Token(Response res,String tokenType)throws Twitter4HoloException{
        return  new Oauth2Token(toJSONObject(toString(res.body())),tokenType);
    }

    public OauthRequestToken toOauthRequestToken(Response res)throws Twitter4HoloException{
        return new OauthRequestToken(toString(res.body()));
    }

    public OauthToken toOauthToken(Response res)throws Twitter4HoloException{
        return new OauthToken(toString(res.body()));
    }

    public RateLimit toRateLimit(Response res){
        return new RateLimit(res);
    }

    public abstract ResponseData<RateLimitResult> toRateLimitResultResponseData(Response res)throws Twitter4HoloException;

    public abstract Status toStatus(Response res)throws Twitter4HoloException;

    public abstract ResponseData<Status> toStatusResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<Status> toStatusResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<OembedStatus> toOembedStatusResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseData<CursorIDs> toCursorIDsResponseData(Response res)throws Twitter4HoloException;

    public abstract Media toMedia(Response res)throws Twitter4HoloException;

    public abstract ResponseData<SearchResult> toSearchResultResponseData(Response res)throws Twitter4HoloException;

    public abstract DirectMessage toDirectMessage(Response res)throws Twitter4HoloException;

    public abstract ResponseData<DirectMessage> toDirectMessageResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<DirectMessage> toDirectMessageResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<IDs> toIDsResponseData(Response res)throws Twitter4HoloException;

    public abstract User toUser(Response res)throws Twitter4HoloException;

    public abstract ResponseData<User> toUserResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<User> toUserResponseList(Response res)throws Twitter4HoloException;

    public abstract Relationship toRelationship(Response res)throws Twitter4HoloException;

    public abstract ResponseData<Relationship> toRelationshipResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<Friendship> toFriendshipResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<CursorUsers> toCursorUsersResponseData(Response res)throws Twitter4HoloException;

    public abstract Setting toSetting(Response res)throws Twitter4HoloException;

    public abstract ResponseData<Setting> toSettingResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseData<Banner> toBannerResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<Suggestion> toSuggestionResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<SuggestionUser> toSuggestionUserResponseData(Response res)throws Twitter4HoloException;

    public abstract UserList toUserList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<UserList> toUserListResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<UserList> toUserListResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<CursorLists> toCursorListsResponseData(Response res)throws Twitter4HoloException;

    public abstract SavedSearch toSavedSearch(Response res)throws Twitter4HoloException;

    public abstract ResponseData<SavedSearch> toSavedSearchResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<SavedSearch> toSavedSearchResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<Place> toPlaceResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseData<PlaceQuery> toPlaceQueryResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseData<TrendResult> toTrendResultResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<TrendPlace> toTrendPlaceResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<ConfigurationResult> toConfigurationResultResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseList<Language> toLanguageResponseList(Response res)throws Twitter4HoloException;

    public abstract ResponseData<PrivacyResult> toPrivacyResultResponseData(Response res)throws Twitter4HoloException;

    public abstract ResponseData<TosResult> toTosResultResponseData(Response res)throws Twitter4HoloException;

    protected JSONObject toJSONObject(String res)throws Twitter4HoloException{
        try {
            return new JSONObject(res);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    protected JSONArray toJSONArray(String res)throws Twitter4HoloException{
        try {
            return new JSONArray(res);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    public String toString(ResponseBody body)throws Twitter4HoloException{
        try {
            return body.string();
        } catch (IOException e) {
            e.printStackTrace();
            throw  new Twitter4HoloException(e.getMessage());
        }
    }

}
