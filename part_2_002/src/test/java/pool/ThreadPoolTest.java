package pool;

import org.junit.Test;
import org.junit.Before;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ThreadPoolTest {
    private ThreadPool pool;

    @Before
    public void before() {
        pool = new ThreadPool();
    }

    @Test
    public void whenEmptyWorks() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
                ThreadPoolTest.this.pool.shutdown();
            }
        }.start();
        this.pool.start();
    }

    private List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    @Test
    public void whenSomeWorks() {
        Thread works = new Thread() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    ThreadPoolTest.this.pool.work(new Runnable() {
                        @Override
                        public void run() {
                            list.add(list.size());
                        }
                    });
                }
            }
        };
        works.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
                ThreadPoolTest.this.pool.shutdown();
            }
        }.start();
        this.pool.start();
        assertThat(list.size(), greaterThan(0));
    }
}
