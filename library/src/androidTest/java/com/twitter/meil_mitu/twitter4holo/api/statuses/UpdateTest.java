package com.twitter.meil_mitu.twitter4holo.api.statuses;

import com.twitter.meil_mitu.twitter4holo.ApplicationTest;
import com.twitter.meil_mitu.twitter4holo.Twitter;
import com.twitter.meil_mitu.twitter4holo.TwitterFactory;
import com.twitter.meil_mitu.twitter4holo.data.Status;
import com.twitter.meil_mitu.twitter4holo.oauth.Oauth;

import junit.framework.TestCase;

public class UpdateTest extends TestCase{

    public void testCall() throws Exception{
        Twitter tw=ApplicationTest.Twitter1;
        String text = "Twitter4Holo test in "+System.currentTimeMillis()+" *-&<> #test http://google.com *-&<>";
        Status status = tw.statuses().update(text).call();
        assertEquals(status.EntitySupport.PlainText,text);
    }
}