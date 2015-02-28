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
