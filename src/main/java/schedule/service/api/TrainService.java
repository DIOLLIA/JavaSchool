package schedule.service.api;

import schedule.model.Train;

import java.util.List;

public interface TrainService {

    Train addTrain(Train train);

    void getTrain(int id);

    void deleteTrain(int id);

    List<Train> getTrains();

}

