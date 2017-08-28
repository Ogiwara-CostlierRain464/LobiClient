package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

public class RequestResult implements Parcelable {
    public Integer success;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.success);
    }

    public RequestResult() {
    }

    protected RequestResult(Parcel in) {
        this.success = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<RequestResult> CREATOR = new Parcelable.Creator<RequestResult>() {
        @Override
        public RequestResult createFromParcel(Parcel source) {
            return new RequestResult(source);
        }

        @Override
        public RequestResult[] newArray(int size) {
            return new RequestResult[size];
        }
    };
}
