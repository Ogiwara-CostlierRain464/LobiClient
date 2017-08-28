package jp.ogiwara.lobiapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Cursorable implements Parcelable {
    public String next_cursor;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.next_cursor);
    }

    public Cursorable() {
    }

    protected Cursorable(Parcel in) {
        this.next_cursor = in.readString();
    }
}
