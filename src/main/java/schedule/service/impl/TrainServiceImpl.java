package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.controller.model.ScheduleItem;
import schedule.dao.api.TrainDao;
import schedule.entity.ScheduleEntity;
import schedule.entity.TrainEntity;
import schedule.model.Schedule;
import schedule.model.Train;
import schedule.service.api.TrainService;

import java.util.*;

/**
 * @author Rudkov Andrey
 * <p>
 * used next reduction of:
 * <l> Model Mapper - MM
 * <l> {@link TrainEntity} - TE
 */
@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private TrainDao trainDao;
    private ModelMapper modelMapper;

    /**
     * Method take
     *
     * @param trainDto mapping it to TE
     * @return added new Train
     */
    public Train addTrain(Train trainDto) {
        TrainEntity trainEntity = modelMapper.map(trainDto, TrainEntity.class);
        trainDao.addTrain(trainEntity);
        return modelMapper.map(trainEntity, Train.class);
    }

    /**
     * Method take
     *
     * @param trainDto mapping it to TE
     * @return update Train
     */
    public void editTrain(Train trainDto) {
        TrainEntity trainEntity = modelMapper.map(trainDto, TrainEntity.class);
        trainDao.editTrain(trainEntity);
    }

    /**
     * Method delete train by
     *
     * @param id
     */
    public void deleteTrain(int id) {
        trainDao.deleteTrain(id);
    }

    /**
     * Method return all TE from database as List
     *
     * @return all TE from database
     */
    public List<Train> getTrains() {

        List<TrainEntity> all = trainDao.getTrains();
        List<Train> trains = new ArrayList<>();

        for (TrainEntity trainEntity : all) {
            trains.add(modelMapper.map(trainEntity, Train.class));
        }
        return trains;
    }

    /**
     * Method find Train from database by
     *
     * @param id
     * @return Train founded by id
     */
    @Override
    public Train get(int id) {
        TrainEntity trainEntity = trainDao.getTrain(id);
        Train train = modelMapper.map(trainEntity, Train.class);
        return train;
    }

    /**
     * Method search Train in database by
     *
     * @param number
     * @return Train if founded otherwise null
     */
    @Override
    public Train findByNumber(int number) {
        TrainEntity trainEntity = trainDao.findByNumber(number);
        if (!(trainEntity == null)) {
            Train train = modelMapper.map(trainEntity, Train.class);
            return train;
        } else return null;
    }

    /**
     * Method search all {@link Schedule} by Train
     *
     * @param id
     * @return List of schedule items with train.id from @param
     */
    @Override
    public List<Schedule> getScheduleByTrainId(int id) {
        List<Schedule> schedules = new ArrayList<>();
        List<ScheduleEntity> scheduleEntities = trainDao.getScheduleByTrainId(id);
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            schedules.add(modelMapper.map(scheduleEntity, Schedule.class));
        }
        return schedules;
    }

    /**
     * Method search schedules with @Param RouteDailyId
     * and request train number. It takes unique routes by RouteDailyId
     * and fill result List of ScheduleIten
     *
     * @param schedules
     * @return List of ScheduleIten with unique routes of requested train
     */
    @Override
    public List<ScheduleItem> createTrainRoutesList(List<Schedule> schedules) {

        Map<Integer, List<Schedule>> scheduleMap = new HashMap<>();
        for (Schedule element : schedules) {
            int dailyRouteId = element.getRouteDailyId();
            if (!scheduleMap.containsKey(dailyRouteId)) {
                scheduleMap.put(dailyRouteId, new ArrayList<>());
            }
            scheduleMap.get(dailyRouteId).add(element);
        }
        List<ScheduleItem> resultScheduleList = new ArrayList<>();

        for (List<Schedule> scheduleList : scheduleMap.values()) {

            scheduleList.sort(new RouteStationIdComparator());

            Schedule firstStationInSchedule = scheduleList.get(0);
            Schedule lastStationInSchedule = scheduleList.get(scheduleList.size() - 1);

            ScheduleItem scheduleItem = new ScheduleItem();
            scheduleItem.setTrainNumber(firstStationInSchedule.getTrainNumber().getNumberOfTrain());
            scheduleItem.setStationOfDeparture(firstStationInSchedule.getStationName().getStationName());
            scheduleItem.setStationOfArrival(lastStationInSchedule.getStationName().getStationName());
            scheduleItem.setDepartureTime(firstStationInSchedule.getDepartureTime());
            scheduleItem.setArrivalTime(lastStationInSchedule.getArrivalTime());
            scheduleItem.setScheduleDailyRouteId(firstStationInSchedule.getRouteDailyId());
            resultScheduleList.add(scheduleItem);
        }
        return resultScheduleList;
    }

    private class RouteStationIdComparator implements Comparator<Schedule> {
        @Override
        public int compare(Schedule o1, Schedule o2) {
            if (o1.getRouteStationIndex() == o2.getRouteStationIndex()) {
                return 0;
            } else if (o1.getRouteStationIndex() > o2.getRouteStationIndex()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Autowired
    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
