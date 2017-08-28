package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Group extends GroupSmall implements Parcelable {
    public List<Chat> chats;
    public UserSmall owner;
    public List<UserSmall> subleaders;
    public String now;
    public GroupMe me;
    public GroupBookmarkInfo group_bookmark_info;

    public List<UserSmall> members;
    public Integer members_count;
    public String  members_next_cursor;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.chats);
        dest.writeParcelable(this.owner, flags);
        dest.writeList(this.subleaders);
        dest.writeString(this.now);
        dest.writeParcelable(this.me, flags);
        dest.writeParcelable(this.group_bookmark_info, flags);
        dest.writeList(this.members);
        dest.writeValue(this.members_count);
        dest.writeString(this.members_next_cursor);
    }

    public Group() {
    }

    protected Group(Parcel in) {
        this.chats = new ArrayList<Chat>();
        in.readList(this.chats, Chat.class.getClassLoader());
        this.owner = in.readParcelable(UserSmall.class.getClassLoader());
        this.subleaders = in.readParcelable(UserSmall[].class.getClassLoader());
        this.now = in.readString();
        this.me = in.readParcelable(GroupMe.class.getClassLoader());
        this.group_bookmark_info = in.readParcelable(GroupBookmarkInfo.class.getClassLoader());
        this.members = in.readParcelable(UserSmall[].class.getClassLoader());
        this.members_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.members_next_cursor = in.readString();
    }

    public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}

