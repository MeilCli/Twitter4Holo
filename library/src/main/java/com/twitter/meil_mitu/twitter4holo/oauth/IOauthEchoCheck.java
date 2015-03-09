package com.twitter.meil_mitu.twitter4holo.oauth;

import com.squareup.okhttp.Response;
import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

public interface IOauthEchoCheck{

    public void checkError(Response res) throws Twitter4HoloException;
}
