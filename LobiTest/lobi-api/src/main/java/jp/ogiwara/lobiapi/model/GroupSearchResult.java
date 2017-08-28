package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GroupSearchResult implements Parcelable {
    public Integer next_page;
    public List<Group> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.next_page);
        dest.writeTypedList(this.items);
    }

    public GroupSearchResult() {
    }

    protected GroupSearchResult(Parcel in) {
        this.next_page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.items = in.createTypedArrayList(Group.CREATOR);
    }

    public static final Parcelable.Creator<GroupSearchResult> CREATOR = new Parcelable.Creator<GroupSearchResult>() {
        @Override
        public GroupSearchResult createFromParcel(Parcel source) {
            return new GroupSearchResult(source);
        }

        @Override
        public GroupSearchResult[] newArray(int size) {
            return new GroupSearchResult[size];
        }
    };
}
