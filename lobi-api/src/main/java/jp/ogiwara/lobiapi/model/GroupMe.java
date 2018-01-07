package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GroupMe{
    public Integer can_extract;
    public Integer is_online;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
