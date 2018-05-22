package schedule.service.api;

import schedule.model.Schedule;
import schedule.model.Train;

import java.util.List;

public interface TrainService {

    Train addTrain(Train train);

    void editTrain(Train train);

    void deleteTrain(int id);

    List<Train> getTrains();

    Train get(int id);

    List<Schedule> getScheduleByTrainId(int id);

    List<String> trainsList();
}

