package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Reply{
    public List<Chat> chats;
    public Chat to;
    public Integer count;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
