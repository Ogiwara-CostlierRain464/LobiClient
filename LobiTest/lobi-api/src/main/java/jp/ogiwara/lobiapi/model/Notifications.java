package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Notifications implements Parcelable {
    public String last_cursor;
    public List<Notification> notifications;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.last_cursor);
        dest.writeTypedList(this.notifications);
    }

    public Notifications() {
    }

    protected Notifications(Parcel in) {
        this.last_cursor = in.readString();
        this.notifications = in.createTypedArrayList(Notification.CREATOR);
    }

    public static final Parcelable.Creator<Notifications> CREATOR = new Parcelable.Creator<Notifications>() {
        @Override
        public Notifications createFromParcel(Parcel source) {
            return new Notifications(source);
        }

        @Override
        public Notifications[] newArray(int size) {
            return new Notifications[size];
        }
    };
}
