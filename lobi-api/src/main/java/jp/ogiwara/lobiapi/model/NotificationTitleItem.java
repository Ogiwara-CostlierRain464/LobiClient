package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NotificationTitleItem {
    public String label;
    public String link;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
