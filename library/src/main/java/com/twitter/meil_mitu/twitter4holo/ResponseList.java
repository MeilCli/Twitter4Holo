package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.data.RateLimit;

import java.util.ArrayList;

public class ResponseList<T> extends ArrayList<T> {

    public final RateLimit RateLimit;

    public ResponseList(RateLimit rateLimit){
        this.RateLimit=rateLimit;
    }

}
