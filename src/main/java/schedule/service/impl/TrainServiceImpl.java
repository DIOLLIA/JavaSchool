package schedule.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.controller.model.ScheduleItem;
import schedule.dao.api.TrainDao;
import schedule.entity.ScheduleEntity;
import schedule.entity.TrainEntity;
import schedule.exception.CustomDaoException;
import schedule.exception.CustomServiceException;
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

    private static final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

    private TrainDao trainDao;
    private ModelMapper modelMapper;

    /**
     * Method take
     *
     * @param trainDto mapping it to TE
     * @return added new Train
     */
    public Train addTrain(Train trainDto) throws CustomServiceException {
        TrainEntity trainEntity;
        try {
            trainEntity = modelMapper.map(trainDto, TrainEntity.class);
            trainDao.addTrain(trainEntity);
        } catch (CustomDaoException e) {
            logger.warn("create train {} warning. Caused by {}", trainDto, e.getCause());
            throw new CustomServiceException(e.getMessage(), e.getCause());
        }

        return modelMapper.map(trainEntity, Train.class);
    }

    /**
     * Method take
     *
     * @param trainDto, mapping it to TE
     * @return update Train
     */
    public void editTrain(Train trainDto) throws CustomServiceException {
        TrainEntity trainEntity;
        try {
            trainEntity = modelMapper.map(trainDto, TrainEntity.class);
            trainDao.editTrain(trainEntity);
        } catch (CustomDaoException e) {
            logger.warn("update train {} warning", trainDto, e.getCause());
            throw new CustomServiceException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Method delete train by
     *
     * @param id
     */
    public void deleteTrain(int id) throws CustomServiceException {
        try {
            trainDao.deleteTrain(id);
        } catch (CustomDaoException e) {
            logger.warn("delete train with id {} warning", id, e.getCause());
            throw new CustomServiceException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Method return all TE from database as List
     *
     * @return all TE from database
     */
    public List<Train> getTrains() throws CustomServiceException {
        List<TrainEntity> all;
        List<Train> trains = new ArrayList<>();
        try {
            all = trainDao.getTrains();
            for (TrainEntity trainEntity : all) {
                trains.add(modelMapper.map(trainEntity, Train.class));
            }
        } catch (CustomDaoException e) {
            logger.warn("get train list  warning", e.getCause());
            throw new CustomServiceException(e.getMessage(), e.getCause());
        }
        return trains;
    }

    /**
     * Method find Train from database by
     *
     * @param id
     * @return {@link Train} founded by id
     */
    @Override
    public Train get(int id) throws CustomServiceException {
        Train train;
        try {
            TrainEntity trainEntity = trainDao.getTrain(id);
            train = modelMapper.map(trainEntity, Train.class);
        } catch (CustomDaoException e) {
            logger.warn("get train with id {}  warning", id, e.getCause());
            throw new CustomServiceException(e.getMessage(), e.getCause());
        }
        return train;
    }

    /**
     * Method search Train in database by
     *
     * @param number
     * @return {@link Train} if founded otherwise null
     */
    @Override
    public Train findByNumber(int number) throws CustomServiceException {
        TrainEntity trainEntity;
        try {
            trainEntity = trainDao.findByNumber(number);
        } catch (CustomDaoException e) {
            logger.warn("Find train by number {} warning", number, e.getCause());
            throw new CustomServiceException(e.getMessage(), e.getCause());
        }
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
    public List<Schedule> getScheduleByTrainId(int id) throws CustomServiceException {
        List<Schedule> schedules = new ArrayList<>();
        try {
            List<ScheduleEntity> scheduleEntities = trainDao.getScheduleByTrainId(id);
            for (ScheduleEntity scheduleEntity : scheduleEntities) {
                schedules.add(modelMapper.map(scheduleEntity, Schedule.class));
            }
        } catch (CustomDaoException e) {
            logger.warn("getScheduleByTrainId {} method  warning", id, e.getCause());
            throw new CustomServiceException(e.getMessage(), e.getCause());
        }
        return schedules;
    }

    /**
     * Method search schedules with @Param RouteDailyId
     * and request train number. It takes unique routes by RouteDailyId
     * and fill result List of ScheduleItem
     *
     * @param schedules
     * @return List of ScheduleItem with unique routes of requested train
     */
    @Override
    public List<ScheduleItem> createTrainRoutesList(List<Schedule> schedules) {

        Map<Integer, List<Schedule>> scheduleMap = new HashMap<>();
        for (Schedule element : schedules) {
            int dailyRouteId = element.getRouteDailyId();
            if (!scheduleMap.containsKey(dailyRouteId)) {
                logger.info("createTrainRoutesList method, " +
                        "on if operator with dailyRouteId {}", dailyRouteId);
                scheduleMap.put(dailyRouteId, new ArrayList<>());
            }
            scheduleMap.get(dailyRouteId).add(element);
        }
        List<ScheduleItem> resultScheduleList = new ArrayList<>();

        for (List<Schedule> scheduleList : scheduleMap.values()) {
            try {
                scheduleList.sort(new RouteStationIdComparator());
            } catch (Exception e) {
                logger.warn("createTrainRoutesList method, " +
                        "on scheduleList.sort {}", scheduleList);
            }
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

    @Autowired
    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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
}
