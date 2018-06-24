package schedule.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import schedule.dao.api.RouteDao;
import schedule.entity.RouteEntity;
import schedule.entity.StationEntity;

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

        return (List<RouteEntity>) query.list();
        /*   List<RouteEntity> routes = query.list();        return routes;*/
    }

    @Override
    public List<StationEntity> stationsOnRoute(int routeId) {
        Query query = getCurrentSession().createQuery("SELECT stationsList FROM RouteEntity re WHERE re.id =:routeId ");
        query.setParameter("routeId", routeId);
        return  (List<StationEntity>)query.list();


    }

    @Override
    public Integer findByName(String routeName) {
        Query query = getCurrentSession().createQuery("SELECT id FROM RouteEntity re WHERE re.routeName =:routeName");
        query.setParameter("routeName",routeName);

        return (Integer) query.uniqueResult();
    }

    @Override
    public RouteEntity routeById(int id) {
        Query query = getCurrentSession().createQuery("FROM RouteEntity re WHERE re.id=:id ");
        query.setParameter("id",id);
        return (RouteEntity)query.uniqueResult();
    }

}

