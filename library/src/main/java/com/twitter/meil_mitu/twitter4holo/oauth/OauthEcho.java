package com.twitter.meil_mitu.twitter4holo.oauth;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.Twitter4HoloConfig;
import com.twitter.meil_mitu.twitter4holo.TwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.api.account.VerifyCredentials;
import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import java.io.IOException;
import java.net.CookieManager;

public class OauthEcho extends Oauth{

    protected static VerifyCredentials Verify;
    protected IOauthEchoCheck Check;
    protected CookieManager Cookie;

    private static final IOauthEchoCheck defaultCheck = new IOauthEchoCheck(){
        @Override
        public void checkError(Response res) throws Twitter4HoloException{
            if(res.isSuccessful() == false) throw new Twitter4HoloException("not success");
        }
    };

    public OauthEcho(IOauthEchoCheck check, Twitter4HoloConfig config){
        super(false, config, null, null);
        init(check);
    }

    public OauthEcho(IOauthEchoCheck check, Twitter4HoloConfig config, String consumerKey, String consumerSecret){
        super(false, config, consumerKey, consumerSecret);
        init(check);
    }

    public OauthEcho(IOauthEchoCheck check, Twitter4HoloConfig config, String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret){
        super(false, config, consumerKey, consumerSecret, accessToken, accessTokenSecret);
        init(check);
    }

    private void init(IOauthEchoCheck check){
        if(Verify == null){
            Verify = new VerifyCredentials(this, TwitterJsonConverter.getDefaultConverter());
        }
        this.Check = check;
        if(this.Check == null){
            this.Check = defaultCheck;
        }
    }


    @Override
    protected void okhttpSetting(Twitter4HoloConfig config){
        super.okhttpSetting(config);
        if(Cookie == null){
            Cookie = new CookieManager();
        }
        Http.setCookieHandler(Cookie);
    }

    @Override
    public Response get(AbsGet param) throws Twitter4HoloException{
        if((param.allowOauthType() & OauthType.OauthEcho) == 0){
            throw new IncorrectException("do not allow OauthType");
        }
        Request.Builder builder = new Request.Builder();
        builder.url(toUrl(param));
        builder.header("User-Agent", Config.getUserAgent());
        if(param.isAuthorization() && ConsumerKey != null && ConsumerSecret != null && AccessToken != null && AccessTokenSecret != null){
            try{
                builder.addHeader("X-Auth-Service-Provider", Verify.url());
                builder.addHeader("X-Verify-Credentials-Authorization", "OAuth " + createAuthorization(Verify, false));
            }catch(Exception e){
                e.printStackTrace();
                throw new Twitter4HoloException(e.getMessage());
            }
        }
        builder.get();
        try{
            Response res = call(builder.build());
            this.Check.checkError(res);
            return res;
        }catch(IOException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

    @Override
    public Response post(AbsPost param) throws Twitter4HoloException{
        if((param.allowOauthType() & OauthType.OauthEcho) == 0){
            throw new IncorrectException("do not allow OauthType");
        }
        Request.Builder builder = new Request.Builder();
        builder.url(param.url());
        builder.header("User-Agent", Config.getUserAgent());
        if(param.isAuthorization() && ConsumerKey != null && ConsumerSecret != null && AccessToken != null && AccessTokenSecret != null){
            try{
                builder.addHeader("X-Auth-Service-Provider", Verify.url());
                builder.addHeader("X-Verify-Credentials-Authorization", "OAuth " + createAuthorization(Verify, false));
            }catch(Exception e){
                e.printStackTrace();
                throw new Twitter4HoloException(e.getMessage());
            }
        }
        builder.post(toBody(param));
        try{
            Response res = call(builder.build());
            this.Check.checkError(res);
            return res;
        }catch(IOException e){
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        }
    }

}
