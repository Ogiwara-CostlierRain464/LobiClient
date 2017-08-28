package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class VisibleGroups extends Cursorable implements Parcelable {
    public List<Group> public_groups;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.public_groups);
    }

    public VisibleGroups() {
    }

    protected VisibleGroups(Parcel in) {
        this.public_groups = in.createTypedArrayList(Group.CREATOR);
    }

    public static final Parcelable.Creator<VisibleGroups> CREATOR = new Parcelable.Creator<VisibleGroups>() {
        @Override
        public VisibleGroups createFromParcel(Parcel source) {
            return new VisibleGroups(source);
        }

        @Override
        public VisibleGroups[] newArray(int size) {
            return new VisibleGroups[size];
        }
    };
}
