package com.twitter.meil_mitu.twitter4holo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.twitter.meil_mitu.twitter4holo.exception.Twitter4HoloException;

import org.json.JSONObject;

import static com.twitter.meil_mitu.twitter4holo.util.JsonUtils.getJSONObject;

public class Banner implements Parcelable{

    public final BannerSize Ipad, IpadRetina, Web, WebRetina, Mobile, MobileRetina;

    public Banner(JSONObject obj) throws Twitter4HoloException{
        if(obj.isNull("sizes")){
            throw new Twitter4HoloException("sizes is null");
        }else{
            obj = getJSONObject(obj, "sizes");
        }
        if(obj.isNull("ipad")){
            throw new Twitter4HoloException("ipad is null");
        }else{
            Ipad = new BannerSize(getJSONObject(obj, "ipad"));
        }
        if(obj.isNull("ipad_retina")){
            throw new Twitter4HoloException("ipad_retina is null");
        }else{
            IpadRetina = new BannerSize(getJSONObject(obj, "ipad_retina"));
        }
        if(obj.isNull("web")){
            throw new Twitter4HoloException("web is null");
        }else{
            Web = new BannerSize(getJSONObject(obj, "web"));
        }
        if(obj.isNull("web_retina")){
            throw new Twitter4HoloException("web_retina is null");
        }else{
            WebRetina = new BannerSize(getJSONObject(obj, "web_retina"));
        }
        if(obj.isNull("mobile")){
            throw new Twitter4HoloException("mobile is null");
        }else{
            Mobile = new BannerSize(getJSONObject(obj, "mobile"));
        }
        if(obj.isNull("mobile_retina")){
            throw new Twitter4HoloException("mobile_retina");
        }else{
            MobileRetina = new BannerSize(getJSONObject(obj, "mobile_retina"));
        }
    }

    public Banner(Parcel in){
        this.Ipad = in.readParcelable(BannerSize.class.getClassLoader());
        this.IpadRetina = in.readParcelable(BannerSize.class.getClassLoader());
        this.Web = in.readParcelable(BannerSize.class.getClassLoader());
        this.WebRetina = in.readParcelable(BannerSize.class.getClassLoader());
        this.Mobile = in.readParcelable(BannerSize.class.getClassLoader());
        this.MobileRetina = in.readParcelable(BannerSize.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeParcelable(this.Ipad, 0);
        dest.writeParcelable(this.IpadRetina, 0);
        dest.writeParcelable(this.Web, 0);
        dest.writeParcelable(this.WebRetina, 0);
        dest.writeParcelable(this.Mobile, 0);
        dest.writeParcelable(this.MobileRetina, 0);
    }

    @Override
    public String toString(){
        return "Banner{" +
                "Ipad=" + Ipad +
                ", IpadRetina=" + IpadRetina +
                ", Web=" + Web +
                ", WebRetina=" + WebRetina +
                ", Mobile=" + Mobile +
                ", MobileRetina=" + MobileRetina +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Banner)) return false;

        Banner banner = (Banner) o;

        if(!Ipad.equals(banner.Ipad)) return false;
        if(!IpadRetina.equals(banner.IpadRetina)) return false;
        if(!Mobile.equals(banner.Mobile)) return false;
        if(!MobileRetina.equals(banner.MobileRetina)) return false;
        if(!Web.equals(banner.Web)) return false;
        if(!WebRetina.equals(banner.WebRetina)) return false;

        return true;
    }

    @Override
    public int hashCode(){
        int result = Ipad.hashCode();
        result = 31 * result + IpadRetina.hashCode();
        result = 31 * result + Web.hashCode();
        result = 31 * result + WebRetina.hashCode();
        result = 31 * result + Mobile.hashCode();
        result = 31 * result + MobileRetina.hashCode();
        return result;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Banner> CREATOR = new Parcelable.Creator<Banner>(){
        public Banner createFromParcel(Parcel source){
            return new Banner(source);
        }

        public Banner[] newArray(int size){
            return new Banner[size];
        }
    };
}
