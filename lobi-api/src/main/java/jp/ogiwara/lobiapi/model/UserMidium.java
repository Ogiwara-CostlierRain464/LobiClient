package jp.ogiwara.lobiapi.model;

import android.os.Parcel;

import java.util.List;

public class UserMidium extends UserSmall{
    public List<UserSmall> users;
    public String users_next_cursor;
}
