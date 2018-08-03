package nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {
    private ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * При повторном добавлении будет исключение
     * @param model элемент кэша
     */
    public void add(Base model) {
        model.resetVersion();
        Base value =
                   this.cache.computeIfAbsent(model.getId(), k -> model);
        if (value != model) {
            throw new OptimisticException();
        }
    }

    public void update(Base model) {
        update(model, false);
    }


    public void delete(Base model) {
        update(model, true);
    }

    /**
     * ввёл version равную -1 как признак того, что уже удалено
     * @param model элемент кэша
     */
    private void update(Base model, boolean delete) {
        this.cache.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() < v.getVersion()
                || v.getVersion() == -1) {
                throw new OptimisticException();
            }
            if (delete) {
                model.setDeleted();
            } else {
                model.incVersion();
            }
            return model;
        });
    }
}
