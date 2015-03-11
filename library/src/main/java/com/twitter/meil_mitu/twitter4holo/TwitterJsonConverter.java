package com.twitter.meil_mitu.twitter4holo;

import com.squareup.okhttp.Response;
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
import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;

public class TwitterJsonConverter extends AbsJsonConverter implements ITwitterJsonConverter{

    private static TwitterJsonConverter defaultConverter;

    public static TwitterJsonConverter getDefaultConverter(){
        if(defaultConverter == null){
            defaultConverter = new TwitterJsonConverter();
        }
        return defaultConverter;
    }

    @Override
    public Oauth2Token toOauth2Token(Response res) throws Twitter4HoloException{
        return new Oauth2Token(toJSONObject(toString(res.body())));
    }

    @Override
    public Oauth2Token toOauth2Token(Response res, String tokenType) throws Twitter4HoloException{
        return new Oauth2Token(toJSONObject(toString(res.body())), tokenType);
    }

    @Override
    public OauthRequestToken toOauthRequestToken(Response res) throws Twitter4HoloException{
        return new OauthRequestToken(toString(res.body()));
    }

    @Override
    public OauthToken toOauthToken(Response res) throws Twitter4HoloException{
        return new OauthToken(toString(res.body()));
    }

    @Override
    public RateLimit toRateLimit(Response res){
        return new RateLimit(res);
    }

    @Override
    public ResponseData<RateLimitResult> toRateLimitResultResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<RateLimitResult>(new RateLimitResult(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public Status toStatus(Response res) throws Twitter4HoloException{
        return new Status(toJSONObject(toString(res.body())));
    }

    @Override
    public Status toStatus(JSONObject obj) throws Twitter4HoloException{
        return new Status(obj);
    }

    @Override
    public ResponseData<Status> toStatusResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<Status>(toStatus(res), toRateLimit(res));
    }

    @Override
    public ResponseList<Status> toStatusResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<Status> list = new ResponseList<Status>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new Status(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<OembedStatus> toOembedStatusResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<OembedStatus>(new OembedStatus(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseData<CursorIDs> toCursorIDsResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<CursorIDs>(new CursorIDs(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public Media toMedia(Response res) throws Twitter4HoloException{
        return new Media(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<SearchResult> toSearchResultResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<SearchResult>(new SearchResult(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public DirectMessage toDirectMessage(Response res) throws Twitter4HoloException{
        return new DirectMessage(toJSONObject(toString(res.body())));
    }

    @Override
    public DirectMessage toDirectMessage(JSONObject obj) throws Twitter4HoloException{
        return new DirectMessage(obj);
    }

    @Override
    public ResponseData<DirectMessage> toDirectMessageResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<DirectMessage>(new DirectMessage(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseList<DirectMessage> toDirectMessageResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<DirectMessage> list = new ResponseList<DirectMessage>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new DirectMessage(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<IDs> toIDsResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<IDs>(new IDs(toJSONArray(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public User toUser(Response res) throws Twitter4HoloException{
        return new User(toJSONObject(toString(res.body())));
    }

    @Override
    public User toUser(JSONObject obj) throws Twitter4HoloException{
        return new User(obj);
    }

    @Override
    public ResponseData<User> toUserResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<User>(toUser(res), toRateLimit(res));
    }

    @Override
    public ResponseList<User> toUserResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<User> list = new ResponseList<User>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new User(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public Relationship toRelationship(Response res) throws Twitter4HoloException{
        return new Relationship(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<Relationship> toRelationshipResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<Relationship>(toRelationship(res), toRateLimit(res));
    }

    @Override
    public ResponseList<Friendship> toFriendshipResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<Friendship> list = new ResponseList<Friendship>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new Friendship(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<CursorUsers> toCursorUsersResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<CursorUsers>(new CursorUsers(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public Setting toSetting(Response res) throws Twitter4HoloException{
        return new Setting(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<Setting> toSettingResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<Setting>(toSetting(res), toRateLimit(res));
    }

    @Override
    public ResponseData<Banner> toBannerResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<Banner>(new Banner(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseList<Suggestion> toSuggestionResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<Suggestion> list = new ResponseList<Suggestion>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new Suggestion(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<SuggestionUser> toSuggestionUserResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<SuggestionUser>(new SuggestionUser(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public UserList toUserList(Response res) throws Twitter4HoloException{
        return new UserList(toJSONObject(toString(res.body())));
    }

    @Override
    public UserList toUserList(JSONObject obj) throws Twitter4HoloException{
        return new UserList(obj);
    }

    @Override
    public ResponseData<UserList> toUserListResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<UserList>(toUserList(res), toRateLimit(res));
    }

    @Override
    public ResponseList<UserList> toUserListResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<UserList> list = new ResponseList<UserList>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new UserList(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<CursorLists> toCursorListsResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<CursorLists>(new CursorLists(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public SavedSearch toSavedSearch(Response res) throws Twitter4HoloException{
        return new SavedSearch(toJSONObject(toString(res.body())));
    }

    @Override
    public ResponseData<SavedSearch> toSavedSearchResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<SavedSearch>(toSavedSearch(res), toRateLimit(res));
    }

    @Override
    public ResponseList<SavedSearch> toSavedSearchResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<SavedSearch> list = new ResponseList<SavedSearch>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new SavedSearch(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<Place> toPlaceResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<Place>(new Place(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseData<PlaceQuery> toPlaceQueryResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<PlaceQuery>(new PlaceQuery(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseData<TrendResult> toTrendResultResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<TrendResult>(new TrendResult(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseList<TrendPlace> toTrendPlaceResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<TrendPlace> list = new ResponseList<TrendPlace>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new TrendPlace(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<ConfigurationResult> toConfigurationResultResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<ConfigurationResult>(new ConfigurationResult(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseList<Language> toLanguageResponseList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ResponseList<Language> list = new ResponseList<Language>(toRateLimit(res));
        for(int i = 0; i < size; i++){
            try{
                list.add(new Language(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public ResponseData<PrivacyResult> toPrivacyResultResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<PrivacyResult>(new PrivacyResult(toJSONObject(toString(res.body()))), toRateLimit(res));
    }

    @Override
    public ResponseData<TosResult> toTosResultResponseData(Response res) throws Twitter4HoloException{
        return new ResponseData<TosResult>(new TosResult(toJSONObject(toString(res.body()))), toRateLimit(res));
    }
}
