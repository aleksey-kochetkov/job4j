package jmm.problem;

import java.util.Arrays;

/**
 * Демонстрация проблемы многопоточности.
 * II. Проблема параллельного доступа к данным.
 * A data race occurs when two or more threads access the same memory
 * location concurrently.
 * Результат работы программы непредсказуем
 * [181959, 181944]
 */
public class DataRaceProblem implements Runnable {
    static int[] data = new int[2];

    public static void main(String[] args) throws InterruptedException {
        Runnable problem = new DataRaceProblem();
        Thread one = new Thread(problem, "one");
        Thread two = new Thread(problem, "two");
        one.start();
        two.start();
        one.join();
        two.join();
        System.out.format("%s%n", Arrays.toString(data));
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            data[0]++;
            data[1]++;
        }
    }
}
