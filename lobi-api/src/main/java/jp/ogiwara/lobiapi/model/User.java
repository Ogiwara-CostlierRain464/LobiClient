package jp.ogiwara.lobiapi.model;

import android.os.Parcel;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class User extends UserMidium{
    public Long followed_date;
    public Long following_date;
    public Integer followers_count;
    public Long contacted_date;
    public Integer contacts_count;

    public Integer is_blocked;

    public Integer my_groups_count;

    public List<MePublicGroupsItem> public_groups;
    public Integer public_groups_count;
    public String public_groups_next_cursor;

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).toString();
    }
}

