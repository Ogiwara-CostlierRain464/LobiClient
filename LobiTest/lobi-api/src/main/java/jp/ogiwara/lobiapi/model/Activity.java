package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Activity implements Parcelable {
    public String link;
    public Unit unit;
    public List<UserMinimal> user;
    public String created_date;
    public ActivityTitle title;
    public Long id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.link);
        dest.writeParcelable(this.unit, flags);
        dest.writeTypedList(this.user);
        dest.writeString(this.created_date);
        dest.writeParcelable(this.title, flags);
        dest.writeValue(this.id);
    }

    public Activity() {
    }

    protected Activity(Parcel in) {
        this.link = in.readString();
        this.unit = in.readParcelable(Unit.class.getClassLoader());
        this.user = in.createTypedArrayList(UserMinimal.CREATOR);
        this.created_date = in.readString();
        this.title = in.readParcelable(ActivityTitle.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Activity> CREATOR = new Parcelable.Creator<Activity>() {
        @Override
        public Activity createFromParcel(Parcel source) {
            return new Activity(source);
        }

        @Override
        public Activity[] newArray(int size) {
            return new Activity[size];
        }
    };
}
