package com.twitter.meil_mitu.twitter4holo.oauth;


import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsMethod;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.Config;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.exception.IncorrectException;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Oauth extends AbsOauth {

    protected String AccessToken;
    protected String AccessTokenSecret;

    public Oauth(Config config, String consumerKey, String consumerSecret) {
        super(config, consumerKey, consumerSecret);
    }

    public Oauth(Config config, String consumerKey, String consumerSecret,String accessToken,String accessTokenSecret) {
        super(config, consumerKey, consumerSecret);
        this.AccessToken=accessToken;
        this.AccessTokenSecret=accessTokenSecret;
    }

    @Override
    public Response get(AbsGet param) throws Twitter4HoloException {
        if((param.allowOauthType()& OauthType.Oauth1)==0&&(param.allowOauthType()&OauthType.Oauth1RequestToken)==0){
            throw new IncorrectException("do not allow OauthType");
        }
        Request.Builder builder = new Request.Builder();
        builder.url(toUrl(param));
        builder.header("User-Agent",Config.getUserAgent());
        if(param.isAuthorization()) {
            try {
                builder.addHeader("Authorization","OAuth " +createAuthorization(param,false));
            } catch (Exception e) {
                e.printStackTrace();
                throw new Twitter4HoloException(e.getMessage());
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
        }
    }

    @Override
    public Response post(AbsPost param) throws Twitter4HoloException {
        if((param.allowOauthType()& OauthType.Oauth1)==0&&(param.allowOauthType()&OauthType.Oauth1RequestToken)==0){
            throw new IncorrectException("do not allow OauthType");
        }
        Request.Builder builder = new Request.Builder();
        builder.url(param.url());
        builder.header("User-Agent",Config.getUserAgent());
        if(param.isAuthorization()){
            try {
                builder.addHeader("Authorization","OAuth " +createAuthorization(param,param.fileSize()>0));
            } catch (Exception e) {
                e.printStackTrace();
                throw new Twitter4HoloException(e.getMessage());
            }
        }
        if((param.allowOauthType()&OauthType.Oauth1RequestToken)==0){
            builder.post(toBody(param));
        }else{
            builder.post(RequestBody.create(MediaText, ""));
        }
        try {
            Response res= call(builder.build());
            checkError(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            throw  new Twitter4HoloException(e.getMessage());
        }
    }

    protected String makeOauthNonce() {
        return String.valueOf(new Random().nextInt(99999999));
    }

    protected String makeTimestamp(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }

    protected String createAuthorization(AbsMethod method,boolean isMultiPost) throws Exception {
        String nonce = makeOauthNonce();
        String time = makeTimestamp();
        String callback;
        if((method.allowOauthType()&OauthType.Oauth1RequestToken)==0){
            callback=null;
        }else{
            callback=method.getParamMap().get("oauth_callback");
        }
        SortedMap<String,String> params = new TreeMap<String,String>();
        params.put("oauth_nonce",nonce);
        if(callback!=null){
            params.put("oauth_callback",callback);
        }
        params.put("oauth_signature_method","HMAC-SHA1");
        params.put("oauth_timestamp",time);
        params.put("oauth_consumer_key",ConsumerKey);
        if(AccessToken!=null){
            params.put("oauth_token",AccessToken);
        }
        params.put("oauth_version","1.0");
        params.put("oauth_signature",createSignature(method,
                isMultiPost==false||(method.allowOauthType()&OauthType.Oauth1RequestToken)==0
                ,callback,nonce,time));
        StringBuilder baseValue=new StringBuilder();
        for(Map.Entry<String,String> e: params.entrySet()){
            if(baseValue.length()>0){
                baseValue.append(',');
                baseValue.append(' ');
            }
            baseValue.append(Utils.urlEncode(e.getKey()));
            baseValue.append('=');
            baseValue.append('"');
            baseValue.append(Utils.urlEncode(e.getValue()));
            baseValue.append('"');
        }
        String baseString = baseValue.toString();
        return baseString;
    }

    protected String createSignature(AbsMethod method,boolean isContainValue,String callback,String nonce,String time) throws Exception {
        SortedMap<String,String> params = new TreeMap<String,String>();
        params.put("oauth_nonce",nonce);
        if(callback!=null){
            params.put("oauth_callback",callback);
        }
        params.put("oauth_signature_method","HMAC-SHA1");
        params.put("oauth_timestamp",time);
        params.put("oauth_consumer_key",ConsumerKey);
        if(AccessToken!=null){
            params.put("oauth_token",AccessToken);
        }
        params.put("oauth_version","1.0");
        if(isContainValue){
            for(Map.Entry<String,String> e:method.getParam()){
                params.put(e.getKey(),e.getValue());
            }
        }
        StringBuilder baseValue=new StringBuilder();
        for(Map.Entry<String,String> e: params.entrySet()){
            if(baseValue.length()>0){
                baseValue.append('&');
            }
            baseValue.append(Utils.urlEncode(e.getKey()));
            baseValue.append('=');
            baseValue.append(Utils.urlEncode(e.getValue()));
        }
        String baseString=method.method()+"&"+Utils.urlEncode(method.url())+"&";
        baseString=baseString+Utils.urlEncode(baseValue.toString());
        String keyText = Utils.urlEncode(ConsumerSecret)+"&"+(AccessTokenSecret==null?"":Utils.urlEncode(AccessTokenSecret));
        SecretKeySpec signingKey = new SecretKeySpec(keyText.getBytes(),"HmacSHA1");
        Mac mac = Mac.getInstance(signingKey.getAlgorithm());
        mac.init(signingKey);
        byte[] binary = mac.doFinal(baseString.getBytes());
        String signature = Utils.base64Encode(binary);
        return signature;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public String getAccessTokenSecret() {
        return AccessTokenSecret;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        AccessTokenSecret = accessTokenSecret;
    }
}
