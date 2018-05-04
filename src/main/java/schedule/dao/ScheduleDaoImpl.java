package schedule.dao;

import org.springframework.stereotype.Repository;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    EntityManager entityManager;

    @Override
    public List<Schedule> findByStationsAndRoutes(List<Route> routes, Station stationOfDeparture, Station stationOfArrival) {
        TypedQuery<Schedule> query = entityManager.createQuery("SELECT sch FROM Schedule sch JOIN sch.station st" + "WHERE sch.route IN :routes" + "AND( st.name LIKE :stationOfDeparture OR st.name LIKE :stationOfArrival)",Schedule.class);
        query.setParameter("routes", routes);
        query.setParameter("stationOfDeparture",stationOfDeparture);
        query.setParameter("stationOfArrival",stationOfArrival);

        List<Schedule> resultList = new ArrayList<>();
        resultList=query.getResultList();
        return resultList;
    }
}
