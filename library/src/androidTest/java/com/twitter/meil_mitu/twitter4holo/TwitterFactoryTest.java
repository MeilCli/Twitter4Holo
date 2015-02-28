package com.twitter.meil_mitu.twitter4holo;

import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth2;

import junit.framework.TestCase;

public class TwitterFactoryTest extends TestCase{

    public void testGetOauth() throws Exception{
        AbsOauth oauth1_1=TwitterFactory.getInstance().getOauth(Oauth.class,ApplicationTest.ConsumerKey,ApplicationTest.ConsumerSecret,ApplicationTest.AccessToken,ApplicationTest.AccessTokenSecret);
        AbsOauth oauth1_2=TwitterFactory.getInstance().getOauth(Oauth.class,ApplicationTest.ConsumerKey,ApplicationTest.ConsumerSecret,ApplicationTest.AccessToken,ApplicationTest.AccessTokenSecret);
        AbsOauth oauth2_1=TwitterFactory.getInstance().getOauth(Oauth2.class,ApplicationTest.ConsumerKey,ApplicationTest.ConsumerSecret);
        AbsOauth oauth2_2=TwitterFactory.getInstance().getOauth(Oauth2.class,ApplicationTest.ConsumerKey,ApplicationTest.ConsumerSecret);
        assertEquals(oauth1_1,oauth1_2);
        assertEquals(oauth2_1,oauth2_2);
    }
}