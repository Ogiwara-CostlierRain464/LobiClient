package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Unit{
    public String thumbnail;
    public Image icon;
    public String uid;
    public String like_count;
    public String name;
    public String created_date;
    public String boo_count;
    public String message;
    /**
     * chat_gooed
     * chat_posted
     * chat_image_gooed
     * chat_image_posted
     * group_joined
     */
    public String type;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
