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
        this.cache.compute(model.getId(), (k, v) -> {
            if (model.getVersion() < v.getVersion()) {
                throw new OptimisticException();
            }
            model.incVersion();
            return model;
        });
    }


    public void delete(Base model) {
        cache.remove(model.getId());
    }
}
