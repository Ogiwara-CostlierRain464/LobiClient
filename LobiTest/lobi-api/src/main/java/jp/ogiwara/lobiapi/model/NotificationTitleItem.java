package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ogiwara on 2017/08/19.
 */
public class NotificationTitleItem implements Parcelable {
    public String label;
    public String link;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
        dest.writeString(this.link);
    }

    public NotificationTitleItem() {
    }

    protected NotificationTitleItem(Parcel in) {
        this.label = in.readString();
        this.link = in.readString();
    }

    public static final Parcelable.Creator<NotificationTitleItem> CREATOR = new Parcelable.Creator<NotificationTitleItem>() {
        @Override
        public NotificationTitleItem createFromParcel(Parcel source) {
            return new NotificationTitleItem(source);
        }

        @Override
        public NotificationTitleItem[] newArray(int size) {
            return new NotificationTitleItem[size];
        }
    };
}
