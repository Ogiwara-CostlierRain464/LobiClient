package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

public class UserSmall extends UserMinimal implements Parcelable {
    public Integer unread_count;
    public Long last_chat_at;
    public Float lat;
    public Float lng;

    public UserSmall() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(this.unread_count);
        dest.writeValue(this.last_chat_at);
        dest.writeValue(this.lat);
        dest.writeValue(this.lng);
    }

    protected UserSmall(Parcel in) {
        super(in);
        this.unread_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.last_chat_at = (Long) in.readValue(Long.class.getClassLoader());
        this.lat = (Float) in.readValue(Float.class.getClassLoader());
        this.lng = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Creator<UserSmall> CREATOR = new Creator<UserSmall>() {
        @Override
        public UserSmall createFromParcel(Parcel source) {
            return new UserSmall(source);
        }

        @Override
        public UserSmall[] newArray(int size) {
            return new UserSmall[size];
        }
    };
}
