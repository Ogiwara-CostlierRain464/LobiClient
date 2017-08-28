package jp.ogiwara.lobiapi.model;

import android.os.Parcel;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class User extends UserMidium{
    public Long followed_date;
    public Long following_date;
    public Integer followers_count;
    public Long contacted_date;
    public Integer contacts_count;

    public Integer is_blocked;

    public Integer my_groups_count;

    public List<MePublicGroupsItem> public_groups;
    public Integer public_groups_count;
    public String public_groups_next_cursor;

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(this.followed_date);
        dest.writeValue(this.following_date);
        dest.writeValue(this.followers_count);
        dest.writeValue(this.contacted_date);
        dest.writeValue(this.contacts_count);
        dest.writeValue(this.is_blocked);
        dest.writeValue(this.my_groups_count);
        dest.writeTypedList(this.public_groups);
        dest.writeValue(this.public_groups_count);
        dest.writeString(this.public_groups_next_cursor);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.followed_date = (Long) in.readValue(Long.class.getClassLoader());
        this.following_date = (Long) in.readValue(Long.class.getClassLoader());
        this.followers_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.contacted_date = (Long) in.readValue(Long.class.getClassLoader());
        this.contacts_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_blocked = (Integer) in.readValue(Integer.class.getClassLoader());
        this.my_groups_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.public_groups = in.createTypedArrayList(MePublicGroupsItem.CREATOR);
        this.public_groups_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.public_groups_next_cursor = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

