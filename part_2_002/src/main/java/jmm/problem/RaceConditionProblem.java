package jmm.problem;

import java.util.Random;

/**
 * Демонстрация проблемы многопоточности.
 * I. Проблема рассинхронизации потоков.
 * A race condition occurs when the correctness of a computation depends
 * on the relative timing of multiple threads.
 * Первые строки вывода программы:
 * mainone 0
 * 0one
 * main1
 *  1one
 *  main2
 */
public class RaceConditionProblem {

    public static void main(String[] args) {
        new RaceConditionProblem().start();
    }

    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RaceConditionProblem.this.print();
            }
        }, "one").start();
        this.print();
    }

    private void print() {
        for (int i = 0; i < 10; i++) {
            this.doSeriousComputations();
            System.out.print(Thread.currentThread().getName());
            this.doSeriousComputations();
            System.out.print(" ");
            this.doSeriousComputations();
            System.out.print(i);
            this.doSeriousComputations();
            System.out.println();
        }
    }

    private static final Random RANDOM = new Random();
    private void doSeriousComputations() {
        final int MAX =  Integer.MAX_VALUE / 3 * (RANDOM.nextInt(3) + 1);
        for (long j = 0; j < MAX; j++) {
            j = j;
        }
    }
}
