package jp.ogiwara.lobiapi.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Bookmarks extends Cursorable{
    public List<Chat> data;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
