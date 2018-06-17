package generic;

import services.SimpleArray;

public abstract class AbstractStore<T extends  Base> implements Store<T> {
    private SimpleArray<T> data = new SimpleArray<>(0xF);

    @Override
    public void add(T model) {
        this.data.add(model);
    }


    /**
     * Считаю что в этой задаче SimpleArray менять запрещено.
     * Поэтому вычисление индекса реализую тут.
     * @param id T Id
     * @return Признак удалено
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int i = 0;
        for (T t : this.data) {
            if (t.getId().equals(id)) {
                this.data.set(i, model);
                result = true;
                break;
            }
            i++;
        }
        return result;
    }

    /**
     * Считаю что в этой задаче SimpleArray менять запрещено.
     * Поэтому вычисление индекса реализую тут.
     * @param id T Id
     * @return Признак удалено
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int i = 0;
        for (T t : this.data) {
            if (t.getId().equals(id)) {
                this.data.remove(i);
                result = true;
                break;
            }
            i++;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T t : this.data) {
            if (t.getId().equals(id)) {
                result = t;
                break;
            }
        }
        return result;
    }
}
