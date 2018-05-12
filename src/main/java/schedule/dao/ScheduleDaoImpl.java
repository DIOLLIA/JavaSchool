package schedule.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;

import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<ScheduleEntity> findByStationsAndRoutes(List<RouteEntity> routes, String stationOfDeparture, String stationOfArrival) {
        Query query = getCurrentSession().createQuery(" FROM ScheduleEntity");
        /*        Query query = getCurrentSession().createQuery("SELECT sch FROM ScheduleEntity sch JOIN sch.stationName st WHERE sch.routeName.id IN :routes AND( st.stationName.id LIKE :stationOfDeparture OR st.stationName.id LIKE :stationOfArrival)");*/

        // query.setParameter("routes", routes);
        // query.setParameter("stationOfDeparture", stationOfDeparture);
        // query.setParameter("stationOfArrival", stationOfArrival);

        List resultList = query.list();
        return resultList;
    }

}