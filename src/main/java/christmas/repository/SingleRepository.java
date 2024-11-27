package christmas.repository;

import java.util.Optional;

public interface SingleRepository<Type> {
    void save(Type entity);
    Optional<Type> get();
}
