package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Parcelable {
    public List<Asset> assets;
    public Integer assets_expired;
    public Integer booed;
    public Integer bookmarks_count;
    public Integer boos_count;
    public Long created_date;
    public Long edited_date;
    public String id;
    public String image;
    public Integer image_height;
    public Integer image_width;
    public String image_type;
    public Integer is_group_bookmarked;
    public Integer is_me_bookmarked;
    public Integer liked;
    public Integer likes_count;
    public Long max_edit_limit_date;
    public String message;
    public Reply replies;
    public String reply_to;
    public String stamp_id;
    public String type;
    public List<Url> urls;
    public UserMinimal user;
    public Long bookmark_id;
    public Group group;

    public Chat() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.assets);
        dest.writeValue(this.assets_expired);
        dest.writeValue(this.booed);
        dest.writeValue(this.bookmarks_count);
        dest.writeValue(this.boos_count);
        dest.writeValue(this.created_date);
        dest.writeValue(this.edited_date);
        dest.writeString(this.id);
        dest.writeString(this.image);
        dest.writeValue(this.image_height);
        dest.writeValue(this.image_width);
        dest.writeString(this.image_type);
        dest.writeValue(this.is_group_bookmarked);
        dest.writeValue(this.is_me_bookmarked);
        dest.writeValue(this.liked);
        dest.writeValue(this.likes_count);
        dest.writeValue(this.max_edit_limit_date);
        dest.writeString(this.message);
        dest.writeParcelable(this.replies, flags);
        dest.writeString(this.reply_to);
        dest.writeString(this.stamp_id);
        dest.writeString(this.type);
        dest.writeTypedList(this.urls);
        dest.writeParcelable(this.user, flags);
        dest.writeValue(this.bookmark_id);
        dest.writeParcelable(this.group, flags);
    }

    protected Chat(Parcel in) {
        this.assets = in.createTypedArrayList(Asset.CREATOR);
        this.assets_expired = (Integer) in.readValue(Integer.class.getClassLoader());
        this.booed = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bookmarks_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.boos_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.created_date = (Long) in.readValue(Long.class.getClassLoader());
        this.edited_date = (Long) in.readValue(Long.class.getClassLoader());
        this.id = in.readString();
        this.image = in.readString();
        this.image_height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.image_width = (Integer) in.readValue(Integer.class.getClassLoader());
        this.image_type = in.readString();
        this.is_group_bookmarked = (Integer) in.readValue(Integer.class.getClassLoader());
        this.is_me_bookmarked = (Integer) in.readValue(Integer.class.getClassLoader());
        this.liked = (Integer) in.readValue(Integer.class.getClassLoader());
        this.likes_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.max_edit_limit_date = (Long) in.readValue(Long.class.getClassLoader());
        this.message = in.readString();
        this.replies = in.readParcelable(Reply.class.getClassLoader());
        this.reply_to = in.readString();
        this.stamp_id = in.readString();
        this.type = in.readString();
        this.urls = in.createTypedArrayList(Url.CREATOR);
        this.user = in.readParcelable(UserMinimal.class.getClassLoader());
        this.bookmark_id = (Long) in.readValue(Long.class.getClassLoader());
        this.group = in.readParcelable(Group.class.getClassLoader());
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };
}