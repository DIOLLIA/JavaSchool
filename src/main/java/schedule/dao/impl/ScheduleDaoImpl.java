package schedule.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import schedule.dao.api.ScheduleDao;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;

import java.util.List;

@Repository
public class ScheduleDaoImpl extends GeneralCrudDaoImpl<ScheduleEntity> implements ScheduleDao {
//todo fix  exceptiou caused by qyery.list(): java.lang.ClassCastException: java.util.ArrayList cannot be cast to java.lang.Integer
    @Override
    public List<ScheduleEntity> findByStationsAndRoutes(List<RouteEntity> routes, String stationOfDeparture, String stationOfArrival) {
        Query query = getCurrentSession().createQuery("SELECT sch FROM ScheduleEntity sch JOIN sch.stationName st WHERE sch.routeName IN :routes AND( st.stationName LIKE :stationOfDeparture OR st.stationName LIKE :stationOfArrival)");


        query.setParameterList("routes", routes);
        query.setParameter("stationOfDeparture", stationOfDeparture);
        query.setParameter("stationOfArrival", stationOfArrival);

        List resultList = query.list();
        return resultList;
    }

}
