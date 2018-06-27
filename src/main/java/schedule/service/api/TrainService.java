package schedule.service.api;

import schedule.controller.model.ScheduleItem;
import schedule.exception.CustomServiceException;
import schedule.model.Schedule;
import schedule.model.Train;

import java.util.List;

/**
 * @author Rudkov Andrey
 * <p>
 * TrainService class basically interacts with {@link Train} objects.
 * Class methods can: create, delete or edit trains,
 * return full list of trains or certain train by number or ID,
 * return {@link Schedule}  or {@link ScheduleItem} list
 */
public interface TrainService {

    Train addTrain(Train train) throws CustomServiceException;

    void editTrain(Train train) throws CustomServiceException;

    void deleteTrain(int id) throws CustomServiceException;

    List<Train> getTrains() throws CustomServiceException;

    Train get(int id) throws CustomServiceException;

    Train findByNumber(int number) throws CustomServiceException;

    List<Schedule> getScheduleByTrainId(int id) throws CustomServiceException;

    List<ScheduleItem> createTrainRoutesList(List<Schedule> schedules) throws CustomServiceException;
}

