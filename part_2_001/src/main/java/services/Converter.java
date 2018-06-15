package services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator;

            /**
             * В этом методе цикл while служит для пропуска пустых итераторов.
             * @return есть элементы для next()
             */
            @Override
            public boolean hasNext() {
                boolean result = true;
                while (result
                       && (this.currentIterator == null
                           || !this.currentIterator.hasNext())) {
                    if (it.hasNext()) {
                        this.currentIterator = it.next();
                    } else {
                        result = false;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.currentIterator.next();
            }
        };
    }
}
