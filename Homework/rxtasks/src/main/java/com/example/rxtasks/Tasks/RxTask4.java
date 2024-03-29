package com.example.rxtasks.Tasks;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class RxTask4 {

    /**
     * TODO :
     * <p>
     * Method takes boolean stream of single boolean value
     * and two numbers streams
     * <p>
     * If boolean value in stream it true, then you should choose first stream of number,
     * in other case - second
     * <p>
     * If result stream has any number greater than 99, your observable should finish with error
     * <p>
     * Examples:
     * Input boolean stream: (true)
     * First input stream: (5, 19, 12)
     * Second input stream: (9, 210, 87)
     * Result stream: (5, 19, 12)
     * <p>
     * Input boolean stream: (false)
     * First input stream: (5, 19, 12)
     * Second input stream: (9, 210, 87)
     * Result stream: (9, Exception)
     */
    @NonNull
    public static Observable<Integer> task(@NonNull Observable<Boolean> flagObservable,
                                            @NonNull Observable<Integer> first, @NonNull Observable<Integer> second) {

        return (flagObservable.blockingSingle()
                ? first
                : second).doOnNext(x -> {
                    if (x > 99){
                        throw new Exception("Number is greater then 99");
                    }
        });
    }
}
