package schedule.service.api;

import schedule.controller.model.ScheduleItem;
import schedule.exception.CustomServiceException;
import schedule.model.Schedule;
import schedule.model.Train;

import java.util.List;

public interface TrainService {

    Train addTrain(Train train) throws CustomServiceException;

    void editTrain(Train train);

    void deleteTrain(int id);

    List<Train> getTrains();

    Train get(int id);

    Train findByNumber(int number);

    List<Schedule> getScheduleByTrainId(int id);

    List<ScheduleItem> createTrainRoutesList(List<Schedule> schedules);
}

