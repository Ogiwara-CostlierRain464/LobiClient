package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ogiwara on 2017/08/19.
 */
public class GroupMe implements Parcelable {
    public Integer can_extract;
    public Integer is_online;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.can_extract);
        dest.writeValue(this.is_online);
    }

    public GroupMe() {
    }

    protected GroupMe(Parcel in) {
        this.can_extract = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_online = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<GroupMe> CREATOR = new Parcelable.Creator<GroupMe>() {
        @Override
        public GroupMe createFromParcel(Parcel source) {
            return new GroupMe(source);
        }

        @Override
        public GroupMe[] newArray(int size) {
            return new GroupMe[size];
        }
    };
}
