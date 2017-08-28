package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Reply implements Parcelable {
    public List<Chat> chats;
    public Chat to;
    public Integer count;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.chats);
        dest.writeParcelable(this.to, flags);
        dest.writeValue(this.count);
    }

    public Reply() {
    }

    protected Reply(Parcel in) {
        this.chats = in.createTypedArrayList(Chat.CREATOR);
        this.to = in.readParcelable(Chat.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Reply> CREATOR = new Parcelable.Creator<Reply>() {
        @Override
        public Reply createFromParcel(Parcel source) {
            return new Reply(source);
        }

        @Override
        public Reply[] newArray(int size) {
            return new Reply[size];
        }
    };
}
