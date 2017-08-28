package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Users extends Cursorable implements Parcelable {
    public List<User> users;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.users);
    }

    public Users() {
    }

    protected Users(Parcel in) {
        this.users = in.createTypedArrayList(User.CREATOR);
    }

    public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel source) {
            return new Users(source);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}