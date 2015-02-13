package com.twitter.meil_mitu.twitter4holo;

import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.twitter.meil_mitu.twitter4holo.data.CursorIDs;
import com.twitter.meil_mitu.twitter4holo.data.DirectMessage;
import com.twitter.meil_mitu.twitter4holo.data.Media;
import com.twitter.meil_mitu.twitter4holo.data.Oauth2Token;
import com.twitter.meil_mitu.twitter4holo.data.OauthRequestToken;
import com.twitter.meil_mitu.twitter4holo.data.OauthToken;
import com.twitter.meil_mitu.twitter4holo.data.OembedStatus;
import com.twitter.meil_mitu.twitter4holo.data.RateLimit;
import com.twitter.meil_mitu.twitter4holo.data.SearchResult;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.*;

public abstract class AbsJsonConverter {

    public Oauth2Token toOauth2Token(Response res)throws Twitter4HoloException{
        checkError(res);
        return  new Oauth2Token(toJSONObject(toString(res.body())));
    }

    public Oauth2Token toOauth2Token(Response res,String tokenType)throws Twitter4HoloException{
        checkError(res);
        return  new Oauth2Token(toJSONObject(toString(res.body())),tokenType);
    }

    public OauthRequestToken toOauthRequestToken(Response res)throws Twitter4HoloException{
        checkError(res);
        return new OauthRequestToken(toString(res.body()));
    }

    public OauthToken toOauthToken(Response res)throws Twitter4HoloException{
        checkError(res);
        return new OauthToken(toString(res.body()));
    }

    public RateLimit toRateLimit(Response res){
        return new RateLimit(res);
    }

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

    protected void checkError(Response res)throws Twitter4HoloException{
        if(res.isSuccessful()==false){
            try {
                JSONObject obj= new JSONObject(res.body().string());
                if(obj.isNull("errors")==false){
                    JSONArray ar = obj.getJSONArray("errors");
                    if(ar.length()==0){
                        throw  new Twitter4HoloException("errors array size is 0");
                    }
                    String message = getString(ar.getJSONObject(0),"message");
                    message+="\n\n";
                    message +=res.request().headers().toString();
                    message+="\n\n";
                    message+=res.request().toString();
                    int code = getInt(ar.getJSONObject(0),"code");
                    throw  new Twitter4HoloException(message,res.code(),code);
                }else{
                    throw new Twitter4HoloException("some exception");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Twitter4HoloException(e.getMessage());
            }
        }
    }

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

    protected String toString(ResponseBody body)throws Twitter4HoloException{
        try {
            return body.string();
        } catch (IOException e) {
            e.printStackTrace();
            throw  new Twitter4HoloException(e.getMessage());
        }
    }

}
