package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Group extends GroupSmall{
    /**
     * use {@link jp.ogiwara.lobiapi.LobiAPI#getGroupChatV2(String)}
     */
    //public List<Chat> chats;getGroupChatsV2
    public UserSmall owner;
    public List<UserSmall> subleaders;
    public String now;
    public GroupMe me;
    public GroupBookmarkInfo group_bookmark_info;
    /**
     * use {@link jp.ogiwara.lobiapi.LobiAPI#getGroupMembers(String)}
     */
    //public List<UserSmall> members; //heavy
    public Integer members_count;
    public String  members_next_cursor;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

