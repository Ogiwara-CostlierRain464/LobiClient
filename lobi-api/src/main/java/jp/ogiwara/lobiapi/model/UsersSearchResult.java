package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UsersSearchResult{
    public Integer has_next;
    public Integer page;
    public List<User> users;
}
