package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Members extends Cursorable implements Parcelable {
    public List<UserSmall> members;
    public UserSmall owner;
    public List<UserSmall> subleaders;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.members);
        dest.writeParcelable(this.owner, flags);
        dest.writeTypedList(this.subleaders);
    }

    public Members() {
    }

    protected Members(Parcel in) {
        this.members = in.createTypedArrayList(UserSmall.CREATOR);
        this.owner = in.readParcelable(UserSmall.class.getClassLoader());
        this.subleaders = in.createTypedArrayList(UserSmall.CREATOR);
    }

    public static final Parcelable.Creator<Members> CREATOR = new Parcelable.Creator<Members>() {
        @Override
        public Members createFromParcel(Parcel source) {
            return new Members(source);
        }

        @Override
        public Members[] newArray(int size) {
            return new Members[size];
        }
    };
}
