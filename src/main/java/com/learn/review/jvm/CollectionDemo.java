package com.learn.review.jvm;

import java.util.Random;
import java.util.concurrent.*;

public class CollectionDemo {

    public static final ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();

    public static void main(String[] args) {
        Runnable a = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    i++;
                    map.put(i, i * new Random().nextInt());
                    if (i > 10) {
                        i = 0;
                        try {
                            Thread.currentThread().sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Runnable b = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    System.out.println(" ---- 第" + i++ + "次");
                    int j = 0;
                    for (Integer value : map.values()) {
                        Integer remove = map.remove(value);
                        System.out.println(remove);
                        try {
                            Thread.currentThread().sleep(1000);
                            System.out.println(map.values().size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        j++;
                    }
                    if (j > 11) {
                        System.out.println("remove times: " + j);
                    }

                }
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(a);
        executor.submit(b);
    }

}
