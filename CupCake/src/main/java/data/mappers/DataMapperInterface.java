package data.mappers;

import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public interface DataMapperInterface<T, S> {
    void add(T t) throws Exception;
    T get(S t) throws Exception;
    List<T> getAll() throws Exception;
}
