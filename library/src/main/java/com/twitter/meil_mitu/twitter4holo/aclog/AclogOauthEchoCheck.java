package com.twitter.meil_mitu.twitter4holo.aclog;

import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.oauth.IOauthEchoCheck;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;


public class AclogOauthEchoCheck implements IOauthEchoCheck{

    @Override
    public void checkError(Response res) throws Twitter4HoloException{
        if(res.isSuccessful() == false){
            try{
                JSONObject obj = new JSONObject(res.body().string());
                obj = getJSONObject(obj, "error");
                throw new Twitter4HoloException(getString(obj, "message"));
            }catch(Exception e){
                e.printStackTrace();
                throw new Twitter4HoloException(e.getMessage());
            }
        }
    }
}
