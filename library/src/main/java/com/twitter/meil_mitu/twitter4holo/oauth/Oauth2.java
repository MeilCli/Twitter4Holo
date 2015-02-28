package com.twitter.meil_mitu.twitter4holo.oauth;


import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.Twitter4HoloConfig;
import com.twitter.meil_mitu.twitter4holo.TwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.api.oauth2.InvalidateToken;
import com.twitter.meil_mitu.twitter4holo.api.oauth2.Token;
import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;
import java.io.IOException;

public class Oauth2 extends AbsOauth{
    protected String AccessToken;
    protected String TokenType;

    public Oauth2(Twitter4HoloConfig config, String consumerKey, String consumerSecret) {
        super(config, consumerKey, consumerSecret);
    }

    public Oauth2(Twitter4HoloConfig config, String consumerKey, String consumerSecret,String accessToken,String tokenType) {
        super(config, consumerKey, consumerSecret);
        this.AccessToken = accessToken;
        this.TokenType = tokenType;
    }

    @Override
    public Response get(AbsGet param) throws Twitter4HoloException{
        if((param.allowOauthType()&OauthType.Oauth2)==0&&(param.allowOauthType()&OauthType.Oauth2Basic)==0){
            throw new IncorrectException("do not allow OauthType");
        }
        if(AccessToken==null||TokenType==null){
            new Token(this, TwitterJsonConverter.getDefaultConverter()).call();// token is post
        }
        Request.Builder builder = new Request.Builder();
        builder.url(toUrl(param));
        builder.header("User-Agent",Config.getUserAgent());
        if(param.isAuthorization()){
            if((param.allowOauthType()&OauthType.Oauth2Basic)==0){
                builder.addHeader("Authorization",TokenType+" "+AccessToken);
            }else{
                builder.addHeader("Authorization","Basic "+createAuthorization());
            }
        }
        builder.get();
        try {
            Response res= call(builder.build());
            checkError(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Twitter4HoloException(e.getMessage());
        } catch (Twitter4HoloException e){
            if(e.getErrorCode()==89){
                new InvalidateToken(this, TwitterJsonConverter.getDefaultConverter()).call();
                return get(param);
            }
            throw e;
        }
    }

    @Override
    public Response post(AbsPost param) throws Twitter4HoloException{
        if((param.allowOauthType()&OauthType.Oauth2)==0&&(param.allowOauthType()&OauthType.Oauth2Basic)==0){
            throw new IncorrectException("do not allow OauthType");
        }
        Request.Builder builder = new Request.Builder();
        builder.url(param.url());
        builder.header("User-Agent",Config.getUserAgent());
        if(param.isAuthorization()){
            if((param.allowOauthType()&OauthType.Oauth2Basic)==0){
                builder.addHeader("Authorization",TokenType+" "+AccessToken);
            }else{
                builder.addHeader("Authorization","Basic "+createAuthorization());
            }
        }
        builder.post(toBody(param));
        try {
            Response res= call(builder.build());
            checkError(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            throw  new Twitter4HoloException(e.getMessage());
        }
    }

    protected String createAuthorization() {
        String encodedConsumerKey = Utils.urlEncode(ConsumerKey);
        String encodedConsumerSecret = Utils.urlEncode(ConsumerSecret);
        String bearer = encodedConsumerKey + ":" + encodedConsumerSecret;
        return Utils.base64Encode(bearer.getBytes());
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public String getTokenType() {
        return TokenType;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }
}
