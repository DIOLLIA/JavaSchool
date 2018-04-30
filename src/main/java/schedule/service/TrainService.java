package schedule.service;

import schedule.entity.TrainEntity;

import java.util.List;

public interface TrainService {

    public void addTrain(TrainEntity train);

    public void getTrain(int id);

    public void deleteTrain(int id);

    public List<TrainEntity> getTrains();

}
