package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Chat{
    public static class Type{
        public static String NORMAL = "normal";
        public static String MEMO_UPDATED = "system.memo_updated";
        public static String WALLPAPER_UPDATED = "system.wallpaper_updated";
        public static String NAME_UPDATED = "system.name_updated";
        public static String SHOUT = "shout";
    }

    public List<Asset> assets;
    public Integer assets_expired;
    public Integer booed;
    public Integer bookmarks_count;
    public Integer boos_count;
    public Long created_date;
    //public Long edited_date;
    public String id;
    public Image image;
    public Integer image_height;
    public Integer image_width;
    public String image_type;
    public Integer is_group_bookmarked;
    public Integer is_me_bookmarked;
    public Integer liked;
    public Integer likes_count;
    //public Long max_edit_limit_date;
    public String message;
    public Reply replies;
    public String reply_to;
    public String stamp_id;
    public String type; //normal(chat, photo, stamp) system.memo_updated system.wallpaper_updated shout system.name_updated
    public List<Url> urls;
    public UserMinimal user;
    public Long bookmark_id;
    public Group group; //must set uid!!!
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}