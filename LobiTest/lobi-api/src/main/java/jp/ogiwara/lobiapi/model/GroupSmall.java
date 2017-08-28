package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

public class GroupSmall extends GroupMinimal implements Parcelable {
    public Long created_date;
    public String description;
    public String type;
    public GroupCan can;
    public String stream_host;

    public String icon;
    public String wallpaper;

    public Integer is_archived;
    public Integer is_authorized;
    public Integer is_notice;
    public Integer is_official;
    public Integer is_online;
    public Integer is_public;

    public Long last_chat_at;
    public Integer online_users;
    public Integer total_users;

    public Integer push_enabled;

    public GroupSmall() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(this.created_date);
        dest.writeString(this.description);
        dest.writeString(this.type);
        dest.writeParcelable(this.can, flags);
        dest.writeString(this.stream_host);
        dest.writeString(this.icon);
        dest.writeString(this.wallpaper);
        dest.writeValue(this.is_archived);
        dest.writeValue(this.is_authorized);
        dest.writeValue(this.is_notice);
        dest.writeValue(this.is_official);
        dest.writeValue(this.is_online);
        dest.writeValue(this.is_public);
        dest.writeValue(this.last_chat_at);
        dest.writeValue(this.online_users);
        dest.writeValue(this.total_users);
        dest.writeValue(this.push_enabled);
    }

    protected GroupSmall(Parcel in) {
        super(in);
        this.created_date = (Long) in.readValue(Long.class.getClassLoader());
        this.description = in.readString();
        this.type = in.readString();
        this.can = in.readParcelable(GroupCan.class.getClassLoader());
        this.stream_host = in.readString();
        this.icon = in.readString();
        this.wallpaper = in.readString();
        this.is_archived = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_authorized = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_notice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_official = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_online = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_public = (Integer) in.readValue(Integer.class.getClassLoader());
        this.last_chat_at = (Long) in.readValue(Long.class.getClassLoader());
        this.online_users = (Integer) in.readValue(Integer.class.getClassLoader());
        this.total_users = (Integer) in.readValue(Integer.class.getClassLoader());
        this.push_enabled = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<GroupSmall> CREATOR = new Creator<GroupSmall>() {
        @Override
        public GroupSmall createFromParcel(Parcel source) {
            return new GroupSmall(source);
        }

        @Override
        public GroupSmall[] newArray(int size) {
            return new GroupSmall[size];
        }
    };
}
