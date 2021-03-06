package emotionalsongs;

import java.util.List;
import java.util.Optional;

public interface InterfaceDB<T> {
    Optional<T> get(Object id);
    List<T> getAll();
    void save(T t);
    void update(T base, T modified);
    void delete(T t);
}
