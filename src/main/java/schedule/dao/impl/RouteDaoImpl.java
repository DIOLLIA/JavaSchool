package schedule.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;

import java.util.List;

@Repository
public class RouteDaoImpl implements RouteDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<RouteEntity> routes() {
        return getCurrentSession().createQuery("from RouteEntity").list();
    }
}
