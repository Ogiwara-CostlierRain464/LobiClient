package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Followers extends Users implements Parcelable {
    public Integer visibility;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.visibility);
    }

    public Followers() {
    }

    protected Followers(Parcel in) {
        this.visibility = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Followers> CREATOR = new Parcelable.Creator<Followers>() {
        @Override
        public Followers createFromParcel(Parcel source) {
            return new Followers(source);
        }

        @Override
        public Followers[] newArray(int size) {
            return new Followers[size];
        }
    };
}