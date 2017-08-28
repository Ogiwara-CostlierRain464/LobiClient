package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupCan implements Parcelable {
    public Integer add_members;
    public Integer invite;
    public Integer join;
    public Integer kick;
    public Integer part;
    public Integer peek;
    public Integer post_chat;
    public Integer remove;
    public Integer shout;
    public Integer update_description;
    public Integer update_icon;
    public Integer update_name;
    public Integer update_restriction;
    public Integer update_wallpaper;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.add_members);
        dest.writeValue(this.invite);
        dest.writeValue(this.join);
        dest.writeValue(this.kick);
        dest.writeValue(this.part);
        dest.writeValue(this.peek);
        dest.writeValue(this.post_chat);
        dest.writeValue(this.remove);
        dest.writeValue(this.shout);
        dest.writeValue(this.update_description);
        dest.writeValue(this.update_icon);
        dest.writeValue(this.update_name);
        dest.writeValue(this.update_restriction);
        dest.writeValue(this.update_wallpaper);
    }

    public GroupCan() {
    }

    protected GroupCan(Parcel in) {
        this.add_members = (Integer) in.readValue(Integer.class.getClassLoader());
        this.invite = (Integer) in.readValue(Integer.class.getClassLoader());
        this.join = (Integer) in.readValue(Integer.class.getClassLoader());
        this.kick = (Integer) in.readValue(Integer.class.getClassLoader());
        this.part = (Integer) in.readValue(Integer.class.getClassLoader());
        this.peek = (Integer) in.readValue(Integer.class.getClassLoader());
        this.post_chat = (Integer) in.readValue(Integer.class.getClassLoader());
        this.remove = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shout = (Integer) in.readValue(Integer.class.getClassLoader());
        this.update_description = (Integer) in.readValue(Integer.class.getClassLoader());
        this.update_icon = (Integer) in.readValue(Integer.class.getClassLoader());
        this.update_name = (Integer) in.readValue(Integer.class.getClassLoader());
        this.update_restriction = (Integer) in.readValue(Integer.class.getClassLoader());
        this.update_wallpaper = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<GroupCan> CREATOR = new Parcelable.Creator<GroupCan>() {
        @Override
        public GroupCan createFromParcel(Parcel source) {
            return new GroupCan(source);
        }

        @Override
        public GroupCan[] newArray(int size) {
            return new GroupCan[size];
        }
    };
}
