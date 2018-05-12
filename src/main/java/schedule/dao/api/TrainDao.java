package schedule.dao.api;

import schedule.entity.TrainEntity;

import java.util.List;

public interface TrainDao {
    void addTrain(TrainEntity train);

    TrainEntity getTrain(int id);

    void deleteTrain(int id);

    List<TrainEntity> getTrains();
}
