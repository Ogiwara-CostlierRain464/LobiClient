package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UsersSearchResult implements Parcelable {
    public Integer has_next;
    public Integer page;
    public List<User> users;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.has_next);
        dest.writeValue(this.page);
        dest.writeTypedList(this.users);
    }

    public UsersSearchResult() {
    }

    protected UsersSearchResult(Parcel in) {
        this.has_next = (Integer) in.readValue(Integer.class.getClassLoader());
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.users = in.createTypedArrayList(User.CREATOR);
    }

    public static final Parcelable.Creator<UsersSearchResult> CREATOR = new Parcelable.Creator<UsersSearchResult>() {
        @Override
        public UsersSearchResult createFromParcel(Parcel source) {
            return new UsersSearchResult(source);
        }

        @Override
        public UsersSearchResult[] newArray(int size) {
            return new UsersSearchResult[size];
        }
    };
}
