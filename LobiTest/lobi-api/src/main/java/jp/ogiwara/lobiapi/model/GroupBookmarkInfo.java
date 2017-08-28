package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupBookmarkInfo implements Parcelable {
    public Integer can_request;
    public Integer group_bookmark_count;
    public Integer has_bookmark_count;
    public Integer request_count;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.can_request);
        dest.writeValue(this.group_bookmark_count);
        dest.writeValue(this.has_bookmark_count);
        dest.writeValue(this.request_count);
    }

    public GroupBookmarkInfo() {
    }

    protected GroupBookmarkInfo(Parcel in) {
        this.can_request = (Integer) in.readValue(Integer.class.getClassLoader());
        this.group_bookmark_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.has_bookmark_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.request_count = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<GroupBookmarkInfo> CREATOR = new Parcelable.Creator<GroupBookmarkInfo>() {
        @Override
        public GroupBookmarkInfo createFromParcel(Parcel source) {
            return new GroupBookmarkInfo(source);
        }

        @Override
        public GroupBookmarkInfo[] newArray(int size) {
            return new GroupBookmarkInfo[size];
        }
    };
}
