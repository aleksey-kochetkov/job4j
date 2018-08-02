package nonblocking;

import org.junit.Before;
import org.junit.Test;

public class NonBlockingCacheTest {
    private OptimisticException exception;

    @Before
    public void before() {
        this.exception = null;
    }

    @Test(expected = OptimisticException.class)
    public void whenRepetedAdditionThenException() {
        NonBlockingCache cache = new NonBlockingCache();
        Thread one = new Thread("one") {
            @Override
            public void run() {
                try {
                    cache.add(new Base(0));
                } catch (OptimisticException exception) {
                    NonBlockingCacheTest.this.exception = exception;
                }
            }
        };
        one.start();
        cache.add(new Base(0));
        try {
            one.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        if (this.exception != null) {
            throw exception;
        }
    }

    @Test
    public void whenUpdate() {
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(new Base(3));
        Thread one = new Thread("one") {
            @Override
            public void run() {
                Base model = new Base(3);
                model.incVersion();
                try {
                    cache.update(model);
                } catch (OptimisticException exception) {
                    NonBlockingCacheTest.this.exception = exception;
                }
            }
        };
        one.start();
        try {
            one.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        if (this.exception != null) {
            throw exception;
        }
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateNotNewThenException() {
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(new Base(3));
        Thread one = new Thread("one") {
            @Override
            public void run() {
                try {
                    cache.update(new Base(3));
                } catch (OptimisticException exception) {
                    NonBlockingCacheTest.this.exception = exception;
                }
            }
        };
        one.start();
        try {
            one.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        if (this.exception != null) {
            throw exception;
        }
    }

    @Test
    public void whenDeleteThenSuccess() {
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(new Base(4));
        Thread one = new Thread("one") {
            @Override
            public void run() {
                Base model = new Base(4);
                model.incVersion();
                try {
                    cache.delete(model);
                } catch (OptimisticException exception) {
                    NonBlockingCacheTest.this.exception = exception;
                }
            }
        };
        one.start();
        try {
            one.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        if (this.exception != null) {
            throw exception;
        }
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateDeletedThenException() {
        NonBlockingCache cache = new NonBlockingCache();
        Base model = new Base(5);
        cache.add(model);
        model.incVersion();
        cache.delete(model);
        Thread one = new Thread("one") {
            @Override
            public void run() {
                try {
                    cache.update(new Base(5));
                } catch (OptimisticException exception) {
                    NonBlockingCacheTest.this.exception = exception;
                }
            }
        };
        one.start();
        try {
            one.join();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        if (this.exception != null) {
            throw exception;
        }
    }
}
