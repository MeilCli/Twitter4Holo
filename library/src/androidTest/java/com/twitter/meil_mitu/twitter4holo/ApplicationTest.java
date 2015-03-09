package com.twitter.meil_mitu.twitter4holo;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.twitter.meil_mitu.twitter4holo.aclog.Aclog;
import com.twitter.meil_mitu.twitter4holo.aclog.AclogOauthEchoCheck;
import com.twitter.meil_mitu.twitter4holo.activeandroid.TwitterBuildHelper;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth2;
import com.twitter.meil_mitu.twitter4holo.oauth.OauthEcho;

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
    public static Aclog Aclog1 = new Aclog(new OauthEcho(new AclogOauthEchoCheck(),null,ConsumerKey,ConsumerSecret,AccessToken,AccessTokenSecret));
    public static Aclog Aclog2=new Aclog(new OauthEcho(new AclogOauthEchoCheck(),null));

    public ApplicationTest() {
        super(Application.class);
    }
}