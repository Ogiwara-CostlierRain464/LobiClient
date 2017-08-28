package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Unit implements Parcelable {
    public String thumbnail;
    public String icon;
    public String uid;
    public String like_count;
    public String name;
    public String created_date;
    public String boo_count;
    public String message;
    /**
     * chat_gooed
     * chat_posted
     * chat_image_gooed
     * chat_image_posted
     * group_joined
     */
    public String type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thumbnail);
        dest.writeString(this.icon);
        dest.writeString(this.uid);
        dest.writeString(this.like_count);
        dest.writeString(this.name);
        dest.writeString(this.created_date);
        dest.writeString(this.boo_count);
        dest.writeString(this.message);
        dest.writeString(this.type);
    }

    public Unit() {
    }

    protected Unit(Parcel in) {
        this.thumbnail = in.readString();
        this.icon = in.readString();
        this.uid = in.readString();
        this.like_count = in.readString();
        this.name = in.readString();
        this.created_date = in.readString();
        this.boo_count = in.readString();
        this.message = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Unit> CREATOR = new Parcelable.Creator<Unit>() {
        @Override
        public Unit createFromParcel(Parcel source) {
            return new Unit(source);
        }

        @Override
        public Unit[] newArray(int size) {
            return new Unit[size];
        }
    };
}
