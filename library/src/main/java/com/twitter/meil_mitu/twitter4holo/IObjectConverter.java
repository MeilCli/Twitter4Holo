package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

public interface IObjectConverter{

    public JSONObject toJSONObject() throws Twitter4HoloException;
}
