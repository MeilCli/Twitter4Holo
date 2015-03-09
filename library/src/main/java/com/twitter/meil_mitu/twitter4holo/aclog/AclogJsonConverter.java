package com.twitter.meil_mitu.twitter4holo.aclog;

import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.AbsJsonConverter;
import com.twitter.meil_mitu.twitter4holo.Twitter4HoloConfig;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStats;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogStatus;
import com.twitter.meil_mitu.twitter4holo.aclog.data.AclogUser;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONArray;

import java.util.ArrayList;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;

public class AclogJsonConverter extends AbsJsonConverter implements IAclogJsonConverter{

    private static AclogJsonConverter defaultConverter;

    public static AclogJsonConverter getDefaultConverter(){
        if(defaultConverter == null){
            defaultConverter = new AclogJsonConverter();
        }
        return defaultConverter;
    }


    @Override
    public AclogStatus toStatus(Response res) throws Twitter4HoloException{
        return new AclogStatus(toJSONObject(toString(res.body())));
    }

    @Override
    public ArrayList<AclogStatus> toStatusList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ArrayList<AclogStatus> list = new ArrayList<AclogStatus>();
        for(int i = 0; i < size; i++){
            try{
                list.add(new AclogStatus(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }

    @Override
    public AclogStats toStats(Response res) throws Twitter4HoloException{
        return new AclogStats(toJSONObject(toString(res.body())));
    }

    @Override
    public ArrayList<AclogUser> toUserList(Response res) throws Twitter4HoloException{
        JSONArray ar = toJSONArray(toString(res.body()));
        int size = ar.length();
        ArrayList<AclogUser> list = new ArrayList<AclogUser>();
        for(int i = 0; i < size; i++){
            try{
                list.add(new AclogUser(getJSONObject(ar, i)));
            }catch(Twitter4HoloException e){
                e.printStackTrace();
                if(Twitter4HoloConfig.IsDebug){
                    throw e;
                }
            }
        }
        return list;
    }
}
