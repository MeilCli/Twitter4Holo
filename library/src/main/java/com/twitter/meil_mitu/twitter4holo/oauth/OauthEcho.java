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
import com.twitter.meil_mitu.twitter4holo.util.Utils;

import java.io.IOException;
import java.net.CookieManager;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

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
                //aclogでエラーになるから前のライブラリから流用する
                //原因は不明
                //builder.addHeader("X-Auth-Service-Provider",Verify.url());
                builder.addHeader("X-Auth-Service-Provider", VerifyCredentialsUrl);
                //builder.addHeader("X-Verify-Credentials-Authorization","OAuth " +createAuthorization(param,false));//realm="http://api.twitter.com/",
                builder.addHeader("X-Verify-Credentials-Authorization", createAuthorization());
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
                //builder.addHeader("X-Auth-Service-Provider",Verify.url());
                builder.addHeader("X-Auth-Service-Provider", VerifyCredentialsUrl);
                //builder.addHeader("X-Verify-Credentials-Authorization","OAuth " +createAuthorization(param,false));//realm="http://api.twitter.com/",
                builder.addHeader("X-Verify-Credentials-Authorization", createAuthorization());
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

    private static final String VerifyCredentialsUrl = "https://api.twitter.com/1.1/account/verify_credentials.json";

    private String makeParam(SortedMap<String, String> params, boolean signature){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> param : params.entrySet()){
            if(sb.length() > 0){
                if(signature == true){
                    sb.append('&');
                }else{
                    sb.append(',');
                }
            }
            sb.append(param.getKey());
            sb.append('=');
            if(signature == false){
                sb.append('"');
            }
            if(signature == true){
                sb.append(param.getValue());
            }else{
                sb.append(Utils.urlEncode(param.getValue()));
            }
            if(signature == false){
                sb.append('"');
            }
        }
        return sb.toString();
    }

    protected String createAuthorization() throws Exception{
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("oauth_consumer_key", ConsumerKey);
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_token", AccessToken);
        params.put("oauth_version", "1.0");
        params.put("oauth_timestamp", String.valueOf((long) (System.currentTimeMillis() / 1000)));
        params.put("oauth_nonce", String.valueOf(Math.random()));

        String text = "GET&" + Utils.urlEncode(VerifyCredentialsUrl) + "&" + Utils.urlEncode(makeParam(params, true));
        String keyText = Utils.urlEncode(ConsumerSecret) + "&" + Utils.urlEncode(AccessTokenSecret);
        SecretKeySpec signingKey = new SecretKeySpec(keyText.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance(signingKey.getAlgorithm());
        mac.init(signingKey);
        byte[] binary = mac.doFinal(text.getBytes());
        String signature = Utils.base64Encode(binary);

        params.put("oauth_signature", signature);

        StringBuilder sb = new StringBuilder();
        sb.append("OAuth realm=");
        sb.append('"');
        sb.append("http://api.twitter.com/");
        sb.append('"');
        sb.append(',');
        sb.append(makeParam(params, false));

        return sb.toString();
    }
}
