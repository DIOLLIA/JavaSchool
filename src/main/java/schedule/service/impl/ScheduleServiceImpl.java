package schedule.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.controller.model.ScheduleToSend;
import schedule.dao.api.RouteDao;
import schedule.dao.api.ScheduleDao;
import schedule.dao.api.StationDao;
import schedule.dao.api.TrainDao;
import schedule.entity.RouteEntity;
import schedule.entity.ScheduleEntity;
import schedule.entity.StationEntity;
import schedule.messenger.SimpleMessageSender;
import schedule.model.Route;
import schedule.model.Schedule;
import schedule.model.Station;
import schedule.model.Train;
import schedule.service.api.ScheduleService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleDao scheduleDao;
    private ModelMapper modelMapper;
    private TrainDao trainDao;
    private StationDao stationDao;
    private RouteDao routeDao;
    private SessionFactory sessionFactory;
    private SimpleMessageSender simpleMessageSender;

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
    public List<Schedule> formatSchedule(List<Schedule> schedule) {
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
        int routeStationIndex = schedule.get(0).getRouteDailyId();
        for (Schedule item : schedule) {
            if (item.getRouteDailyId() == routeStationIndex) {
                formatedSchedule.add(item);
            }
        }
        return formatedSchedule;
    }

    @Override
    public void addSchedule(String routeName, String arrivalTime, String departureTime, String station, int dailyRoute, int numberInOrder, int trainNumber) {
        List<ScheduleEntity> scheduleEntityList = scheduleDao.getScheduleByDidAfterOrderNumber(numberInOrder, dailyRoute);

        incrementOrderNumbers(scheduleEntityList);

        LocalTime departureTimeFormatted = LocalTime.parse(departureTime);
        LocalTime arrivalTimeFormatted = LocalTime.parse(arrivalTime);
        Train train = modelMapper.map(trainDao.findByNumber(trainNumber), Train.class);
        int routeId = routeDao.findByName(routeName);
        Route route = modelMapper.map(routeDao.routeById(routeId), Route.class);
        Station stationModel = modelMapper.map(stationDao.findByName(station), Station.class);

        Schedule schedule = new Schedule();
        schedule.setTrainNumber(train);
        schedule.setRouteName(route);
        schedule.setStationName(stationModel);
        schedule.setArrivalTime(arrivalTimeFormatted);
        schedule.setDepartureTime(departureTimeFormatted);
        schedule.setRouteDailyId(dailyRoute);
        schedule.setRouteStationIndex(numberInOrder);
        ScheduleEntity scheduleEntity = modelMapper.map(schedule, ScheduleEntity.class);
        scheduleDao.create(scheduleEntity);
    }

    @Override
    public void sendAll(List<ScheduleToSend> scheduleToSends) {
        simpleMessageSender.sendAll(scheduleToSends);
    }

    @Override
    public List<ScheduleToSend> transform(List<Schedule> schedules) {
        List<ScheduleToSend> scheduleToSends = new ArrayList<>();

        for (Schedule item : schedules) {
            ScheduleToSend scheduleToSend = new ScheduleToSend();
            scheduleToSend.setArrTime(item.getArrivalTime().toString().substring(0, 5));
            scheduleToSend.setDepTime(item.getDepartureTime().toString().substring(0, 5));
            scheduleToSend.setStation(item.getStationName().getStationName());
            scheduleToSend.setRouteDailyId(item.getRouteDailyId());
            scheduleToSend.setStationOrder(item.getRouteStationIndex());
            scheduleToSend.setTrain(item.getTrainNumber().getNumberOfTrain());
            scheduleToSends.add(scheduleToSend);
        }
        return scheduleToSends;
    }

    @Override
    public List<Schedule> findTrainById(int trainId) {
        List<ScheduleEntity> scheduleEntity = scheduleDao.findTrainById(trainId);
        List<Schedule> schedules = new ArrayList<>();
        for (ScheduleEntity item : scheduleEntity) {
            schedules.add(modelMapper.map(item, Schedule.class));
        }
        return schedules;

    }

    private void incrementOrderNumbers(List<ScheduleEntity> scheduleEntityList) {
        for (ScheduleEntity scheduleEntity : scheduleEntityList) {
            int stationIdInc = scheduleEntity.getRouteStationIndex() + 1;
            scheduleEntity.setRouteStationIndex(stationIdInc);

            scheduleDao.update(scheduleEntity);
        }
    }

    @Autowired
    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    @Autowired
    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Autowired
    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setSimpleMessageSender(SimpleMessageSender simpleMessageSender) {
        this.simpleMessageSender = simpleMessageSender;
    }
}
