package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class NotificationTitle{
    public List<NotificationTitleItem> items;
    public String template;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
