package com.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<L, R> {

    private L left;

    private R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }
}
