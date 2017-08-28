package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Activities implements Parcelable {
    public Long last_cursor;
    public List<Activity> activity;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.last_cursor);
        dest.writeTypedList(this.activity);
    }

    public Activities() {
    }

    protected Activities(Parcel in) {
        this.last_cursor = (Long) in.readValue(Long.class.getClassLoader());
        this.activity = in.createTypedArrayList(Activity.CREATOR);
    }

    public static final Parcelable.Creator<Activities> CREATOR = new Parcelable.Creator<Activities>() {
        @Override
        public Activities createFromParcel(Parcel source) {
            return new Activities(source);
        }

        @Override
        public Activities[] newArray(int size) {
            return new Activities[size];
        }
    };
}
