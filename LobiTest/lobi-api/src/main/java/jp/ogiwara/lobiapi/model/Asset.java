package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Asset implements Parcelable {
    public String url;
    public String type;
    public String id;
    public String raw_url;
    public Integer order;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.type);
        dest.writeString(this.id);
        dest.writeString(this.raw_url);
        dest.writeValue(this.order);
    }

    public Asset() {
    }

    protected Asset(Parcel in) {
        this.url = in.readString();
        this.type = in.readString();
        this.id = in.readString();
        this.raw_url = in.readString();
        this.order = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Asset> CREATOR = new Parcelable.Creator<Asset>() {
        @Override
        public Asset createFromParcel(Parcel source) {
            return new Asset(source);
        }

        @Override
        public Asset[] newArray(int size) {
            return new Asset[size];
        }
    };
}
