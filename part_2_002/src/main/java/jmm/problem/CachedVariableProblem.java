package jmm.problem;

/**
 * Демонстрация проблемы многопоточности.
 * III. Проблема кэширования в процессоре.
 * A cached variable problem occurs when a thread works with a copy of a
 * variable from the CPU cache.
 * Поток "one" никогда не закончит выполнять цикл.
 * Эта демонстрация работает по разному в зависимости от компьютера. На
 * одном компьютере она показывает ожидаемый результат - вечный цикл. А
 * на другом компьютере - под Idea она так же не завершает цикл, а при
 * запуске сборки из командной строки, цикл завершается всё-равно.
 */
public class CachedVariableProblem {

    public static void main(String[] args) {
        new CachedVariableProblem().start();
    }

    /**
     * volatile снимет проблему - цикл будет иметь конец
     */
    private boolean variable;

    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!variable) {
                    int i;
                }
                System.out.println(Thread.currentThread().getName());
            }
        }, "one").start();
        for (long i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            i = i;
        }
        System.out.println(Thread.currentThread().getName());
        this.variable = true;
    }
}
