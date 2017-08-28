package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

public class ActivityTitleItem implements Parcelable {
    public String link;
    public String label;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.link);
        dest.writeString(this.label);
    }

    public ActivityTitleItem() {
    }

    protected ActivityTitleItem(Parcel in) {
        this.link = in.readString();
        this.label = in.readString();
    }

    public static final Parcelable.Creator<ActivityTitleItem> CREATOR = new Parcelable.Creator<ActivityTitleItem>() {
        @Override
        public ActivityTitleItem createFromParcel(Parcel source) {
            return new ActivityTitleItem(source);
        }

        @Override
        public ActivityTitleItem[] newArray(int size) {
            return new ActivityTitleItem[size];
        }
    };
}
