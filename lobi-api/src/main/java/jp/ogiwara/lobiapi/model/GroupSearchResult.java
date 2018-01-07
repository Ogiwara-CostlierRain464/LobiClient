package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class GroupSearchResult{
    public Integer next_page;
    public List<Group> items;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
