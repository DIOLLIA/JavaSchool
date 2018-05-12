package schedule.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public class RouteDaoImpl extends GeneralCrudDaoImpl<RouteEntity> implements RouteDao {

    @Override
    public List<RouteEntity> routes() {
        return getCurrentSession().createQuery("from RouteEntity").list();
    }


    @Override
    public List<RouteEntity> findByStationNames(String... stationNames) {
        Query query = getCurrentSession().createQuery(
                "SELECT DISTINCT u FROM RouteEntity u JOIN u.stationsList s WHERE s.stationName IN :stationNames");

        query.setParameter("stationNames", Arrays.asList(stationNames));

        List<RouteEntity> routes = query.list();

        return routes;
    }
}

