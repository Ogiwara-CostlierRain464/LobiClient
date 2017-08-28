package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ActivityTitle implements Parcelable {
    public String template;
    public List<ActivityTitleItem> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.template);
        dest.writeTypedList(this.items);
    }

    public ActivityTitle() {
    }

    protected ActivityTitle(Parcel in) {
        this.template = in.readString();
        this.items = in.createTypedArrayList(ActivityTitleItem.CREATOR);
    }

    public static final Parcelable.Creator<ActivityTitle> CREATOR = new Parcelable.Creator<ActivityTitle>() {
        @Override
        public ActivityTitle createFromParcel(Parcel source) {
            return new ActivityTitle(source);
        }

        @Override
        public ActivityTitle[] newArray(int size) {
            return new ActivityTitle[size];
        }
    };
}
