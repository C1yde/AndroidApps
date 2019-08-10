package com.example.rxtasks.Tasks;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class RxTask1 {
    /**
     * TODO : implement this method
     * <p>
     * This method takes list of strings and creates an observable of integers,
     * that represents length of strings which contains letter 'r' (or 'R')
     * <p>
     * Example:
     * Input list: ("Vasya", "Dima", "Artur", "Petya", "Roma")
     * Result stream: (5, 4)
     */
    @NonNull
    public static Observable<Integer> task(@NonNull List<String> list) {
        return Observable.fromIterable(list)
            .filter(x -> x.contains("r") || x.contains("R"))
            .map(String::length);
    }
}
