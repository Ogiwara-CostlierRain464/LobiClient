package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Contacts extends Users{
    public Integer visibility;

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).toString();
    }
}
