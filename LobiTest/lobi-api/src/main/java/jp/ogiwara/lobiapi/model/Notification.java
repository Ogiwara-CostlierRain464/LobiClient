package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Notification implements Parcelable {
    public Long created_date;
    public String icon;
    public String id;
    public String link;
    public String message;
    public String type;
    public UserMinimal user;
    public NotificationTitle title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.created_date);
        dest.writeString(this.icon);
        dest.writeString(this.id);
        dest.writeString(this.link);
        dest.writeString(this.message);
        dest.writeString(this.type);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.title, flags);
    }

    public Notification() {
    }

    protected Notification(Parcel in) {
        this.created_date = (Long) in.readValue(Long.class.getClassLoader());
        this.icon = in.readString();
        this.id = in.readString();
        this.link = in.readString();
        this.message = in.readString();
        this.type = in.readString();
        this.user = in.readParcelable(UserMinimal.class.getClassLoader());
        this.title = in.readParcelable(NotificationTitle.class.getClassLoader());
    }

    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel source) {
            return new Notification(source);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };
}

