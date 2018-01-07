package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Notification{
    public Long created_date;
    public Image icon;
    public String id;
    public String link;
    public String message;
    public String type;
    public UserMinimal user;
    public NotificationTitle title;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

