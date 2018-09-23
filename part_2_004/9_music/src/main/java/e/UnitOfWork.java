package e;

public interface UnitOfWork extends AutoCloseable {
    void beginWork();
    void commitWork();
}
