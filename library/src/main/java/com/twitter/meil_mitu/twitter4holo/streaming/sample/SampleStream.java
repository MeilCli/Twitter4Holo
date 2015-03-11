package com.twitter.meil_mitu.twitter4holo.streaming.sample;

import com.twitter.meil_mitu.twitter4holo.AbsGet;
import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.OauthType;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;
import com.twitter.meil_mitu.twitter4holo.streaming.IStreamListener;
import com.twitter.meil_mitu.twitter4holo.streaming.IStreamParam;
import com.twitter.meil_mitu.twitter4holo.streaming.MessageType;
import com.twitter.meil_mitu.twitter4holo.streaming.Stream;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.toJSONObject;

public class SampleStream extends AbsGet<ITwitterJsonConverter> implements IStreamParam{

    private IStreamListener streamListener;
    private ISampleStreamListener sampleStreamListener;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public SampleStream(AbsOauth oauth, ITwitterJsonConverter json){
        super(oauth, json);
    }

    /**
     * should not use this
     */
    public SampleStream delimited(boolean delimited){
        addParam("delimited", delimited);
        return this;
    }

    public SampleStream stallWarnings(boolean stallWarnings){
        addParam("stall_warnings", stallWarnings);
        return this;
    }

    public SampleStream setStreamListener(IStreamListener streamListener){
        if(streamListener == null){
            return this;
        }
        this.streamListener = streamListener;
        return this;
    }

    public SampleStream setSampleStreamListener(ISampleStreamListener sampleStreamListener){
        if(sampleStreamListener == null){
            return this;
        }
        this.sampleStreamListener = sampleStreamListener;
        return this;
    }

    @Override
    public String url(){
        return "https://stream.twitter.com/1.1/statuses/sample.json";
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
    public Stream call() throws Twitter4HoloException{
        return new Stream(this);
    }

    @Override
    public InputStream getInputStream() throws Twitter4HoloException{
        return Oauth.get(this).body().byteStream();
    }

    @Override
    public IStreamListener getStreamListener(){
        return streamListener;
    }

    @Override
    public void onLine(final String line){
        if(sampleStreamListener == null){
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
                    sampleStreamListener.onStatus(Json.toStatus(obj));
                    break;
                default:
                    sampleStreamListener.onUnknown(line);
                    break;
            }
        }catch(Twitter4HoloException e){
            e.printStackTrace();
        }
    }
}
