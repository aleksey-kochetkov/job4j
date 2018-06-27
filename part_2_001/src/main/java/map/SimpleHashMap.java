package map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private Object[] entries = new Object[1];
    private int size;
    private int modCount;

    private class Entry {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        if (this.size == this.entries.length) {
            this.resize();
        }
        int i = getIndex(key);
        if (this.entries[i] == null) {
            this.modCount++;
            this.entries[i] = new Entry(key, value);
            result = true;
            size++;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        V result = null;
        Entry entry = (Entry) this.entries[this.getIndex(key)];
        if (entry != null && entry.key.equals(key)) {
            result = entry.value;
        }
        return result;
    }

    /**
     * Переименовал метод из задания чтоб в голове не путалось.
     * @param key ключ
     * @return удаление произведено
     */
    @SuppressWarnings("unchecked")
    public boolean remove(K key) {
        boolean result = false;
        int i = this.getIndex(key);
        if (this.entries[i] != null && ((Entry) this.entries[i]).key.equals(key)) {
            this.modCount++;
            this.entries[i] = null;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int next;
            private int expectedModCount = SimpleHashMap.this.modCount;

            @Override
            public boolean hasNext() {
                while (this.next < SimpleHashMap.this.entries.length
                       && SimpleHashMap.this.entries[this.next] == null) {
                    this.next++;
                }
                return this.next < SimpleHashMap.this.entries.length;
            }

            @SuppressWarnings("unchecked")
            @Override
            public K next() {
                if (SimpleHashMap.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return ((Entry) SimpleHashMap.this.entries[next++]).key;
            }
        };
    }

    private int getIndex(K key) {
        return key.hashCode() & (this.entries.length - 1);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Object[] oldEntries = this.entries;
        this.entries = new Object[this.entries.length * 2];
        for (Object e : oldEntries) {
            this.entries[this.getIndex(((Entry) e).key)] = e;
        }
    }
}
