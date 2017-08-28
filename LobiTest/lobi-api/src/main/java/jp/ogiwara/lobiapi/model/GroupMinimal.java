package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

public class GroupMinimal implements Parcelable {
    public String uid;
    public String name;

    public GroupMinimal() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.name);
    }

    protected GroupMinimal(Parcel in) {
        this.uid = in.readString();
        this.name = in.readString();
    }

    public static final Creator<GroupMinimal> CREATOR = new Creator<GroupMinimal>() {
        @Override
        public GroupMinimal createFromParcel(Parcel source) {
            return new GroupMinimal(source);
        }

        @Override
        public GroupMinimal[] newArray(int size) {
            return new GroupMinimal[size];
        }
    };
}
