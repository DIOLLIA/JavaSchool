package schedule.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import schedule.dao.api.GeneralCrudDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GeneralCrudDaoImpl<T> implements GeneralCrudDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Class<T> entityClass;

    public GeneralCrudDaoImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public T create(T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    @Override
    public List<T> getAll() {
        List<T> entities = getCurrentSession().createQuery("SELECT t FROM "
                + entityClass.getSimpleName() + " t").list();
        return entities;
    }

    @Override
    public T update(T entity) {
        if (entity != null) {
            getCurrentSession().merge(entity);
        }
        return entity;
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().beginTransaction();     // эти две строки проверить на надобностть
        if (null != entity) {
            getCurrentSession().delete(entity);
        } else {
            System.out.println("!!Attempt to remove entity = null");
        }
        getCurrentSession().getTransaction().commit(); //
    }
}
