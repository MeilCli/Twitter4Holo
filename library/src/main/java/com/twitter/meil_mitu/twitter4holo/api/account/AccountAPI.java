package com.twitter.meil_mitu.twitter4holo.api.account;

import com.twitter.meil_mitu.twitter4holo.AbsOauth;
import com.twitter.meil_mitu.twitter4holo.ITwitterJsonConverter;
import com.twitter.meil_mitu.twitter4holo.AbsAPI;

import java.io.File;

public class AccountAPI extends AbsAPI<ITwitterJsonConverter> {

    public AccountAPI(AbsOauth oauth, ITwitterJsonConverter json) {
        super(oauth, json);
    }

    public GetSettings getSettings(){return new GetSettings(Oauth,Json);}

    public VerifyCredentials verifyCredentials(){return new VerifyCredentials(Oauth,Json);}

    public PostSettings postSettings(){return new PostSettings(Oauth,Json);}

    public UpdateDeliveryDevice updateDeliveryDevice(String device){return new UpdateDeliveryDevice(Oauth,Json,device);}

    public UpdateProfile updateProfile(){return new UpdateProfile(Oauth,Json);}

    public UpdateProfileBackgroundImage updateProfileBackgroundImage(){return new UpdateProfileBackgroundImage(Oauth,Json);}

    public UpdateProfileImage updateProfileImage(File image){return new UpdateProfileImage(Oauth,Json,image);}

    public RemoveProfileBanner removeProfileBanner(){return new RemoveProfileBanner(Oauth,Json);}

    public UpdateProfileBanner updateProfileBanner(File banner){return new UpdateProfileBanner(Oauth,Json,banner);}

}
