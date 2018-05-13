package schedule.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;

import java.util.List;

@Repository
public class RouteDaoImpl extends GeneralCrudDaoImpl<RouteEntity> implements RouteDao {

    @Override
    public List<RouteEntity> routes() {
        return getCurrentSession().createQuery("from RouteEntity").list();
    }


    @Override
    //todo change this query with select in array.
    public List<RouteEntity> findByStationNames(String... stationNames) {
        Query query = getCurrentSession().createQuery(
                "SELECT DISTINCT u FROM RouteEntity u LEFT JOIN u.stationsList s WHERE s.stationName LIKE :name1 or s.stationName LIKE :name2");
        query.setParameter("name1", stationNames[0]);
        query.setParameter("name2", stationNames[1]);

        List<RouteEntity> routes = query.list();

        return routes;
    }

}

