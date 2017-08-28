package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GroupChatsSearchResult extends Cursorable implements Parcelable {
    public List<Chat> chats;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.chats);
    }

    public GroupChatsSearchResult() {
    }

    protected GroupChatsSearchResult(Parcel in) {
        this.chats = in.createTypedArrayList(Chat.CREATOR);
    }

    public static final Parcelable.Creator<GroupChatsSearchResult> CREATOR = new Parcelable.Creator<GroupChatsSearchResult>() {
        @Override
        public GroupChatsSearchResult createFromParcel(Parcel source) {
            return new GroupChatsSearchResult(source);
        }

        @Override
        public GroupChatsSearchResult[] newArray(int size) {
            return new GroupChatsSearchResult[size];
        }
    };
}
