package fs;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Future;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;

/**
 * Всего здесь 3 варианта реализации: однопоточный, пул потоков и
 * двухпоточный (обход файлов и отбор файлов).
 * 2) Пул потоков
 * В этом задании нужно написать метод, который возвращает список всех
 * файлов с конкретным расширением. Метод должен заходить во все
 * каталоги. Здесь нельзя использовать FileVisitor. Это задание на работу
 * с деревом каталогов.
 * Использование многопоточности по числу физических процессоров.
 */
public class ExecutorSearch {
    public List<File> files(String parent, List<String> exts)
                        throws InterruptedException, ExecutionException {
        ConcurrentLinkedQueue<Future<List<File>>> futures =
                                           new ConcurrentLinkedQueue<>();
        ExecutorService pool = Executors.newFixedThreadPool(
                             Runtime.getRuntime().availableProcessors());
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        pool.submit(new Task(semaphore, new AtomicInteger(), pool,
                                       futures, new File(parent), exts));
        semaphore.acquire();
        pool.shutdownNow();
        List<File> result = new ArrayList<File>();
        for (Future<List<File>> f : futures) {
            result.addAll(f.get());
        }
        return result;
    }
}
