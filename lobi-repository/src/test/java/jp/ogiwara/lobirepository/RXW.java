package jp.ogiwara.lobirepository;

import org.junit.Test;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;


public class RXW {
    @Test
    public void test(){
        Flowable.just("A","B").toList()
                .subscribe(new BiConsumer<List<String>, Throwable>() {
                    @Override
                    public void accept(@NonNull List<String> strings, @NonNull Throwable throwable) throws Exception {
                        System.out.println(strings);
                    }
                });
    }
}
