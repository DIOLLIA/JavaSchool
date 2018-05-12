package schedule.dao.api;

import java.util.List;

public interface GeneralCrudDao<T> {
    T create(T entity);

    List<T> getAll();

    T update(T entity);

    void delete(T entity);


}
