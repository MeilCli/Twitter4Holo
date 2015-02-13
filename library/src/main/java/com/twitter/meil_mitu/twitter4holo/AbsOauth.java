package com.twitter.meil_mitu.twitter4holo;

import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class AbsOauth {
    protected static final MediaType MediaText = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
    protected static final MediaType MediaPng = MediaType.parse("image/png");
    protected static final MediaType MediaGif = MediaType.parse("image/gif");
    protected static final MediaType MediaJpeg = MediaType.parse("image/jpeg");

    protected String ConsumerKey;
    protected String ConsumerSecret;
    protected Config Config;
    protected OkHttpClient Http;

    private String lastProtocol;

    protected AbsOauth(Config config,String consumerKey,String consumerSecret){
        if(config==null){
            config=new Config();
        }
        this.Config = config;
        if(consumerKey==null||consumerSecret==null){
            throw new NullPointerException("ConsumerKey or ConsumerSecret is null");
        }
        this.ConsumerKey=consumerKey;
        this.ConsumerSecret=consumerSecret;
    }

    public abstract Response get(AbsGet param) throws Twitter4HoloException;

    public abstract Response post(AbsPost param) throws Twitter4HoloException;

    protected Response call(Request request)throws IOException{
        okhttpSetting(Config);
        Response res =Http.newCall(request).execute();
        lastProtocol=res.header("OkHttp-Selected-Protocol");
        return res;
    }

    public String getLastProtocol() {
        return lastProtocol;
    }

    protected void okhttpSetting(Config config){
        if(Http==null){
            Http=new OkHttpClient();
            ArrayList<Protocol> list = new ArrayList<Protocol>();
            list.add(Protocol.HTTP_1_1);
            if(Config.isUseHttp2()){
                list.add(Protocol.HTTP_2);
            }
            if(Config.isUseSpdy()){
                list.add(Protocol.SPDY_3);
            }
            Http.setProtocols(list);
            Http.setConnectionPool(new ConnectionPool(5,400));
        }
        Http.setConnectTimeout(Config.getConnectTimeoutSecond(), TimeUnit.SECONDS);
        Http.setReadTimeout(Config.getReadTimeoutSecond(),TimeUnit.SECONDS);
    }

    protected String toUrl(AbsGet param){
        StringBuilder builder = new StringBuilder();
        builder.append(param.url());
        builder.append('?');
        for(Map.Entry<String,String> e : param.getParam()){
            builder.append(e.getKey());
            builder.append('=');
            builder.append(Utils.urlEncode(e.getValue()));
            builder.append('&');
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    protected RequestBody toBody(AbsPost param){
        RequestBody body;
        if(param.fileSize()==0&&param.size()!=0) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> e : param.getParam()) {
                builder.append(Utils.urlEncode(e.getKey()));
                builder.append('=');
                builder.append(Utils.urlEncode(e.getValue()));
                builder.append('&');
            }
            builder.deleteCharAt(builder.length() - 1);
            body = RequestBody.create(MediaText, builder.toString());
        }else{
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(MultipartBuilder.FORM);
            for(Map.Entry<String,String> e:param.getParam()){
                multipartBuilder.addFormDataPart(e.getKey(),Utils.urlEncode(e.getValue()));
            }
            for(Map.Entry<String,File> e:param.getFileParam()){
                File f = e.getValue();
                MediaType type;
                if(f.getName().endsWith(".png")) {
                    type = MediaPng;
                }else if(f.getName().endsWith(".gif")){
                    type=MediaGif;
                }else{
                    type=MediaJpeg;
                }
                multipartBuilder.addFormDataPart(e.getKey(),f.getName(),RequestBody.create(type,f));
            }
            body=multipartBuilder.build();
        }
        return body;
    }
}
