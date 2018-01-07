package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Asset{
    public Image url;
    public String type;
    public String id;
    //public String raw_url;
    public Integer order;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
