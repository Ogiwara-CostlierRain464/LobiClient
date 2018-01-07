package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

public class GroupSmall extends GroupMinimal {
    public Long created_date;
    public String description;
    public String type; // not_joined | invited
    public GroupCan can;
    public String stream_host;

    public Image icon;
    @Nullable
    public Image wallpaper;

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

}
