package schedule.dao.impl;

import org.springframework.stereotype.Repository;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;

import java.util.List;

@Repository
public class RouteDaoImpl  extends  GeneralCrudDaoImpl <RouteEntity> implements RouteDao {

    @Override
    public List<RouteEntity> routes() {
        return getCurrentSession().createQuery("from RouteEntity").list();
    }
}
