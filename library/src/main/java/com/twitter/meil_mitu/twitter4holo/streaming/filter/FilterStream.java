package com.twitter.meil_mitu.twitter4holo.streaming.filter;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.AbsPost;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.streaming.IStreamListener;
import com.twitter.meil_mitu.twitter4holo.streaming.IStreamParam;
import com.twitter.meil_mitu.twitter4holo.streaming.MessageType;
import com.twitter.meil_mitu.twitter4holo.streaming.Stream;
import com.twitter.meil_mitu.twitter4holo.util.Utils;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONObject;

public class FilterStream extends AbsPost<ITwitterJsonConverter> implements IStreamParam{

    private IStreamListener streamListener;
    private IFilterStreamListener filterStreamListener;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public FilterStream(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    public FilterStream follow(long[] follow){
        addParam("follow", Utils.toString(follow));
        return this;
    }

    public FilterStream track(String[] track){
        addParam("track", Utils.toString(track));
        return this;
    }

    public FilterStream locations(String[] locations){
        addParam("locations", Utils.toString(locations));
        return this;
    }

    /**
     * should not use this
     */
    public FilterStream delimited(boolean delimited){
        addParam("delimited", delimited);
        return this;
    }

    public FilterStream stallWarnings(boolean stallWarnings){
        addParam("stall_warnings", stallWarnings);
        return this;
    }

    public FilterStream setStreamListener(IStreamListener streamListener){
        if(streamListener == null){
            return this;
        }
        this.streamListener = streamListener;
        return this;
    }

    public FilterStream setFilterStreamListener(IFilterStreamListener filterStreamListener){
        if(filterStreamListener == null){
            return this;
        }
        this.filterStreamListener = filterStreamListener;
        return this;
    }

    @Override
    public String url(){
        return "https://stream.twitter.com/1.1/statuses/filter.json";
    }

    @Override
    public int allowOauthType(){
        return OauthType.Oauth1;
    }

    @Override
    public boolean isAuthorization(){
        return true;
    }

    @Override
    public Stream call(){
        return new Stream(this);
    }

    @Override
    public InputStream getInputStream() throws Twitter4HoloException{
        return Oauth.post(this).body().byteStream();
    }

    @Override
    public IStreamListener getStreamListener(){
        return streamListener;
    }

    @Override
    public void onLine(final String line){
        if(filterStreamListener == null){
            return;
        }
        executorService.submit(new Runnable(){
            @Override
            public void run(){
                handleLine(line);
            }
        });
    }

    private void handleLine(String line){
        try{
            JSONObject obj = toJSONObject(line);
            int type = MessageType.type(obj);
            switch(type){
                case MessageType.Status:
                    filterStreamListener.onStatus(Json.toStatus(obj));
                    break;
                default:
                    filterStreamListener.onUnknown(line);
                    break;
            }
        }catch(Twitter4HoloException e){
            e.printStackTrace();
        }
    }

}
