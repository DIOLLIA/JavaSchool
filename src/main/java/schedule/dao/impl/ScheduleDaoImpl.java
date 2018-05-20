package schedule.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import schedule.dao.api.ScheduleDao;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;
import schedule.entity.StationEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleDaoImpl extends GeneralCrudDaoImpl<ScheduleEntity> implements ScheduleDao {
    @Override
    public List<ScheduleEntity> findByStationsAndRoutes(List<RouteEntity> routes, String stationOfDeparture, String stationOfArrival) {
        if (routes == null || routes.isEmpty() || stationOfDeparture == null || stationOfArrival == null) {
            return new ArrayList<>();
        }

        Query query = getCurrentSession().createQuery("SELECT sch FROM ScheduleEntity sch JOIN sch.stationName st WHERE sch.routeName IN :routes AND (st.stationName LIKE :stationOfDeparture OR st.stationName LIKE :stationOfArrival)");

        query.setParameterList("routes", routes);
        query.setParameter("stationOfDeparture", stationOfDeparture);
        query.setParameter("stationOfArrival", stationOfArrival);
        return query.list();
    }

    @Override
    public List<ScheduleEntity> findByStation(StationEntity station) {
        Query query = getCurrentSession().createQuery("SELECT sch from ScheduleEntity sch JOIN sch.stationName st WHERE  st.stationName LIKE :station");
        query.setParameter("station", station.getStationName());
        List resultList = query.list();
        return resultList;
    }
}
