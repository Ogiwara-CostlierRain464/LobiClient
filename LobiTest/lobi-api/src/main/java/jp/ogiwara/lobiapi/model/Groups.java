package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Groups implements Parcelable {
    public String title;
    public List<Group> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeTypedList(this.items);
    }

    public Groups() {
    }

    protected Groups(Parcel in) {
        this.title = in.readString();
        this.items = in.createTypedArrayList(Group.CREATOR);
    }

    public static final Parcelable.Creator<Groups> CREATOR = new Parcelable.Creator<Groups>() {
        @Override
        public Groups createFromParcel(Parcel source) {
            return new Groups(source);
        }

        @Override
        public Groups[] newArray(int size) {
            return new Groups[size];
        }
    };
}
