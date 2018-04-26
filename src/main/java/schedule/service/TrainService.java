package schedule.service;

import schedule.model.Train;

import java.util.List;

public interface TrainService {

    public void addTrain(Train train);

    public void getTrain(int id);

    public void deleteTrain(int id);

    public List<Train> getTrains();

}
