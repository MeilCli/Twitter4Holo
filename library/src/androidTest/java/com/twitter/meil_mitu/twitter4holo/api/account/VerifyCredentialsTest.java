package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.ApplicationTest;

import junit.framework.TestCase;

public class VerifyCredentialsTest extends TestCase{

    public void testCall() throws Exception{
        ApplicationTest.Twitter1.account().verifyCredentials().call();
    }
}