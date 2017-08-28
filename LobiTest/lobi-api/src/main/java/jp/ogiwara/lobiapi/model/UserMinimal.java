package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ogiwara on 2017/08/18.
 */
public class UserMinimal implements Parcelable {
    public String uid;
    public String name;
    public String description;
    public String icon;
    public String cover;
    public Integer premium;

    public UserMinimal() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.icon);
        dest.writeString(this.cover);
        dest.writeValue(this.premium);
    }

    protected UserMinimal(Parcel in) {
        this.uid = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
        this.cover = in.readString();
        this.premium = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<UserMinimal> CREATOR = new Creator<UserMinimal>() {
        @Override
        public UserMinimal createFromParcel(Parcel source) {
            return new UserMinimal(source);
        }

        @Override
        public UserMinimal[] newArray(int size) {
            return new UserMinimal[size];
        }
    };
}
