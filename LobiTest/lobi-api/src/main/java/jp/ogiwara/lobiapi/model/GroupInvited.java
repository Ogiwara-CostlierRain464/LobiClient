package jp.ogiwara.lobiapi.model;

import android.os.Parcel;

public class GroupInvited extends GroupSmall{
    public UserMinimal invited_by;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.invited_by, flags);
    }

    public GroupInvited() {
    }

    protected GroupInvited(Parcel in) {
        super(in);
        this.invited_by = in.readParcelable(UserMinimal.class.getClassLoader());
    }

    public static final Creator<GroupInvited> CREATOR = new Creator<GroupInvited>() {
        @Override
        public GroupInvited createFromParcel(Parcel source) {
            return new GroupInvited(source);
        }

        @Override
        public GroupInvited[] newArray(int size) {
            return new GroupInvited[size];
        }
    };
}
