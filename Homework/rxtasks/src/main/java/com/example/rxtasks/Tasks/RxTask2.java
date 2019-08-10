package com.example.rxtasks.Tasks;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

public class RxTask2 {

    /**
     * TODO:
     * <p>
     * Method takes observable of strings as a parameter
     * <p>
     * Supply all elements until you meet word END in the stream (END word should be excluded)
     * After that remove all repeated values from the stream
     */
    @NonNull
    public static Observable<String> task(@NonNull Observable<String> observable) {
        return observable
                .takeWhile((Predicate<? super String>) x -> !x.equals("END"))
                .distinct();
    }
}
