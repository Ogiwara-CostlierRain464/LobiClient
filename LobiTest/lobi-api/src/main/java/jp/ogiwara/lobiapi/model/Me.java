package jp.ogiwara.lobiapi.model;

import android.os.Parcel;

public class Me extends User{
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public Me() {
    }

    protected Me(Parcel in) {
    }

    public static final Creator<Me> CREATOR = new Creator<Me>() {
        @Override
        public Me createFromParcel(Parcel source) {
            return new Me(source);
        }

        @Override
        public Me[] newArray(int size) {
            return new Me[size];
        }
    };
}
