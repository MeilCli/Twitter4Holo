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

public interface ITwitterJsonConverter{

    public Oauth2Token toOauth2Token(Response res) throws Twitter4HoloException;

    public Oauth2Token toOauth2Token(Response res, String tokenType) throws Twitter4HoloException;

    public OauthRequestToken toOauthRequestToken(Response res) throws Twitter4HoloException;

    public OauthToken toOauthToken(Response res) throws Twitter4HoloException;

    public RateLimit toRateLimit(Response res);

    public ResponseData<RateLimitResult> toRateLimitResultResponseData(Response res) throws Twitter4HoloException;

    public Status toStatus(Response res) throws Twitter4HoloException;

    public ResponseData<Status> toStatusResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<Status> toStatusResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<OembedStatus> toOembedStatusResponseData(Response res) throws Twitter4HoloException;

    public ResponseData<CursorIDs> toCursorIDsResponseData(Response res) throws Twitter4HoloException;

    public Media toMedia(Response res) throws Twitter4HoloException;

    public ResponseData<SearchResult> toSearchResultResponseData(Response res) throws Twitter4HoloException;

    public DirectMessage toDirectMessage(Response res) throws Twitter4HoloException;

    public ResponseData<DirectMessage> toDirectMessageResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<DirectMessage> toDirectMessageResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<IDs> toIDsResponseData(Response res) throws Twitter4HoloException;

    public User toUser(Response res) throws Twitter4HoloException;

    public ResponseData<User> toUserResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<User> toUserResponseList(Response res) throws Twitter4HoloException;

    public Relationship toRelationship(Response res) throws Twitter4HoloException;

    public ResponseData<Relationship> toRelationshipResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<Friendship> toFriendshipResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<CursorUsers> toCursorUsersResponseData(Response res) throws Twitter4HoloException;

    public Setting toSetting(Response res) throws Twitter4HoloException;

    public ResponseData<Setting> toSettingResponseData(Response res) throws Twitter4HoloException;

    public ResponseData<Banner> toBannerResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<Suggestion> toSuggestionResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<SuggestionUser> toSuggestionUserResponseData(Response res) throws Twitter4HoloException;

    public UserList toUserList(Response res) throws Twitter4HoloException;

    public ResponseData<UserList> toUserListResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<UserList> toUserListResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<CursorLists> toCursorListsResponseData(Response res) throws Twitter4HoloException;

    public SavedSearch toSavedSearch(Response res) throws Twitter4HoloException;

    public ResponseData<SavedSearch> toSavedSearchResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<SavedSearch> toSavedSearchResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<Place> toPlaceResponseData(Response res) throws Twitter4HoloException;

    public ResponseData<PlaceQuery> toPlaceQueryResponseData(Response res) throws Twitter4HoloException;

    public ResponseData<TrendResult> toTrendResultResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<TrendPlace> toTrendPlaceResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<ConfigurationResult> toConfigurationResultResponseData(Response res) throws Twitter4HoloException;

    public ResponseList<Language> toLanguageResponseList(Response res) throws Twitter4HoloException;

    public ResponseData<PrivacyResult> toPrivacyResultResponseData(Response res) throws Twitter4HoloException;

    public ResponseData<TosResult> toTosResultResponseData(Response res) throws Twitter4HoloException;

}
