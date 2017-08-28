package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ogiwara on 2017/08/19.
 */
public class MePublicGroupsItem implements Parcelable {
    public Integer visibility;
    public Group group;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.visibility);
        dest.writeParcelable(this.group, flags);
    }

    public MePublicGroupsItem() {
    }

    protected MePublicGroupsItem(Parcel in) {
        this.visibility = (Integer) in.readValue(Integer.class.getClassLoader());
        this.group = in.readParcelable(Group.class.getClassLoader());
    }

    public static final Parcelable.Creator<MePublicGroupsItem> CREATOR = new Parcelable.Creator<MePublicGroupsItem>() {
        @Override
        public MePublicGroupsItem createFromParcel(Parcel source) {
            return new MePublicGroupsItem(source);
        }

        @Override
        public MePublicGroupsItem[] newArray(int size) {
            return new MePublicGroupsItem[size];
        }
    };
}
