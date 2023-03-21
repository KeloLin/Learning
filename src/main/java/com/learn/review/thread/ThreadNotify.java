package com.learn.review.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程顺序打印ABC
 * <p>
 * 1. 公共变量保证顺序
 * 2. 未到的需要等待，轮到的执行后通知其他
 */
public class ThreadNotify {


    public static void main(String[] args) {
//        PrintAwaitNotify.print();
//        PrintLockCondition.print();
        PrintSemaphore.print();

    }

    static class PrintAwaitNotify {
        private static volatile int state = 0;
        private static Object LOCK = new Object();

        public static void print() {
            new Thread(() -> print(0, "A")).start();
            new Thread(() -> print(1, "B")).start();
            new Thread(() -> print(2, "C")).start();
        }

        private static void print(int num, String printStr) {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (state % 3 != num) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    state++;
                    System.out.print(printStr);
                    LOCK.notifyAll();
                }
            }
        }
    }

    static class PrintLockCondition {
        public static int state = 0;
        public static Lock lock = new ReentrantLock();
        public static Condition A = lock.newCondition();
        public static Condition B = lock.newCondition();
        public static Condition C = lock.newCondition();

        public static void print() {
            new Thread(() -> printCondition(0, "A", A, B)).start();
            new Thread(() -> printCondition(1, "B", B, C)).start();
            new Thread(() -> printCondition(2, "C", C, A)).start();

        }

        public static void printCondition(int num, String printStr, Condition current, Condition next) {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (state % 3 != num) {
                        current.await();
                    }
                    state++;
                    System.out.print(printStr);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class PrintSemaphore {
        private static int state = 0;
        private static Semaphore A = new Semaphore(1);
        private static Semaphore B = new Semaphore(0);
        private static Semaphore C = new Semaphore(0);

        public static void print() {
            new Thread(() -> printSemaphore(0, "A", A, B)).start();
            new Thread(() -> printSemaphore(1, "B", B, C)).start();
            new Thread(() -> printSemaphore(2, "C", C, A)).start();
        }

        private static void printSemaphore(int num, String printStr, Semaphore current, Semaphore next) {
            for (int i = 0; i < 10; i++) {
                try {
                    while (state % 3 != num) {
                        current.acquire();
                    }
                    state++;
                    System.out.print(printStr);
                    next.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
