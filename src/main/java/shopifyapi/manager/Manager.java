package shopifyapi.manager;

public interface Manager<T> {

    Iterable<T> getAll();

    T get(Long id);

    T save(T model);

    void delete(Long id);
}
