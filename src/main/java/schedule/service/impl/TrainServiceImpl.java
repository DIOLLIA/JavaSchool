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

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private TrainDao trainDao;
    private ModelMapper modelMapper;

    public Train addTrain(Train trainDto) {
        TrainEntity trainEntity = modelMapper.map(trainDto, TrainEntity.class);
        trainDao.addTrain(trainEntity);
        return modelMapper.map(trainEntity, Train.class);
    }

    public void editTrain(Train trainDto) {
        TrainEntity trainEntity = modelMapper.map(trainDto, TrainEntity.class);
        trainDao.editTrain(trainEntity);
    }

    public void deleteTrain(int id) {
        trainDao.deleteTrain(id);
    }

    public List<Train> getTrains() {

        List<TrainEntity> all = trainDao.getTrains();
        List<Train> trains = new ArrayList<>();

        for (TrainEntity trainEntity : all) {
            trains.add(modelMapper.map(trainEntity, Train.class));
        }
        return trains;
    }

    @Override
    public Train get(int id) {
        TrainEntity trainEntity = trainDao.getTrain(id);
        Train train = modelMapper.map(trainEntity, Train.class);
        return train;
    }

    @Override
    public Train findByNumber(int number) {
        TrainEntity trainEntity = trainDao.findByNumber(number);
        Train train = modelMapper.map(trainEntity, Train.class);
        return train;
    }

    @Override
    public List<Schedule> getScheduleByTrainId(int id) {
        List<Schedule> schedules = new ArrayList<>();
        List<ScheduleEntity> scheduleEntities = trainDao.getScheduleByTrainId(id);
        for (ScheduleEntity scheduleEntity : scheduleEntities) {
            schedules.add(modelMapper.map(scheduleEntity, Schedule.class));
        }
        return schedules;
    }
//todo check usages
  /*  @Override
    public List<String> trainsList() {
        List<TrainEntity> trainEntityList = trainDao.getTrains();
        List<String> trainsList = new ArrayList<>();
        for (TrainEntity trainNumber : trainEntityList) {
            String trainNumberString = String.valueOf(trainNumber.getNumberOfTrain());
            trainsList.add(trainNumberString);
        }
        return trainsList;
    }*/

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
