# Twitter4Holo
Twitter Oauth Library for Android 4.0.3 and upper  
作りかけ 

* use [okhttp][okhttp_url]
* response Objects implements Parcelable without **Support class
* if you want to know Objects, you shall see [data package](https://github.com/MeilCli/Twitter4Holo/tree/master/library/src/main/java/com/twitter/meil_mitu/twitter4holo/data)
* Objects is not full
* if you want to use full Objects, you shall make Data class and Converter extends AbsJsonConverter
* oauth/* supported
* oauth2/* supported
* statuses/* supported
* media/* supported
* direct_messages/* supported
* friendships/* supported
* friends/* supported
* followers/* supported
* account/* supported
* blocks/* supported
* users/* supported

####何をしたかったのか
* Oauth2をわかりやすく
* メソッドをわかりやすく
* public fieldのほうが早いらしいのでそっちに
* 必要そうなfieldだけを持ち、足りなかった場合のために拡張性を上げる
* parcelをサポート

####なぜこうなったのか
* public fieldが大文字スタートなのはほとんどC#の影響
* たまにエンドポイント通りの設計になってないのはjavaの予約語とかいろいろあってだな…

####次したいこと
* すべてのREST API対応
* Streaming API対応(site streamは難しそう)
* Oauth Echo対応(Aclogとかサポートしたい)
* ActiveAndroidを使いSQLiteをサポート←public final fieldややこしいので値変更用クラス作る
* 独自クエリ作成←自分用

#### gradle
	repositories {
	    mavenCentral()
	    maven {
 	       url 'https://raw.github.com/MeilCli/Twitter4Holo/master/library/repository'
 	   }
	}
	
	dependencies {
		compile 'com.squareup.okhttp:okhttp:2.2.0'
		compile 'meilcli:twitter4holo:0.0.+@aar'
	}

#### make Twitter instance
*Oauth(not have token)*

	Oauth oauth = new Oauth(new Config(),"ConsumerKey","ConsumerSecret");//Config is nullable
	Twitter twitter = new Twitter(oauth);
	twitter.oauth().requestToken().call();//should use async thread
	String url = twitter.oauth().authorize();
	//go to url and get verifier
	twitter.oauth().accessToken(verifier).call();//should use async thread

*Oauth(have token)*

	Oauth oauth = new Oauth(new Config(),"ConsumerKey","ConsumerSecret","AccessToken","AccessTokenSecret");//Config is nullable
	Twitter twitter = new Twitter(oauth);

*Oauth2*

	Oauth2 oauth = new Oauth2(new Config(),"ConsumerKey","ConsumerSecret");//Config is nullable
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

* GET statuses/retweeters/ids → statuses.Retweeters
* GET friendships/no_retweets/ids → friendships.NoRetweets

*endpoints escape, so change namespace*

* GET direct_messages → directmessages.Get
* POST direct_messages/new → directmessages.PostNew
* GET account/settings → account.GetSettings
* POST account/settings → account.PostSettings
* GET users/suggestions/:slug → suggestions.Get
* GET users/suggestions → suggestions.List
* GET users/suggestions/:slug/members → suggestions.Members

ライセンス
----------

This source is The MIT License.

using [okhttp][okhttp_url] [Apache License, Version 2.0][Apache]
[Apache]: http://www.apache.org/licenses/LICENSE-2.0
[okhttp_url]: https://github.com/square/okhttp
