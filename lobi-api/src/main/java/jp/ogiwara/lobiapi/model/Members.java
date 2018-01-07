package jp.ogiwara.lobiapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Members extends Cursorable{
    public List<UserSmall> members;
    public UserSmall owner;
    public List<UserSmall> subleaders;

}
