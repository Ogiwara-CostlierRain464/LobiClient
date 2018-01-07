package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GroupBookmarkInfo{
    public Integer can_request;
    public Integer group_bookmark_count;
    public Integer has_bookmark_count;
    public Integer request_count;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
