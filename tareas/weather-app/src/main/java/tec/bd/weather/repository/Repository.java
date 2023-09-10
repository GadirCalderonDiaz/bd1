package tec.bd.weather.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface Repository <T,ID> {

    /**
     * Find item by id
     * @param id
     * @return Item
     */
    Optional findById(ID id);

    List<T> findAll();

    T save(T t);

    void delete(ID id);

    T update(T source);
}