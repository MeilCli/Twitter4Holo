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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getInt;
import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getString;

public abstract class AbsOauth{

    protected static final MediaType MediaText = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
    protected static final MediaType MediaPng = MediaType.parse("image/png");
    protected static final MediaType MediaGif = MediaType.parse("image/gif");
    protected static final MediaType MediaJpeg = MediaType.parse("image/jpeg");

    protected String ConsumerKey;
    protected String ConsumerSecret;
    protected Twitter4HoloConfig Config;
    protected OkHttpClient Http;

    private String lastProtocol;

    protected AbsOauth(boolean isRequireConsumer, Twitter4HoloConfig config, String consumerKey, String consumerSecret){
        if(config == null){
            config = new Twitter4HoloConfig();
        }
        this.Config = config;
        if(isRequireConsumer == true && (consumerKey == null || consumerSecret == null)){
            throw new NullPointerException("ConsumerKey or ConsumerSecret is null");
        }
        this.ConsumerKey = consumerKey;
        this.ConsumerSecret = consumerSecret;
    }

    public abstract Response get(AbsGet param) throws Twitter4HoloException;

    public abstract Response post(AbsPost param) throws Twitter4HoloException;

    protected Response call(Request request) throws IOException{
        okhttpSetting(Config);
        Response res = Http.newCall(request).execute();
        lastProtocol = res.header("OkHttp-Selected-Protocol");
        return res;
    }

    public String getLastProtocol(){
        return lastProtocol;
    }

    protected void okhttpSetting(Twitter4HoloConfig config){
        if(Http == null){
            Http = new OkHttpClient();
            ArrayList<Protocol> list = new ArrayList<Protocol>();
            list.add(Protocol.HTTP_1_1);
            if(Config.isUseHttp2()){
                list.add(Protocol.HTTP_2);
            }
            if(Config.isUseSpdy()){
                list.add(Protocol.SPDY_3);
            }
            Http.setProtocols(list);
            Http.setConnectionPool(new ConnectionPool(5, 400));
        }
        Http.setConnectTimeout(Config.getConnectTimeoutSecond(), TimeUnit.SECONDS);
        Http.setReadTimeout(Config.getReadTimeoutSecond(), TimeUnit.SECONDS);
    }

    protected String toUrl(AbsGet param){
        StringBuilder builder = new StringBuilder();
        builder.append(param.url());
        builder.append('?');
        for(Map.Entry<String, String> e : param.getParam()){
            builder.append(e.getKey());
            builder.append('=');
            builder.append(Utils.urlEncode(e.getValue()));
            builder.append('&');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    protected RequestBody toBody(AbsPost param){
        RequestBody body;
        if(param.fileSize() == 0 && param.size() != 0){
            StringBuilder builder = new StringBuilder();
            for(Map.Entry<String, String> e : param.getParam()){
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
            for(Map.Entry<String, String> e : param.getParam()){
                multipartBuilder.addFormDataPart(e.getKey(), Utils.urlEncode(e.getValue()));
            }
            Set<Map.Entry<String, File>> files = param.getFileParam();
            for(Map.Entry<String, File> e : files){
                File f = e.getValue();
                MediaType type;
                if(f.getName().endsWith(".png")){
                    type = MediaPng;
                }else if(f.getName().endsWith(".gif")){
                    type = MediaGif;
                }else{
                    type = MediaJpeg;
                }
                multipartBuilder.addFormDataPart(Utils.urlEncode(e.getKey()), Utils.urlEncode(f.getName()), RequestBody.create(type, f));
            }
            body = multipartBuilder.build();
        }
        return body;
    }

    protected void checkError(Response res) throws Twitter4HoloException{
        if(res.isSuccessful() == false){
            try{
                JSONObject obj = new JSONObject(res.body().string());
                if(obj.isNull("errors") == false){
                    JSONArray ar = obj.getJSONArray("errors");
                    if(ar.length() == 0){
                        throw new Twitter4HoloException("errors array size is 0");
                    }
                    String message = getString(ar.getJSONObject(0), "message");
                    message += "\n\n";
                    message += res.request().headers().toString();
                    message += "\n\n";
                    message += res.request().toString();
                    int code = getInt(ar.getJSONObject(0), "code");
                    throw new Twitter4HoloException(message, res.code(), code);
                }else{
                    throw new Twitter4HoloException("some exception");
                }
            }catch(Exception e){
                e.printStackTrace();
                throw new Twitter4HoloException(e.getMessage());
            }
        }
    }

}
