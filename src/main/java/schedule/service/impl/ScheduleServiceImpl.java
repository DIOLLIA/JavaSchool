package schedule.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.dao.api.ScheduleDao;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;
import schedule.entity.StationEntity;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;
import schedule.service.api.ScheduleService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleDao scheduleDao;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Schedule> findScheduleByStations(List<Route> routes, String stationOfDeparture, String stationOfArrival) {
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
    public List<Schedule> findScheduleByStation(Station station) {

        List<Schedule> searchResult = new ArrayList<>();
        List<ScheduleEntity> result = scheduleDao.findByStation(modelMapper.map(station, StationEntity.class));
        for (ScheduleEntity results : result) {
            searchResult.add(modelMapper.map(results, Schedule.class));
        }
        return searchResult;
    }

    @Override
    public List<Schedule> getSchedule() {
        List<ScheduleEntity> scheduleEntityList = scheduleDao.getAll();
        List<Schedule> scheduleList = new ArrayList<>();
        for (ScheduleEntity item : scheduleEntityList) {
            scheduleList.add(modelMapper.map(item, Schedule.class));
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> formatShcedule(List<Schedule> schedule) {
        List<Schedule> formatedSchedule = new ArrayList<>();
        int routeDailyId = 0;
        for (Schedule item : schedule) {
            if (item.getRouteDailyId() != routeDailyId) {
                formatedSchedule.add(item);
                routeDailyId = item.getRouteDailyId();
            }
        }
        return formatedSchedule;
    }


    public List<Schedule> showRouteDetails(List<Schedule> schedule, int scheduleId) {
        List<Schedule> formatedSchedule = new ArrayList<>();
        int routeStationIndex = schedule.get(scheduleId).getRouteDailyId();
        for (Schedule item : schedule) {
            if (item.getRouteDailyId() == routeStationIndex) {
                formatedSchedule.add(item);
            }
        }
        return formatedSchedule;
    }

/*    @Override
    public void addSchedule(Schedule schedule) {
        Schedule schedule2 = new Schedule();
        schedule2.setRouteName(new Route());
        schedule2.setTrainNumber(new Train());
        schedule2.setStationName(new Station());

        //todo autowire model mapper
        ModelMapper mp = new ModelMapper();
        ScheduleEntity map = mp.map(schedule2, ScheduleEntity.class);
        getCurrentSession().save(map);

    }*/

/*    @Override
    public Schedule findScheduleByStationsAndDepartureTime(String stationFrom, String stationTo, LocalTime departureTime) {
        ScheduleEntity scheduleEntity = scheduleDao.findScheduleByStationsAndDepartureTime(stationFrom, stationTo, departureTime);

        Schedule schedule = modelMapper.map(scheduleEntity, Schedule.class);
        return schedule;
    }*/
}
