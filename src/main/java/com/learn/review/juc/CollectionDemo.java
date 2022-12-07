package com.learn.review.juc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Kelo
 * @Date: 2022/10/24
 */
public class CollectionDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>(4);
        for (int i = 0; i < 100; i++) {
            hashMap.put(i, i);
        }
    }
}
