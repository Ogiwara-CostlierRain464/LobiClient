package jp.ogiwara.cycle;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

public interface Dispatcher<S extends State>{
    void dispatch(@NotNull BasicStore<S> store,
                  @NotNull Action action,
                  @NotNull Context context);
}