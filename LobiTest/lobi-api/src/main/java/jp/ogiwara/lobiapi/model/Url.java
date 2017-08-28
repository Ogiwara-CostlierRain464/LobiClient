package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Url implements Parcelable {
    public String video;
    public String url;
    public String title;
    public String image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.video);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.image);
    }

    public Url() {
    }

    protected Url(Parcel in) {
        this.video = in.readString();
        this.url = in.readString();
        this.title = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Url> CREATOR = new Parcelable.Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel source) {
            return new Url(source);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };
}
