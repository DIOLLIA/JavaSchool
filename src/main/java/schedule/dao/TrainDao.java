package schedule.dao;

import schedule.model.Train;

import java.util.List;

public interface TrainDao {
    public void addTrain(Train train);

    public Train getTrain(int id);

    public void deleteTrain(int id);

    public List<Train> getTrains();
}
