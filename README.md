# Twitter4Holo
Twitter Oauth Library for Android 4.0.3 and upper  
please debug and advice 

* use [okhttp][okhttp_url]
* use [ActiveAndroid][ActiveAndroid_url]
* response Objects implements Parcelable without **Support class
* if you want to know Objects, you shall see [data package](https://github.com/MeilCli/Twitter4Holo/tree/master/library/src/main/java/com/twitter/meil_mitu/twitter4holo/data)
* Objects is not full
* if you want to use full Objects, you shall make Data class and Converter extends AbsJsonConverter
* ok [oauth](https://dev.twitter.com/oauth/3-legged)
* ok [oauth2](https://dev.twitter.com/oauth/application-only)
* ok [oauth echo](https://dev.twitter.com/oauth/echo)
* ok [multiple media upload](https://dev.twitter.com/rest/public/uploading-media)
* ok [Video in MediaEntity](https://blog.twitter.com/2015/now-on-twitter-group-direct-messages-and-mobile-video-capture)
* ok [REST API](https://dev.twitter.com/rest/public)
* ok [SampleStream](https://dev.twitter.com/streaming/reference/get/statuses/sample) (please debug and advice)
* ok [FilterStream](https://dev.twitter.com/streaming/reference/post/statuses/filter) (please debug and advice)
* ok [UserStream](https://dev.twitter.com/streaming/reference/get/user) (please debug and advice)
* ok [Aclog API](http://aclog.koba789.com/about/api)

####何をしたかったのか
* Oauth2をわかりやすく
* メソッドをわかりやすく
* public fieldのほうが早いらしいのでそっちに
* 必要そうなfieldだけを持ち、足りなかった場合のために拡張性を上げる
* parcelをサポート

####なぜこうなったのか
* public fieldが大文字スタートなのはほとんどC#の影響
* たまにエンドポイント通りの設計になってないのはjavaの予約語とかいろいろあってだな…


#### gradle
	repositories {
	    mavenCentral()
	    maven { url "https://raw.github.com/MeilCli/Twitter4Holo/master/library/repository" }
		maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
	}
	
	dependencies {
		compile 'com.squareup.okhttp:okhttp:2.2.0'
		compile 'com.michaelpardo:activeandroid:3.1.0-SNAPSHOT'
		compile 'meilcli:twitter4holo:0.4.+@aar'
	}

#### AndroidManifest.xml
	<manifest ...>
	
		<uses-permission android:name="android.permission.INTERNET"/>
	
	</manifest>

#### Application class
*if use item package* (use ActiveAndroid or change Object Values)

	Configuration.Builder builder =new Configuration.Builder(this.getContext());
    TwitterBuildHelper.add(builder);
    ActiveAndroid.initialize(builder.create());

#### make Twitter instance
*Oauth(not have token)*

	Oauth oauth = new Oauth(new Twitter4HoloConfig(),"ConsumerKey","ConsumerSecret");//Twitter4HoloConfig is nullable
	Twitter twitter = new Twitter(oauth);
	twitter.oauth().requestToken().call();//should use async thread
	String url = twitter.oauth().authorize();
	//go to url and get verifier
	twitter.oauth().accessToken(verifier).call();//should use async thread

*Oauth(have token)*

	Oauth oauth = new Oauth(new Twitter4HoloConfig(),"ConsumerKey","ConsumerSecret","AccessToken","AccessTokenSecret");//Twitter4HoloConfig is nullable
	Twitter twitter = new Twitter(oauth);

*Oauth2*

	Oauth2 oauth = new Oauth2(new Twitter4HoloConfig(),"ConsumerKey","ConsumerSecret");//Twitter4HoloConfig is nullable
	Twitter twitter = new Twitter(oauth);

#### call Twitter API
*update status with media*

	//should use async thread
	long id = twitter.media().upload(File).call().MediaId;//File is File class
    Status status = twitter.statuses().update(Text).mediaIds(new long[]{id}).call();//Text is String class

*get user_timeline*

	//should use async thread
	ResponseList<Status> list = twitter.statuses().userTimeline().screenName(ScreenName).count(20).call();
	//ResponseList extends ArrayList

#### replace API namespace
*if this endpoint is added another endpoints, may change namespace*

* GET statuses/retweeters/ids → statuses.Retweeters → ??
* GET friendships/no_retweets/ids → friendships.NoRetweets → ??

*endpoints escape, so change namespace*

* GET direct_messages → directmessages.Get
* POST direct_messages/new → directmessages.PostNew
* GET account/settings → account.GetSettings
* POST account/settings → account.PostSettings
* GET users/suggestions/:slug → suggestions.Get
* GET users/suggestions → suggestions.List
* GET users/suggestions/:slug/members → suggestions.Members
* GET lists/subscribers → lists.subscribers.Get
* GET lists/members → lists.members.Get

ライセンス
----------

This source is The MIT License.

using [okhttp][okhttp_url] [Apache License, Version 2.0][Apache]  
using [ActiveAndroid][ActiveAndroid_url] [Apache License, Version 2.0][Apache]
[Apache]: http://www.apache.org/licenses/LICENSE-2.0
[okhttp_url]: https://github.com/square/okhttp
[ActiveAndroid_url]: https://github.com/pardom/ActiveAndroid
