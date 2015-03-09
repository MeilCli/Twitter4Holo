package com.twitter.meil_mitu.twitter4holo.aclog.api.tweets;

import com.twitter.meil_mitu.twitter4holo.ApplicationTest;
import com.twitter.meil_mitu.twitter4holo.aclog.Aclog;

import junit.framework.TestCase;

public class UserTimelineTest extends TestCase{

    public void testCall1() throws Exception{
        Aclog aclog = ApplicationTest.Aclog1;
        assertEquals(aclog.tweets().userTimeline().screenName("meil_mitu").count(13).authorization(true).call().size(),13);
    }

    public void testCall2() throws Exception{
        Aclog aclog = ApplicationTest.Aclog2;
        assertEquals(aclog.tweets().userTimeline().screenName("meil_mitu").count(13).call().size(),13);
    }

    public void testCall3() throws Exception{
        Aclog aclog = ApplicationTest.Aclog1;
        UserTimeline ut=aclog.tweets().userTimeline().authorization(true).screenName("ICS_sora_meil");
        ut.setHost("http://aclog.rhe.jp");
        ut.call();
        int count=ApplicationTest.Twitter1.account().verifyCredentials().call().RateLimit.Remaining;
        ut.call();
        assertEquals(ApplicationTest.Twitter1.account().verifyCredentials().call().RateLimit.Remaining,count-1);
    }
}