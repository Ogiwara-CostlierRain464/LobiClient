package jp.ogiwara.lobiapi.model;


import android.os.Parcel;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Pokes extends Cursorable{
    public List<PokeUserItem> users;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
