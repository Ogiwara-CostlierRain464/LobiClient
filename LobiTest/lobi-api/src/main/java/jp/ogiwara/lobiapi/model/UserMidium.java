package jp.ogiwara.lobiapi.model;

import android.os.Parcel;

import java.util.List;

public class UserMidium extends UserSmall{
    public List<UserSmall> users;
    public String users_next_cursor;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.users);
        dest.writeString(this.users_next_cursor);
    }

    public UserMidium() {
    }

    protected UserMidium(Parcel in) {
        super(in);
        this.users = in.createTypedArrayList(UserSmall.CREATOR);
        this.users_next_cursor = in.readString();
    }

    public static final Creator<UserMidium> CREATOR = new Creator<UserMidium>() {
        @Override
        public UserMidium createFromParcel(Parcel source) {
            return new UserMidium(source);
        }

        @Override
        public UserMidium[] newArray(int size) {
            return new UserMidium[size];
        }
    };
}
