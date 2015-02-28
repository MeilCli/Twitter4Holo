package com.twitter.meil_mitu.twitter4holo;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth2;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public static String ConsumerKey="";
    public static String ConsumerSecret="";
    public static String AccessToken="";
    public static String AccessTokenSecret="";


    public static Twitter Twitter1 = new Twitter(TwitterFactory.getInstance().getOauth(Oauth.class, ApplicationTest.ConsumerKey,ApplicationTest.ConsumerSecret,ApplicationTest.AccessToken,ApplicationTest.AccessTokenSecret));
    public static Twitter Twitter2 = new Twitter(TwitterFactory.getInstance().getOauth(Oauth2.class, ApplicationTest.ConsumerKey,ApplicationTest.ConsumerSecret));


    public ApplicationTest() {
        super(Application.class);
    }
}