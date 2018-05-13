package schedule.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.dao.api.RouteDao;
import schedule.dao.api.ScheduleDao;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;
import schedule.model.Train;
import schedule.service.api.ScheduleService;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleDao scheduleDao;
    @Autowired
    RouteDao routeDao;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Schedule> findStations(List<Route> routes, String stationOfDeparture, String stationOfArrival) {
        List<RouteEntity> routeEntities = new ArrayList<>();
        for (Route route : routes) {
            routeEntities.add(modelMapper.map(route, RouteEntity.class));
        }
        List<ScheduleEntity> all = scheduleDao.findByStationsAndRoutes(routeEntities, stationOfDeparture, stationOfArrival);
        List<Schedule> searchResult = new ArrayList<>();

        for (ScheduleEntity scheduleEntity : all) {
            searchResult.add(modelMapper.map(scheduleEntity, Schedule.class));
        }
        return searchResult;
    }

    @Override
    public void addSchedule(Schedule schedule) {
        Schedule schedule2 = new Schedule();
        schedule2.setArrivalTime(LocalTime.of(9, 30));
        schedule2.setDepartureTime(LocalTime.of(10, 10));
        schedule2.setRouteName(new Route());
        schedule2.setTrainNumber(new Train());
        schedule2.setStationName(new Station());

        ModelMapper mp = new ModelMapper();
        ScheduleEntity map = mp.map(schedule2, ScheduleEntity.class);
        getCurrentSession().save(map);

    }
}
