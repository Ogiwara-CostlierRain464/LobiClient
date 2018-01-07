package jp.ogiwara.lobiapi.model;


import android.os.Parcel;

import java.util.List;

public class ChatsSearchResult extends Cursorable{
    public List<Chat> chats;
    public String q;
}
