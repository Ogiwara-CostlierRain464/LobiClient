package jp.ogiwara.lobiapi.model;

import android.os.Parcel;

import java.util.List;

public class Bookmarks extends Cursorable{
    public List<Chat> data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.data);
    }

    public Bookmarks() {
    }

    protected Bookmarks(Parcel in) {
        super(in);
        this.data = in.createTypedArrayList(Chat.CREATOR);
    }

    public static final Creator<Bookmarks> CREATOR = new Creator<Bookmarks>() {
        @Override
        public Bookmarks createFromParcel(Parcel source) {
            return new Bookmarks(source);
        }

        @Override
        public Bookmarks[] newArray(int size) {
            return new Bookmarks[size];
        }
    };
}
