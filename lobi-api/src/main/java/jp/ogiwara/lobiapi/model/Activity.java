package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Activity{
    public String link;
    public Unit unit;
    public List<UserMinimal> user;
    public String created_date;
    public ActivityTitle title;
    public Long id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
