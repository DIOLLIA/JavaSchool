package schedule.dao;

import schedule.entity.TrainEntity;
import schedule.model.Train;

import java.util.List;

public interface TrainDao {
    public void addTrain(TrainEntity train);

    public TrainEntity getTrain(int id);

    public void deleteTrain(int id);

    public List<TrainEntity> getTrains();
}
