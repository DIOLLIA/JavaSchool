package schedule.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.dao.RouteDao;
import schedule.dao.ScheduleDao;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;
import schedule.model.Schedule;

import javax.transaction.Transactional;
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

    public List<Schedule> findStations(String stationOfDeparture, String stationOfArrival) {
        List<RouteEntity> routeEntities = routeDao.routes();
        List<ScheduleEntity> all = scheduleDao.findByStationsAndRoutes(routeEntities, stationOfDeparture, stationOfArrival);
        List<Schedule> searchResult = new ArrayList<>();

        for (ScheduleEntity scheduleEntity : all) {
            searchResult.add(modelMapper.map(scheduleEntity, Schedule.class));
        }
        return searchResult;
    }
}
