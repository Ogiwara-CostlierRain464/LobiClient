package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class PokeUserItem {
    public Long created_date;
    public String type; // like or ???
    public UserMinimal user;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
