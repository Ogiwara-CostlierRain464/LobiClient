package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class NotificationTitle implements Parcelable {
    public List<NotificationTitleItem> items;
    public String template;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.items);
        dest.writeString(this.template);
    }

    public NotificationTitle() {
    }

    protected NotificationTitle(Parcel in) {
        this.items = new ArrayList<NotificationTitleItem>();
        in.readList(this.items, NotificationTitleItem.class.getClassLoader());
        this.template = in.readString();
    }

    public static final Parcelable.Creator<NotificationTitle> CREATOR = new Parcelable.Creator<NotificationTitle>() {
        @Override
        public NotificationTitle createFromParcel(Parcel source) {
            return new NotificationTitle(source);
        }

        @Override
        public NotificationTitle[] newArray(int size) {
            return new NotificationTitle[size];
        }
    };
}
